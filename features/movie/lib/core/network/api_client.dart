import 'package:dio/dio.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final apiClientProvider = ProviderFamily<ApiClient, ApiClientSetup>((_, setup) {
  final client = ApiClient(setup.baseOptions);
  for (var element in setup.interceptors) {
    client.addInterceptors(element);
  }
  return client;
});

class ApiClientSetup {
  List<Interceptor> interceptors;
  BaseOptions baseOptions;

  ApiClientSetup({required this.baseOptions, required this.interceptors});
}

class ApiClient {
  late Dio httpClient;

  ApiClient(BaseOptions baseOptions) {
    httpClient = Dio();
    httpClient.options = baseOptions;
    if (kDebugMode) {
      httpClient.interceptors
          .add(LogInterceptor(responseBody: true, requestBody: true));
    }
  }

  void addInterceptors(Interceptor interceptor) {
    httpClient.interceptors.add(interceptor);
  }

  void removeInterceptors(Interceptor interceptor) {
    httpClient.interceptors.remove(interceptor);
  }
}
