package com.backend.show.utils;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

public class ExternalCallTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalCallTemplate.class);
    private static final RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
    }

    public static Object executeGET(String url){
        try {
            return restTemplate.execute(url,GET,null,null);
        }catch (Exception e){
            LOGGER.error("Error occured while fetching data with URL : {}",url);
        }
        return null;
    }

    public static<T> String executePOST(String url,T request, Map<String,String> headers){
        var entity = getRequestAndHeaders(request,headers);
        try {
            return restTemplate.postForObject(url,entity,String.class);
        }catch (Exception e){
            LOGGER.error("Error occured while fetching POST request : {}",url);
        }
        return "";
    }

    private static <T> HttpEntity<T> getRequestAndHeaders(T request, Map<String, String> headers) {
        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        if (MapUtils.isNotEmpty(headers)){
            headers.forEach((k,v) -> header.set(k,v));
        }
        if (!ObjectUtils.isEmpty(request)){
            return new HttpEntity<>(request,header);
        }
        return new HttpEntity<>(header);
    }
}
