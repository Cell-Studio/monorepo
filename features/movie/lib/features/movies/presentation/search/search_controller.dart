import 'dart:async';

import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../data/services/movie_services.dart';
import '../../domain/models/video.dart';
import '../../navigation/movie_router.dart';

final searchControllerProvider =
    AutoDisposeAsyncNotifierProvider<SearchController, SearchScreenState>(
        SearchController.new);

class SearchController extends AutoDisposeAsyncNotifier<SearchScreenState> {
  late final movieRepository = ref.read(movieRepositoryProvider);
  late final movieNavigator = ref.read(movieNavigatorProvider);

  @override
  FutureOr<SearchScreenState> build() async {
    return SearchScreenState([]);
  }

  Future<void> onVideoClicked(String id) async {
    movieNavigator.openDetails(id);
  }

  Future<void> onQuerySubmitted(String query) async {
    final curState = state.valueOrNull ?? SearchScreenState([]);
    final res = await movieRepository.search(query);
    state = AsyncValue.data(curState.copyWith(list: res));
  }
}

class SearchScreenState {
  List<Video> list;

  SearchScreenState(this.list);

  SearchScreenState copyWith({list}) {
    return SearchScreenState(list ?? this.list);
  }
}
