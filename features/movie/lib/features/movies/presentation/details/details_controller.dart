import 'dart:async';

import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../domain/models/video_details.dart';
import '../../data/services/movie_services.dart';
import '../../navigation/movie_router.dart';

final movieDetailsControllerProvider = AutoDisposeAsyncNotifierProviderFamily<
    MovieDetailsController,
    MovieDetailsScreenState,
    String>(MovieDetailsController.new);

class MovieDetailsController
    extends AutoDisposeFamilyAsyncNotifier<MovieDetailsScreenState, String> {
  late final movieNavigator = ref.read(movieNavigatorProvider);
  late final movieRepository = ref.read(movieRepositoryProvider);

  @override
  FutureOr<MovieDetailsScreenState> build(String id) async {
    final value = await movieRepository.getVideoDetails(id);
    return MovieDetailsScreenState(value);
  }

  void onEpisodeClicked(String id) async {
    final url = await movieRepository.getEpisodeDetails(id);
    movieNavigator.openPlayer(url.url);
  }
}

class MovieDetailsScreenState {
  VideoDetails details;

  MovieDetailsScreenState(this.details);
}
