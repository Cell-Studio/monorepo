import 'episode.dart';
import 'genre.dart';

class VideoDetails {
  String id;
  String title;
  String image;
  List<Episode>? episodes;
  List<Genre>? genres;
  String? synopsis;

  VideoDetails(
      {required this.id,
      required this.title,
      required this.image,
      this.episodes,
      this.genres,
      this.synopsis});
}
