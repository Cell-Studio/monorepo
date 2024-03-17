import 'package:flutter/material.dart';

import '../../domain/models/video.dart';
import 'video_image.dart';

class VideoWidgetV1 extends StatelessWidget {
  final Video video;
  final VoidCallback? onTap;

  const VideoWidgetV1({
    required this.video,
    this.onTap,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
        child: Stack(
      children: [
        VideoImage(imageUrl: video.image),
        Container(
          decoration: const BoxDecoration(
            gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [Color(0x00ffffff), Color(0xaa000000)]),
          ),
        ),
        Align(
            alignment: Alignment.bottomLeft,
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(
                video.title,
                maxLines: 3,
                style: const TextStyle(color: Colors.white, fontSize: 16.0),
              ),
            )),
        Positioned.fill(
          child: Material(
            color: Colors.transparent,
            child: InkWell(
              onTap: onTap,
            ),
          ),
        ),
      ],
    ));
  }
}
