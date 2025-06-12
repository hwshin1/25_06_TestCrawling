package org.example.testcrawling.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {
    // 크롤링 할 url 주소
    private static final String URL = "https://www.kleague.com/index.do";

    public List<Map<String, String>> webCrawling() {
        String baseURL = "https://www.kleague.com";

        try {
            Document doc = Jsoup.connect(URL).get();
            Elements teamElements = doc.select("ul.f-wrap li");

            List<Map<String, String>> teamList = new ArrayList<>();

            for (Element li : teamElements) {
                Element aTag = li.selectFirst("a");
                Element imgTag = li.selectFirst("img");

                if (aTag != null && imgTag != null) {
                    Map<String, String> teamData = new HashMap<>();
                    teamData.put("teamName", aTag.attr("title"));
                    teamData.put("homepage", aTag.attr("href"));

                    String src = imgTag.attr("src");
                    if (!src.startsWith("http")) {
                        src = baseURL + src;
                    }

                    String srcSet = imgTag.attr("srcset");
                    if (!srcSet.startsWith("http")) {
                        srcSet = baseURL + srcSet;
                    }

                    teamData.put("logo", src);
                    teamData.put("logoSrcSet", srcSet);

                    teamList.add(teamData);
                    System.out.println(teamData);
                }
            }

            return teamList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
