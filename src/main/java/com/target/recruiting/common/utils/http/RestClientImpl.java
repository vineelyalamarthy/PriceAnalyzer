package com.target.recruiting.common.utils.http;
import java.io.IOException;
import java.util.Map;

import com.target.recruiting.common.utils.ProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientImpl implements RestClient {

    private static final Logger logger = LoggerFactory.getLogger(RestClientImpl.class);

    private RestTemplate restTemplate;

    private static final String SERVICE_INVOCATION_ERROR = "Error invoking downstream service :";

    public RestClientImpl(@Autowired RestTemplate restTemplate) {
        Assert.notNull(restTemplate, "RestTemplate can't be null");
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> ResponseEntity<T> doPost(String url, Map<String, String> queryParams, Object requestBody,
                                        Map<String, String> headers, Class<T> responseType) {
        //TODO : Implement POST logic.
        return null;

    }

    @Override
    public <T> ResponseEntity<T> doPost(String url, Map<String, String> queryParams, Object requestBody,
                                        Class<T> resonseType) {
        return doPost(url, queryParams, requestBody, null, resonseType);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> ResponseEntity<T> doGet(String url, Map<String, String> queryParams, Map<String, String> headers,
                                       Class<T> responseType) {
        logger.debug("doGet: url={}", url);
        logger.debug("doGet: queryParams={}", queryParams);
        logger.debug("doGet: request headers : {}", headers);
        ResponseEntity<T> resp = null;
        HttpEntity requestEntity = new HttpEntity(buildHttpHeaders(headers));

        String urlWithQueryParams = buildUrlWithQueryParmas(url, queryParams);
        logger.debug("urlWithQueryParams : {}", urlWithQueryParams);
        try {
            resp = restTemplate.exchange(urlWithQueryParams, HttpMethod.GET, requestEntity, responseType);

            logger.debug("doGet: Response from down stream service invocation {}:", JsonUtils.toJson(resp));

        } catch (HttpStatusCodeException httpException) {
            logger.error(SERVICE_INVOCATION_ERROR + " doGet - url={}, queryParams={}, request headers={}", url,
                    JsonUtils.toJson(queryParams), JsonUtils.toJson(headers), httpException);
            logger.error(" HttpStatusCodeException <<<Response Body>>>: {}", httpException.getResponseBodyAsString());

            throw new ProductException(SERVICE_INVOCATION_ERROR + " " + httpException.getMessage(), httpException);

        } catch (RestClientException rce) {
            logger.error(SERVICE_INVOCATION_ERROR + " doGet - url={}, queryParams={}, request headers={}", url,
                    JsonUtils.toJson(queryParams), JsonUtils.toJson(headers), rce);
            throw new ProductException(SERVICE_INVOCATION_ERROR + " " + rce.getMessage(), rce);
        }
        return resp;
    }

    private void addErrorHandler(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {

            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                logger.debug("Error Response from client: {}", JsonUtils.toJson(response.getBody()));

            }
        });
    }

    @Override
    public <T> ResponseEntity<T> doGet(String url, Map<String, String> queryParams, Class<T> resonseType) {
        return doGet(url, queryParams, null, resonseType);
    }

    private HttpHeaders buildHttpHeaders(Map<String, String> headers) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        if (headers == null || headers.isEmpty()) {
            return requestHeaders;
        }
        headers.entrySet().forEach(ent -> requestHeaders.add(ent.getKey(), ent.getValue()));
        return requestHeaders;
    }
}