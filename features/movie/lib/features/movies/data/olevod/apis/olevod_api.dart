import 'dart:core';

import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../core/network/api_client.dart';

final olevodApiProvider = Provider<OlevodApi>((ref) {
  const baseUrl = "https://www.olevod.com";
  return OlevodApi(ref.read(apiClientProvider(ApiClientSetup(
      baseOptions: BaseOptions(
          baseUrl: baseUrl,
          headers: {
            "Cache-Control": "no-cache",
            "Accept":
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
            "Accept-Language": "en-US,en;q=0.9",
            "User-Agent":
                "Mozilla/5.0 (Linux; Android 13; M2012K11AG Build/TKQ1.220829.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/119.0.6045.163 Mobile Safari/537.36"
          },
          responseType: ResponseType.plain),
      interceptors: []))));
});

class OlevodApi {
  final ApiClient apiClient;

  OlevodApi(this.apiClient);

  Map<String, dynamic> notNullMap(Map<String, dynamic> map) {
    return map..removeWhere((k, v) => v == null);
  }

  Future<Response> getVideos(String id, int page) {
    return apiClient.httpClient
        .get('/index.php/vod/show/by/time/id/$id/page/$page.html');
  }

  Future<Response> getVideoDetails(String id) {
    return apiClient.httpClient.getUri(Uri.parse(id));
  }

  Future<Response> getEpisodeDetails(String id) {
    return apiClient.httpClient.getUri(Uri.parse(id));
  }

  Future<Response> search(String query, int page) {
    return apiClient.httpClient
        .get('/index.php/vod/search/page/$page/wd/$query');
  }
}
