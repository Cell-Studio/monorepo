import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../commons/video_rail_widget.dart';
import 'movies_controller.dart';

class MoviesScreen extends ConsumerWidget {
  const MoviesScreen({super.key});

  Widget _createBody(AsyncValue moviesController, MoviesController notifier) {
    if (moviesController.isLoading) {
      return const Center(
        child: CircularProgressIndicator(),
      );
    } else if (moviesController.hasValue) {
      final controller = moviesController.requireValue;
      final data = controller.movies;
      return ListView.separated(
        padding: const EdgeInsets.symmetric(vertical: 16.0),
        physics: const BouncingScrollPhysics(),
        separatorBuilder: (BuildContext context, int index) =>
            const SizedBox(height: 16),
        scrollDirection: Axis.vertical,
        itemCount: data.length,
        itemBuilder: (context, i) {
          final video = data[i];
          return VideoRail(
            id: video.id,
            title: video.title,
            videos: video.videos,
            onViewAllTap: (id) {
              notifier.onViewAllClicked(id);
            },
            onVideoTap: (id) {
              notifier.onVideoClicked(id);
            },
          );
        },
      );
    } else {
      return const SizedBox();
    }
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final moviesController = ref.watch(moviesControllerProvider);
    final notifier = ref.read(moviesControllerProvider.notifier);
    return Scaffold(
        appBar: AppBar(
          title: const Text('Movies'),
          actions: [
            IconButton(
              icon: const Icon(Icons.search_rounded),
              onPressed: () {
                notifier.onSearchClicked();
              },
            ),
          ],
        ),
        body: _createBody(moviesController, notifier));
  }
}

class MovieSourcesDialog extends StatelessWidget {
  const MovieSourcesDialog({
    Key? key,
    required List<String> keys,
    required String selected,
  })  : _keys = keys,
        _selected = selected,
        super(key: key);

  final List<String> _keys;
  final String _selected;

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      shrinkWrap: true,
      physics: const ScrollPhysics(),
      itemBuilder: (context, index) {
        final key = _keys[index];
        return ListTile(
          dense: true,
          title: Row(
            children: [
              if (key == _selected)
                const Icon(
                  Icons.check,
                  size: 20.0,
                  color: Colors.white,
                )
              else
                Container(width: 20.0),
              const SizedBox(width: 16.0),
              Text(key),
            ],
          ),
          selected: key == _selected,
          onTap: () {
            Navigator.of(context).pop(key);
          },
        );
      },
      itemCount: _keys.length,
    );
  }
}
