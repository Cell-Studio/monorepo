import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../commons/video_widget.dart';
import 'list_controller.dart';

class ListScreen extends StatelessWidget {
  final String id;

  const ListScreen(this.id, {super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("List"),
        ),
        body: SafeArea(
          child: Consumer(
            builder: (context, ref, child) {
              final moviesController = ref.watch(listControllerProvider(id));
              final notifier = ref.read(listControllerProvider(id).notifier);

              if (moviesController.isLoading) {
                return const Center(
                  child: CircularProgressIndicator(),
                );
              } else if (moviesController.hasValue) {
                final controller = moviesController.requireValue;
                final data = controller.list;
                return Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: GridView.builder(
                      physics: const BouncingScrollPhysics(
                          parent: AlwaysScrollableScrollPhysics()),
                      gridDelegate:
                          const SliverGridDelegateWithFixedCrossAxisCount(
                        crossAxisCount: 2,
                        childAspectRatio: 1 / 1.4,
                        mainAxisSpacing: 8.0,
                        crossAxisSpacing: 8.0,
                      ),
                      itemCount: data.length,
                      itemBuilder: (BuildContext ctx, index) {
                        return VideoWidgetV1(
                          video: data[index],
                          onTap: () {
                            notifier.onVideoClicked(data[index].id);
                          },
                        );
                      }),
                );
              } else {
                return const SizedBox();
              }
            },
          ),
        ));
  }
}
