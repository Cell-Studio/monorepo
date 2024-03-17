import 'package:flutter/material.dart';

class VideoPlayerProgressBar extends StatelessWidget {
  const VideoPlayerProgressBar(
      {required this.duration,
      required this.position,
      required this.onChange,
      super.key});

  final Duration duration;
  final Duration position;
  final Function(Duration) onChange;

  @override
  Widget build(BuildContext context) {
    return Theme(
        data: Theme.of(context).copyWith(
          sliderTheme: SliderThemeData(
            thumbShape: const RoundSliderThumbShape(
              enabledThumbRadius: 6.0, // Set the radius of the thumb
            ),
            overlayShape: SliderComponentShape.noOverlay,
            trackShape: const RoundedRectSliderTrackShape(),
          ),
        ),
        child: Slider(
          value: position.inSeconds.toDouble(),
          max: duration.inSeconds.toDouble(),
          onChanged: (double value) {
            onChange(Duration(seconds: value.toInt()));
          },
        ));
  }
}
