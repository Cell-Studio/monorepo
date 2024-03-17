import 'package:flutter/material.dart';

import 'playback_speed_dialog.dart';
import 'video_player_bottom_bar.dart';

class VideoPlayerControl extends StatefulWidget {
  const VideoPlayerControl(
      {required this.title,
      required this.position,
      required this.duration,
      required this.isPlaying,
      required this.playbackSpeed,
      required this.onChange,
      required this.onChangePlayStatus,
      required this.onChangePlaybackSpeed,
      super.key});

  final String title;
  final Function(Duration) onChange;
  final Function(bool) onChangePlayStatus;
  final Function(double) onChangePlaybackSpeed;
  final Duration position;
  final Duration duration;
  final bool isPlaying;
  final double playbackSpeed;

  @override
  State<VideoPlayerControl> createState() => _VideoPlayerControlState();
}

class _VideoPlayerControlState extends State<VideoPlayerControl> {
  bool isVisible = false;

  Future<void> _onSpeedButtonTap() async {
    final chosenSpeed = await showModalBottomSheet<double>(
      context: context,
      isScrollControlled: true,
      builder: (context) => PlaybackSpeedDialog(
        speeds: const [0.25, 0.5, 0.75, 1.0, 1.25, 1.5, 2, 4],
        selected: widget.playbackSpeed,
      ),
    );

    if (chosenSpeed != null) {
      widget.onChangePlaybackSpeed(chosenSpeed);
    }
  }

  Widget _buildBottomBar() {
    return VideoPlayerBottomBar(
        position: widget.position,
        duration: widget.duration,
        onChange: widget.onChange,
        selectionWidget: IconButton(
            icon: const Icon(Icons.speed_rounded),
            onPressed: () async {
              _onSpeedButtonTap();
            }));
  }

  Widget _buildTopBar() {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 6),
      child: Text(widget.title),
    );
  }

  Widget _buildHitArea() {
    return GestureDetector(
        onTap: () {
          setState(() {
            isVisible = false;
          });
        },
        child: ColoredBox(
          color: Colors.transparent,
          child: Center(
              child: Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              UnconstrainedBox(
                child: DecoratedBox(
                  decoration: const BoxDecoration(
                    color: Colors.black54,
                    shape: BoxShape.circle,
                  ),
                  // Always set the iconSize on the IconButton, not on the Icon itself:
                  // https://github.com/flutter/flutter/issues/52980
                  child: IconButton(
                    iconSize: 32,
                    padding: const EdgeInsets.all(12.0),
                    icon: const Icon(Icons.fast_rewind_rounded),
                    onPressed: () {
                      widget.onChange(
                          widget.position + const Duration(seconds: -10));
                    },
                  ),
                ),
              ),
              const SizedBox(
                width: 32.0,
              ),
              UnconstrainedBox(
                child: DecoratedBox(
                  decoration: const BoxDecoration(
                    color: Colors.black54,
                    shape: BoxShape.circle,
                  ),
                  // Always set the iconSize on the IconButton, not on the Icon itself:
                  // https://github.com/flutter/flutter/issues/52980
                  child: IconButton(
                    iconSize: 32,
                    padding: const EdgeInsets.all(12.0),
                    icon: Icon(
                      widget.isPlaying
                          ? Icons.pause_rounded
                          : Icons.play_arrow_rounded,
                    ),
                    onPressed: () {
                      widget.onChangePlayStatus(!widget.isPlaying);
                    },
                  ),
                ),
              ),
              const SizedBox(
                width: 32.0,
              ),
              UnconstrainedBox(
                child: DecoratedBox(
                  decoration: const BoxDecoration(
                    color: Colors.black54,
                    shape: BoxShape.circle,
                  ),
                  // Always set the iconSize on the IconButton, not on the Icon itself:
                  // https://github.com/flutter/flutter/issues/52980
                  child: IconButton(
                    iconSize: 32,
                    padding: const EdgeInsets.all(12.0),
                    icon: const Icon(Icons.fast_forward_rounded),
                    onPressed: () {
                      widget.onChange(
                          widget.position + const Duration(seconds: 10));
                    },
                  ),
                ),
              ),
            ],
          )),
        ));
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        onTap: () {
          setState(() {
            isVisible = !isVisible;
          });
        },
        child: AbsorbPointer(
            absorbing: !isVisible,
            child: AnimatedOpacity(
                opacity: isVisible ? 1.0 : 0.0, // Set the target opacity
                duration: const Duration(
                    milliseconds: 250), // Set the duration of the animation
                curve: Curves.easeInOut, // Set the easing curve
                child: Stack(
                  children: [
                    _buildHitArea(),
                    Align(
                      alignment: Alignment.topLeft,
                      child: _buildTopBar(),
                    ),
                    Align(
                        alignment: Alignment.bottomLeft,
                        child: _buildBottomBar())
                  ],
                ))));
  }
}
