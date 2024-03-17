import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class VideoImage extends StatelessWidget {
  final String imageUrl;

  const VideoImage({
    required this.imageUrl,
    Key? key,
  }):super(key: key);

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(4.0),
      child: CachedNetworkImage(
        height: double.infinity,
        width: double.infinity,
        imageUrl: imageUrl,
        fit: BoxFit.cover,
      ),
    );
  }
}