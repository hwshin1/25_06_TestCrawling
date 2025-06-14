package org.example.testcrawling.service;

import org.example.testcrawling.repository.LogoRepository;
import org.example.testcrawling.vo.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogoService {

    private static final String BASE_URL = "https://www.kleague.com";
    private static final String TARGET_URL = BASE_URL + "/index.do";

    @Autowired
    private LogoRepository logoRepository;

    public List<Team> webCrawling() {
        List<Team> teamList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(TARGET_URL).get();
            Elements teamElements = doc.select("ul.f-wrap li");

            for (Element li : teamElements) {
                Element aTag = li.selectFirst("a");
                Element imgTag = li.selectFirst("img");

                if (aTag == null || imgTag == null) continue;

                String teamName = aTag.attr("title");
                String teamHomepage = formatUrl(aTag.attr("href"));
                String teamLogo = formatUrl(imgTag.attr("src"));

                logoRepository.logoInsert(teamName, teamHomepage, teamLogo);

                Team team = new Team(teamName, teamHomepage, teamLogo);
                teamList.add(team);
            }

        } catch (Exception e) {
            System.err.println("크롤링 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("크롤링 실패", e);
        }

        return teamList;
    }

    // 상대 경로일 경우 BASE_URL 붙이기
    private String formatUrl(String url) {
        if (url == null || url.isEmpty()) return "";
        if (url.startsWith("https")) return url;
        if (url.startsWith("//")) return "https:" + url;
        return BASE_URL + url;
    }
}
