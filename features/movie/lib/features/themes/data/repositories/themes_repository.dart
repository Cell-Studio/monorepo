import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../../../core/shared_preferences/provider/preferences_provider.dart';

final themesRepositoryProvider = Provider<ThemesRepository>((ref) {
  return ThemesRepository(ref.read(sharedPreferenceProvider));
});

const darkThemeKey = "DARK_THEME_KEY";

class ThemesRepository {
  final SharedPreferences sharedPreferences;

  ThemesRepository(this.sharedPreferences);

  Future<bool> isDarkTheme() async {
    return Future.value(sharedPreferences.getBool(darkThemeKey) ?? false);
  }

  Future<void> updateTheme(bool isDark) async {
    await sharedPreferences.setBool(darkThemeKey, isDark);
  }
}
