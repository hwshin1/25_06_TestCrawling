package org.example.testcrawling.controller;

import org.example.testcrawling.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class CrawlingController {
    @Autowired
    private MainService mainService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/crawling";
    }

    @RequestMapping("/crawling")
    public String crawling(Model model) {
        List<Map<String, String>> teams = mainService.webCrawling();
        model.addAttribute("teams", teams);
        return "index";
    }
}
