import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:movie/features/movies/presentation/search/search_controller.dart';

import '../commons/video_widget.dart';

class SearchScreen extends ConsumerStatefulWidget {
  const SearchScreen({
    super.key,
  });

  @override
  ConsumerState<SearchScreen> createState() => _SearchScreenState();
}

class _SearchScreenState extends ConsumerState<SearchScreen> {
  final queryController = TextEditingController();

  @override
  void dispose() {
    queryController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Search"),
        ),
        body: SafeArea(
          child: Consumer(
            builder: (context, ref, child) {
              final moviesController = ref.watch(searchControllerProvider);
              final notifier = ref.read(searchControllerProvider.notifier);

              if (moviesController.isLoading) {
                return const Center(
                  child: CircularProgressIndicator(),
                );
              } else if (moviesController.hasValue) {
                final controller = moviesController.requireValue;
                final data = controller.list;
                return Column(
                  children: [
                    TextField(
                      controller: queryController,
                      autofocus: true,
                      onSubmitted: (value) {
                        print("Submit");
                        print(value);
                        notifier.onQuerySubmitted(value);
                      },
                      textInputAction: TextInputAction.search,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: 'Search',
                      ),
                    ),
                    Expanded(
                        child: Padding(
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
                    ))
                  ],
                );
              } else {
                return const SizedBox();
              }
            },
          ),
        ));
  }
}

// class SearchScreen extends StatelessWidget {
//   const SearchScreen({Key? key}) : super(key: key);
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//         appBar: AppBar(
//           title: const Text("Search"),
//         ),
//         body: SafeArea(
//           child: Consumer(
//             builder: (context, ref, child) {
//               final moviesController = ref.watch(searchControllerProvider);
//               final notifier = ref.read(searchControllerProvider.notifier);
//
//               if (moviesController.isLoading) {
//                 return const Center(
//                   child: CircularProgressIndicator(),
//                 );
//               } else if (moviesController.hasValue) {
//                 final controller = moviesController.requireValue;
//                 final data = controller.list;
//                 return Column(
//                   children: [
//                     Padding(
//                       padding: const EdgeInsets.all(8.0),
//                       child: GridView.builder(
//                           physics: const BouncingScrollPhysics(
//                               parent: AlwaysScrollableScrollPhysics()),
//                           gridDelegate:
//                               const SliverGridDelegateWithFixedCrossAxisCount(
//                             crossAxisCount: 2,
//                             childAspectRatio: 1 / 1.4,
//                             mainAxisSpacing: 8.0,
//                             crossAxisSpacing: 8.0,
//                           ),
//                           itemCount: data.length,
//                           itemBuilder: (BuildContext ctx, index) {
//                             return VideoWidgetV1(
//                               video: data[index],
//                               onTap: () {
//                                 notifier.onVideoClicked(data[index].getId());
//                               },
//                             );
//                           }),
//                     )
//                   ],
//                 );
//               } else {
//                 return const SizedBox();
//               }
//             },
//           ),
//         ));
//   }
// }
