package com.example.spotify.search;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class MusicSearch {
    private final String accessToken;

    public MusicSearch(String accessToken) {
        this.accessToken = accessToken;
    }

    public String search(String q){ //q는 검색어
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Host", "api.spotify.com");
        headers.add("Content-type", "application/json");
        String body = "";
      

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.spotify.com/v1/search?type=track&q=" + q, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = (HttpStatus) responseEntity.getStatusCode();
        int status = httpStatus.value(); //상태 코드가 들어갈 status 변수
        String response = responseEntity.getBody();

        return response;
    }
}
