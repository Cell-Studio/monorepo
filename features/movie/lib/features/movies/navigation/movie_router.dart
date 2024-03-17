import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../app/navigation/app_router.dart';
import '../presentation/details/details_screen.dart';
import '../presentation/list/list_screen.dart';
import '../presentation/movies/movies_screen.dart';
import '../presentation/player/player_screen.dart';
import '../presentation/search/search_screen.dart';

class _MovieRoutePaths {
  static const String home = 'movies';
  static const String details = 'details';
  static const String list = 'list';
  static const String player = 'player';
  static const String search = 'search';
}

class _MovieRouteParams {
  static const String id = 'id';
  static const String title = 'title';
}

class MovieRoutePaths {
  static const String home = '/${_MovieRoutePaths.home}';
  static const String details =
      '/${_MovieRoutePaths.home}/${_MovieRoutePaths.details}';
  static const String list =
      '/${_MovieRoutePaths.home}/${_MovieRoutePaths.list}';
  static const String player =
      '/${_MovieRoutePaths.home}/${_MovieRoutePaths.player}';
  static const String search =
      '/${_MovieRoutePaths.home}/${_MovieRoutePaths.search}';
}

final movieRoute = GoRoute(
    path: _MovieRoutePaths.home,
    builder: (context, state) => const MoviesScreen(),
    routes: [
      GoRoute(
          path: _MovieRoutePaths.search,
          builder: (context, state) {
            return const SearchScreen();
          }),
      GoRoute(
          path: _MovieRoutePaths.list,
          builder: (context, state) {
            final id = state.uri.queryParameters[_MovieRouteParams.id] ?? '';
            return ListScreen(id);
          }),
      GoRoute(
        path: _MovieRoutePaths.details,
        builder: (context, state) {
          final id = state.uri.queryParameters[_MovieRouteParams.id] ?? '';
          return DetailsScreen(id);
        },
      ),
      GoRoute(
        path: _MovieRoutePaths.player,
        builder: (context, state) {
          final id = state.uri.queryParameters[_MovieRouteParams.id] ?? '';
          final title =
              state.uri.queryParameters[_MovieRouteParams.title] ?? '';
          return PlayerScreen(id, title);
        },
      ),
    ]);

class MovieNavigator {
  final GoRouter _goRouter;

  MovieNavigator(this._goRouter);

  void openDetails(String id) {
    _goRouter.push(Uri(
        path: MovieRoutePaths.details,
        queryParameters: {_MovieRouteParams.id: id}).toString());
  }

  void openList(String id) {
    _goRouter.push(Uri(
        path: MovieRoutePaths.list,
        queryParameters: {_MovieRouteParams.id: id}).toString());
  }

  void openPlayer(String id) {
    _goRouter.push(Uri(
        path: MovieRoutePaths.player,
        queryParameters: {_MovieRouteParams.id: id}).toString());
  }

  void openSearch() {
    _goRouter.push(Uri(
      path: MovieRoutePaths.search,
    ).toString());
  }
}

final movieNavigatorProvider = Provider<MovieNavigator>((ref) {
  final router = ref.read(appRouterProvider);
  return MovieNavigator(router);
});
