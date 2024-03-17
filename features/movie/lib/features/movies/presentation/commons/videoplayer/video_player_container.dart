import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';

import 'video_player_control.dart';

class VideoPlayerContainer extends StatefulWidget {
  const VideoPlayerContainer(this.url, this.title, {super.key});

  final String url;
  final String title;

  @override
  State<VideoPlayerContainer> createState() => _VideoPlayerContainerState();
}

class _VideoPlayerContainerState extends State<VideoPlayerContainer> {
  late VideoPlayerController _controller;
  Duration position = const Duration(seconds: 0);
  Duration duration = const Duration(seconds: 0);
  bool isPlaying = false;
  double speed = 1.0;

  @override
  void initState() {
    super.initState();
    print("Url: ${widget.url}");
    _controller = VideoPlayerController.networkUrl(Uri.parse(widget.url))
      ..initialize().then((_) {
        // Ensure the first frame is shown after the video is initialized, even before the play button has been pressed.
        setState(() {});
        _controller.play();
      });

    _controller.addListener(_updateState);
  }

  void _updateState() {
    if (!mounted) return;

    setState(() {
      position = _controller.value.position;
      duration = _controller.value.duration;
      isPlaying = _controller.value.isPlaying;
      speed = _controller.value.playbackSpeed;
    });
  }

  @override
  void dispose() {
    super.dispose();
    _controller.removeListener(_updateState);
    _controller.dispose();
  }

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double screenHeight = MediaQuery.of(context).size.height;
    return Container(
        width: screenWidth,
        height: screenHeight,
        color: Colors.black, // Set the background color
        child: Stack(children: [
          Center(
            child: _controller.value.isInitialized
                ? AspectRatio(
                    aspectRatio: _controller.value.aspectRatio,
                    child: VideoPlayer(_controller),
                  )
                : Container(),
          ),
          VideoPlayerControl(
            title: widget.title,
            duration: duration,
            position: position,
            isPlaying: isPlaying,
            playbackSpeed: speed,
            onChangePlayStatus: (value) {
              setState(() {
                value ? _controller.play() : _controller.pause();
              });
            },
            onChange: (duration) {
              _controller.seekTo(duration);
            },
            onChangePlaybackSpeed: (speed) {
              _controller.setPlaybackSpeed(speed);
            },
          )
        ]));
  }
}
