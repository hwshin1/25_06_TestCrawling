package org.example.testcrawling.controller;

import org.example.testcrawling.service.LogoService;
import org.example.testcrawling.vo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CrawlingController {
    @Autowired
    private LogoService logoService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/crawling";
    }

    @RequestMapping("/crawling")
    public String crawling(Model model) {
        List<Team> teams = logoService.webCrawling();

        model.addAttribute("teams", teams);

        System.out.println(teams);

        return "index";
    }
}
