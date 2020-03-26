package com.target.recruiting.common.utils.http;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public interface RestClient {

    <T> ResponseEntity<T> doPost(String url, Map<String, String> queryParams, @Nullable Object requestBody,
            Map<String, String> headers, Class<T> responseType);

    <T> ResponseEntity<T> doPost(String url, Map<String, String> queryParams, @Nullable Object requestBody,
            Class<T> responseType);

    <T> ResponseEntity<T> doGet(String url, Map<String, String> queryParams, Map<String, String> headers,
            Class<T> responseType);

    <T> ResponseEntity<T> doGet(String url, Map<String, String> queryParams, Class<T> responseType);

    default String buildUrlWithQueryParmas(String url, Map<String, String> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return url;
        }
        LinkedMultiValueMap<String, String> multiMap = new LinkedMultiValueMap<>();
        queryParams.entrySet().forEach(entry -> multiMap.add(entry.getKey(), entry.getValue()));
        return UriComponentsBuilder.fromHttpUrl(url).queryParams(multiMap).toUriString();
    }
}