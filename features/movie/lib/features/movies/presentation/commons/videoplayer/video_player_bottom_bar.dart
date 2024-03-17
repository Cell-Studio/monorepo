import 'package:flutter/material.dart';

import '../../helpers/utils.dart';
import 'video_player_progress_bar.dart';

class VideoPlayerBottomBar extends StatelessWidget {
  const VideoPlayerBottomBar(
      {required this.position,
      required this.duration,
      required this.onChange,
      this.selectionWidget,
      super.key});

  final Function(Duration) onChange;
  final Duration position;
  final Duration duration;
  final Widget? selectionWidget;

  Widget _buildPosition(Duration position, Duration duration) {
    return Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        child: Text(
          '${formatDuration(position)} / ${formatDuration(duration)}',
        ));
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        VideoPlayerProgressBar(
            duration: duration, position: position, onChange: onChange),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            _buildPosition(position, duration),
            const Spacer(),
            selectionWidget != null ? selectionWidget! : const SizedBox(),
          ],
        )
      ],
    );
  }
}
