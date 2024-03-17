import 'dart:async';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:movie/features/themes/data/repositories/themes_repository.dart';

final themesServicesProvider =
    AutoDisposeAsyncNotifierProvider<ThemesServices, bool>(ThemesServices.new);

const darkThemeKey = "DARK_THEME_KEY";

class ThemesServices extends AutoDisposeAsyncNotifier<bool> {
  @override
  FutureOr<bool> build() {
    return isDarkTheme();
  }

  Future<bool> isDarkTheme() async {
    final themesRepository = ref.read(themesRepositoryProvider);
    return themesRepository.isDarkTheme();
  }

  Future<void> updateTheme(bool isDark) async {
    final themesRepository = ref.read(themesRepositoryProvider);
    await AsyncValue.guard(() => themesRepository.updateTheme(isDark));
    state = AsyncData(isDark);
  }
}
