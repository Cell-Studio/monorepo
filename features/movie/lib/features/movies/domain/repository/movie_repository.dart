import '../models/episode_details.dart';
import '../models/video.dart';
import '../models/video_details.dart';
import '../models/video_rail.dart';

abstract class MovieRepository {
  Future<VideoRail> getVideoRail(String id, String title);

  Future<List<Video>> getVideos(String id, int page);

  Future<List<VideoRail>> getHome();

  Future<VideoDetails> getVideoDetails(String id);

  Future<EpisodeDetails> getEpisodeDetails(String id);

  Future<List<Video>> search(String query);
}
