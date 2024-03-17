import 'package:flutter/material.dart';

import '../../domain/models/video.dart';
import 'video_widget.dart';

class VideoRail extends StatelessWidget {
  final List<Video> videos;
  final String title;
  final String id;
  final Function(String)? onVideoTap;
  final Function(String)? onViewAllTap;

  const VideoRail({
    required this.id,
    required this.title,
    required this.videos,
    this.onVideoTap,
    this.onViewAllTap,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Expanded(
                child: Padding(
              padding:
                  const EdgeInsets.symmetric(vertical: 4.0, horizontal: 16.0),
              child: Text(title,
                  style: const TextStyle(
                    fontSize: 16.0,
                    fontWeight: FontWeight.bold,
                  )),
            )),
            InkWell(
                onTap: onViewAllTap != null
                    ? () {
                        onViewAllTap!(id);
                      }
                    : null,
                child: const Padding(
                  padding:
                      EdgeInsets.symmetric(vertical: 4.0, horizontal: 16.0),
                  child: Text("View All",
                      style: TextStyle(
                        fontSize: 16.0,
                      )),
                )),
          ],
        ),
        const SizedBox(
          height: 8,
        ),
        SizedBox(
            height: 204,
            child: ListView.separated(
              padding: const EdgeInsets.symmetric(horizontal: 16.0),
              physics: const BouncingScrollPhysics(),
              separatorBuilder: (BuildContext context, int index) =>
                  const SizedBox(
                width: 8,
              ),
              scrollDirection: Axis.horizontal,
              itemCount: videos.length,
              itemBuilder: (context, i) {
                final video = videos[i];
                return SizedBox(
                    width: 146,
                    child: VideoWidgetV1(
                        video: video,
                        onTap: onVideoTap != null
                            ? () {
                                onVideoTap!(video.id);
                              }
                            : null));
              },
            ))
      ],
    );
  }
}
