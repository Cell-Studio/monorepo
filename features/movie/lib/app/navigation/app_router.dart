import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:movie/features/movies/navigation/movie_router.dart';

final _key = GlobalKey<NavigatorState>();

final appRouterProvider = Provider<GoRouter>((ref) {
  return GoRouter(
    navigatorKey: _key,
    initialLocation: AppRoutePaths.home,
    routes: [
      GoRoute(
          path: AppRoutePaths.home,
          redirect: (_, GoRouterState state) {
            final path = state.uri.path;
            if (path == AppRoutePaths.home) {
              return MovieRoutePaths.home;
            }
            return null;
          },
          routes: [
            movieRoute,
          ])
    ],
    errorBuilder: (context, state) => Scaffold(
      body: Center(
        child: Text('No route defined for ${state.name}'),
      ),
    ),
  );
});

class AppRoutePaths {
  static const String home = '/';
}
