import 'package:html/parser.dart';
import 'package:movie/features/movies/domain/models/episode.dart';
import 'package:movie/features/movies/domain/models/episode_details.dart';
import 'package:movie/features/movies/domain/models/video_details.dart';

import '../../../domain/models/filter.dart';
import '../../../domain/models/video.dart';
import '../../../domain/models/video_rail.dart';
import '../../../domain/repository/movie_repository.dart';
import '../apis/olevod_api.dart';

class OlevodRepository implements MovieRepository {
  final OlevodApi _api;
  final String _url = "www.olevod.com";
  final Filter _movies = Filter(id: "1", title: "电影");
  final Filter _tvSeries = Filter(id: "2", title: "连续剧");
  final Filter _variety = Filter(id: "3", title: "综艺");
  final Filter _anime = Filter(id: "4", title: "动漫");

  OlevodRepository(this._api);

  @override
  Future<VideoRail> getVideoRail(String id, String title) async {
    final response = await getVideos(id, 1);
    return VideoRail(id: id, title: title, videos: response);
  }

  @override
  Future<List<Video>> getVideos(String id, int page) async {
    final data = await _api.getVideos(id, page);
    return _parseVideoList(data.data, _url);
  }

  @override
  Future<VideoDetails> getVideoDetails(String id) async {
    final data = await _api.getVideoDetails(id);
    return _parseVideoDetails(data.data, _url);
  }

  @override
  Future<List<VideoRail>> getHome() {
    final responses = Future.wait([
      getVideoRail(_movies.id, _movies.title),
      getVideoRail(_tvSeries.id, _tvSeries.title),
      getVideoRail(_variety.id, _variety.title),
      getVideoRail(_anime.id, _anime.title),
    ]);

    return responses;
  }

  @override
  Future<EpisodeDetails> getEpisodeDetails(String id) async {
    final data = await _api.getVideoDetails(id);
    final url = _parseM3U8Url(data.data, _url);
    return EpisodeDetails(url: url);
  }

  @override
  Future<List<Video>> search(String query) async {
    final data = await _api.search(query, 1);
    return _parseSearchList(data.data, _url);
  }

  VideoDetails _parseVideoDetails(String response, String baseUrl) {
    final document = parse(response, sourceUrl: baseUrl);
    final itemA = document.querySelector("div.content_thumb>a.vodlist_thumb");
    final title = itemA?.attributes["title"] ?? "";
    final image = itemA?.attributes["data-original"] ?? "";
    final playlist =
        document.querySelectorAll("div.playlist_full>ul.content_playlist>li");
    final synopsis =
        document.querySelector("div.content_desc>span")?.innerHtml ?? "";
    final episodes = playlist.map((e) {
      final listA = e.querySelector("a");
      final id = 'https://$_url${listA?.attributes["href"] ?? ""}';
      final episodeTitle = listA?.innerHtml ?? "";
      return Episode(id: id, title: episodeTitle);
    }).toList();

    return VideoDetails(
        id: title,
        title: title,
        image: image,
        episodes: episodes,
        genres: null,
        synopsis: synopsis);
  }

  List<Video> _parseVideoList(String response, String baseUrl) {
    final document = parse(response, sourceUrl: baseUrl);
    final blocks = document.querySelectorAll("ul.vodlist>li.vodlist_item");
    final list = blocks.map((e) {
      final listA = e.querySelector("a.vodlist_thumb");
      final id = 'https://$_url${listA?.attributes["href"] ?? ""}';
      final title = listA?.attributes["title"] ?? "";
      final imageUrl = listA?.attributes["data-original"] ?? "";
      return Video(id: id, title: title, image: imageUrl);
    }).toList();

    return list;
  }

  String _parseM3U8Url(String response, String baseUrl) {
    final pattern = RegExp(
        "\\b((?:https?):\\\\/\\\\/[-a-zA-Z0-9+&@#/%?=~_|!:, .;\\\\]*[-a-zA-Z0-9+&@#/%=~_|\\\\](.mp4|.m3u8))",
        caseSensitive: false);

    final matcher = pattern.stringMatch(response);

    if (matcher != null) {
      return matcher.replaceAll("\\", "");
    } else {
      return "";
    }
  }

  List<Video> _parseSearchList(String response, String baseUrl) {
    final document = parse(response, sourceUrl: baseUrl);
    final blocks = document.querySelectorAll("ul.vodlist>li.searchlist_item");
    final list = blocks.map((e) {
      final listA = e.querySelector("div.searchlist_titbox>h4.vodlist_title>a");
      final id = 'https://$_url${listA?.attributes["href"] ?? ""}';
      final title = listA?.attributes["title"] ?? "";
      final listB = e.querySelector("div.searchlist_img>a");
      final imageUrl = listB?.attributes["data-original"] ?? "";
      return Video(id: id, title: title, image: imageUrl);
    }).toList();

    return list;
  }
}
