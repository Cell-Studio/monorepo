import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:movie/app/navigation/app_router.dart';
import 'package:movie/features/themes/services/theme_services.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'core/shared_preferences/provider/preferences_provider.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  final preferences = await SharedPreferences.getInstance();

  runApp(ProviderScope(
    overrides: [
      sharedPreferenceProvider.overrideWithValue(preferences),
    ],
    child: const MyApp(),
  ));
}

class MyApp extends ConsumerWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final themesServices = ref.watch(themesServicesProvider);
    final appRouter = ref.watch(appRouterProvider);

    final lightTheme = ThemeData(
      useMaterial3: true,
      brightness: Brightness.light,
      fontFamily: 'prodigysans',
    );

    final darkTheme = ThemeData(
      useMaterial3: true,
      brightness: Brightness.dark,
      fontFamily: 'prodigysans',
    );

    return MaterialApp.router(
      routerConfig: appRouter,
      title: 'All in 1',
      theme: lightTheme.copyWith(
          textTheme: lightTheme.textTheme.copyWith(
              displayLarge: lightTheme.textTheme.displayLarge
                  ?.copyWith(fontSize: 80.0, height: 1.1),
              displayMedium: lightTheme.textTheme.displayMedium
                  ?.copyWith(fontSize: 64.0, height: 1.125),
              displaySmall: lightTheme.textTheme.displaySmall?.copyWith(
                  fontSize: 48.0, height: 1.1667, fontWeight: FontWeight.w600),
              headlineLarge: lightTheme.textTheme.headlineLarge
                  ?.copyWith(fontSize: 40.0, height: 1.1),
              headlineMedium: lightTheme.textTheme.headlineMedium?.copyWith(
                  fontSize: 24.0, height: 1.3333, fontWeight: FontWeight.w600),
              headlineSmall: lightTheme.textTheme.headlineSmall?.copyWith(
                  fontSize: 18.0, height: 1.3333, fontWeight: FontWeight.w600),
              titleLarge: lightTheme.textTheme.headlineLarge
                  ?.copyWith(fontSize: 40.0, height: 1.1),
              titleMedium: lightTheme.textTheme.headlineMedium?.copyWith(
                  fontSize: 24.0, height: 1.3333, fontWeight: FontWeight.w600),
              titleSmall: lightTheme.textTheme.headlineSmall?.copyWith(
                  fontSize: 18.0, height: 1.3333, fontWeight: FontWeight.w600),
              bodyLarge: lightTheme.textTheme.bodyLarge?.copyWith(fontSize: 18.0, height: 1.5556),
              bodyMedium: lightTheme.textTheme.bodyMedium?.copyWith(fontSize: 16.0, height: 1.5),
              bodySmall: lightTheme.textTheme.bodySmall?.copyWith(fontSize: 14.0, height: 1.429),
              labelLarge: lightTheme.textTheme.labelLarge?.copyWith(fontSize: 20.0, height: 1.2, fontWeight: FontWeight.w600),
              labelMedium: lightTheme.textTheme.labelMedium?.copyWith(fontSize: 16.0, height: 1.25, fontWeight: FontWeight.w600),
              labelSmall: lightTheme.textTheme.labelSmall?.copyWith(fontSize: 14.0, height: 1.429, fontWeight: FontWeight.w600))),
      darkTheme: darkTheme.copyWith(
          textTheme: darkTheme.textTheme.copyWith(
              displayLarge: darkTheme.textTheme.displayLarge
                  ?.copyWith(fontSize: 80.0, height: 1.1),
              displayMedium: darkTheme.textTheme.displayMedium
                  ?.copyWith(fontSize: 64.0, height: 1.125),
              displaySmall: darkTheme.textTheme.displaySmall?.copyWith(
                  fontSize: 48.0, height: 1.1667, fontWeight: FontWeight.w600),
              headlineLarge: darkTheme.textTheme.headlineLarge
                  ?.copyWith(fontSize: 40.0, height: 1.1),
              headlineMedium: darkTheme.textTheme.headlineMedium?.copyWith(
                  fontSize: 24.0, height: 1.3333, fontWeight: FontWeight.w600),
              headlineSmall: darkTheme.textTheme.headlineSmall?.copyWith(
                  fontSize: 18.0, height: 1.3333, fontWeight: FontWeight.w600),
              titleLarge: darkTheme.textTheme.headlineLarge
                  ?.copyWith(fontSize: 40.0, height: 1.1),
              titleMedium: darkTheme.textTheme.headlineMedium?.copyWith(
                  fontSize: 24.0, height: 1.3333, fontWeight: FontWeight.w600),
              titleSmall: darkTheme.textTheme.headlineSmall?.copyWith(
                  fontSize: 18.0, height: 1.3333, fontWeight: FontWeight.w600),
              bodyLarge: darkTheme.textTheme.bodyLarge?.copyWith(fontSize: 18.0, height: 1.5556),
              bodyMedium: darkTheme.textTheme.bodyMedium?.copyWith(fontSize: 16.0, height: 1.5),
              bodySmall: darkTheme.textTheme.bodySmall?.copyWith(fontSize: 14.0, height: 1.429),
              labelLarge: darkTheme.textTheme.labelLarge?.copyWith(fontSize: 20.0, height: 1.2, fontWeight: FontWeight.w600),
              labelMedium: darkTheme.textTheme.labelMedium?.copyWith(fontSize: 16.0, height: 1.25, fontWeight: FontWeight.w600),
              labelSmall: darkTheme.textTheme.labelSmall?.copyWith(fontSize: 14.0, height: 1.429, fontWeight: FontWeight.w600))),
      themeMode:
          (themesServices.value ?? false) ? ThemeMode.dark : ThemeMode.light,
    );
  }
}
