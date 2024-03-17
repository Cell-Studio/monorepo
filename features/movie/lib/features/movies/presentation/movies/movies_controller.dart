import 'dart:async';

import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../data/services/movie_services.dart';
import '../../domain/models/video_rail.dart';
import '../../navigation/movie_router.dart';

final moviesControllerProvider =
    AutoDisposeAsyncNotifierProvider<MoviesController, MovieScreenState>(
        MoviesController.new);

class MoviesController extends AutoDisposeAsyncNotifier<MovieScreenState> {
  late final movieRepository = ref.watch(movieRepositoryProvider);
  late final movieNavigator = ref.read(movieNavigatorProvider);

  @override
  FutureOr<MovieScreenState> build() async {
    final value = await movieRepository.getHome();
    return MovieScreenState(value);
  }

  Future<void> onViewAllClicked(String id) async {
    movieNavigator.openList(id);
  }

  Future<void> onVideoClicked(String id) async {
    movieNavigator.openDetails(id);
  }

  Future<void> onSearchClicked() async {
    movieNavigator.openSearch();
  }
}

class MovieScreenState {
  List<VideoRail> movies;

  MovieScreenState(this.movies);
}
