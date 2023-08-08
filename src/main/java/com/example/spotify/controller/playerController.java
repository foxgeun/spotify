package com.example.spotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spotify.search.MusicSearch;
import com.example.spotify.token.CreateToken;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class playerController {
	
    private final String accessToken;

    public playerController() {
        // CreateToken 객체를 생성하여 access token을 얻어옵니다.
        CreateToken createToken = new CreateToken();
        this.accessToken = createToken.accesstoken();
    }
	
	@GetMapping("/")
    public String player(Model model) {
        MusicSearch musicSearch = new MusicSearch(accessToken);
        
		model.addAttribute("accessToken", accessToken);
		
		
		
    	return "player";

    }
	
	

	
	
}
