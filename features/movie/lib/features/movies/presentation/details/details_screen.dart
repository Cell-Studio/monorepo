import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../domain/models/episode.dart';
import '../../domain/models/video_details.dart';
import 'details_controller.dart';

class DetailsScreen extends StatelessWidget {
  final String id;

  const DetailsScreen(this.id, {Key? key}) : super(key: key);

  Widget _renderEmptyHeader() {
    return Padding(
      padding: const EdgeInsets.all(12),
      child:
          Row(crossAxisAlignment: CrossAxisAlignment.start, children: <Widget>[
        ClipRRect(
          borderRadius: BorderRadius.circular(4.0),
          child: Container(
            color: Colors.black,
            height: 204,
            width: 146,
          ),
        ),
        const SizedBox(width: 12),
        Expanded(
          child: ClipRRect(
            borderRadius: BorderRadius.circular(4.0),
            child: Container(
              color: Colors.black,
              height: 16 * 2,
              width: double.infinity,
            ),
          ),
        )
      ]),
    );
  }

  Widget _renderEmptySynopsis() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 12),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(4.0),
        child: Container(
          color: Colors.black,
          height: 16 * 5,
          width: double.infinity,
        ),
      ),
    );
  }

  Widget _renderEmptyEpisode() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 12),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(4.0),
        child: Container(
          color: Colors.black,
          height: 16 * 10,
          width: double.infinity,
        ),
      ),
    );
  }

  Widget _renderHeader(VideoDetails data) {
    return Padding(
      padding: const EdgeInsets.all(12),
      child:
          Row(crossAxisAlignment: CrossAxisAlignment.start, children: <Widget>[
        ClipRRect(
          borderRadius: BorderRadius.circular(4.0),
          child: CachedNetworkImage(
            imageUrl: data.image,
            fit: BoxFit.cover,
            height: 204,
            width: 146,
          ),
        ),
        const SizedBox(width: 12),
        Expanded(
          child: Text(
            data.title,
            style: const TextStyle(color: Colors.white, fontSize: 16.0),
          ),
        )
      ]),
    );
  }

  Widget _renderSynopsis(VideoDetails data) {
    return Padding(
        padding: const EdgeInsets.symmetric(horizontal: 12),
        child: Text(data.synopsis ?? '',
            style: const TextStyle(color: Colors.white, fontSize: 16.0)));
  }

  Widget _renderEpisodes(List<Episode> episodes, Function(String) testing) {
    return ListView.builder(
      shrinkWrap: true,
      physics: const NeverScrollableScrollPhysics(),
      itemCount: episodes.length,
      itemBuilder: (context, i) {
        final episode = episodes[i];
        return SizedBox(
            width: double.infinity,
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 12.0),
              child: OutlinedButton(
                  onPressed: () {
                    testing(episode.id);
                  },
                  child: Text(episode.title,
                      textAlign: TextAlign.center,
                      style: const TextStyle(fontSize: 16.0))),
            ));
      },
    );
  }

  Widget _renderEpisodesHeader() {
    return const Padding(
        padding:
            EdgeInsets.only(left: 12.0, right: 12.0, bottom: 0.0, top: 12.0),
        child: Text("Episodes",
            style: TextStyle(color: Colors.white, fontSize: 24.0)));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(body: SafeArea(
      child: Consumer(
        builder: (context, ref, child) {
          final provider = movieDetailsControllerProvider(id);
          final details = ref.watch(provider);
          if (details.isLoading) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          } else if (details.hasValue) {
            final value = details.requireValue;
            return ListView(
                physics: const BouncingScrollPhysics(
                    parent: AlwaysScrollableScrollPhysics()),
                children: [
                  _renderHeader(value.details),
                  _renderSynopsis(value.details),
                  _renderEpisodesHeader(),
                  _renderEpisodes(value.details.episodes!, (id) {
                    ref.read(provider.notifier).onEpisodeClicked(id);
                  })
                ]);
          } else {
            return const SizedBox();
          }
        },
      ),
    ));
  }
}
