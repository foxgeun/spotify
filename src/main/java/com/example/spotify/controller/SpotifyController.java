package com.example.spotify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spotify.search.MusicSearch;
import com.example.spotify.token.CreateToken;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class SpotifyController {
    private final String accessToken;

    public SpotifyController() {
        // CreateToken 객체를 생성하여 access token을 얻어옵니다.
        CreateToken createToken = new CreateToken();
        this.accessToken = createToken.accesstoken();
    }

    @GetMapping(value = "/search")
    public String searchSong(@RequestParam("q") String query, Model model) {
        MusicSearch musicSearch = new MusicSearch(accessToken);
        String response = musicSearch.search(query);

        // JSONParser로 JSONObject로 변환
        try {
            JSONParser parser = new JSONParser();
            System.out.println(response);
            JSONObject jsonObject = (JSONObject) parser.parse(response);

            // "tracks" 안의 "items" 배열 추출
            JSONObject tracksObject = (JSONObject) jsonObject.get("tracks");
            System.out.println(tracksObject);
            JSONArray itemsArray = (JSONArray) tracksObject.get("items");

            // "name" 값을 추출하여 리스트에 담기
            List<String> songNames = new ArrayList<>();
            for (Object item : itemsArray) {
                JSONObject trackObject = (JSONObject) item;
                String songName = (String) trackObject.get("name");
                songNames.add(songName);
                
            }
            model.addAttribute("accessToken", accessToken);
            model.addAttribute("dd", songNames);
            
            System.out.println(songNames);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("searchResult", response);

        return "index"; // index.html 템플릿으로 이동
    }
    
    
}
