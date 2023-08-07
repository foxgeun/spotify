package com.example.spotify.controller;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.spotify.search.MusicSearch;
import com.example.spotify.token.CreateToken;

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

        model.addAttribute("searchResult", response);

        return "index"; // index.html 템플릿으로 이동
    }

}

