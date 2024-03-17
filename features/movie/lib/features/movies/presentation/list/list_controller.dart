import 'dart:async';

import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../data/services/movie_services.dart';
import '../../domain/models/video.dart';
import '../../navigation/movie_router.dart';

final listControllerProvider = AutoDisposeAsyncNotifierProviderFamily<
    MovieListController, MovieListScreenState, String>(MovieListController.new);

class MovieListController
    extends AutoDisposeFamilyAsyncNotifier<MovieListScreenState, String> {
  late final movieRepository = ref.read(movieRepositoryProvider);
  late final movieNavigator = ref.read(movieNavigatorProvider);

  @override
  FutureOr<MovieListScreenState> build(String id) async {
    final value = await movieRepository.getVideos(id, 0);
    return MovieListScreenState(value);
  }

  Future<void> onVideoClicked(String id) async {
    movieNavigator.openDetails(id);
  }
}

class MovieListScreenState {
  List<Video> list;

  MovieListScreenState(this.list);
}
