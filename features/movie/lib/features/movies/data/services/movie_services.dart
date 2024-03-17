import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:movie/features/movies/data/olevod/apis/olevod_api.dart';

import '../../domain/repository/movie_repository.dart';
import '../olevod/repository/olevod_repository.dart';

final movieRepositoryProvider = Provider<MovieRepository>((ref) {
  return OlevodRepository(ref.read(olevodApiProvider));
});
