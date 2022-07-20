package com.declan.myboard.controller;

import com.declan.myboard.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        model.addAttribute("principal", principalDetails);
        return "index";
    }
}
