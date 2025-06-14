package org.example.testcrawling.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private int id;
    private String teamName;
    private String teamLogo;
    private String teamHomepage;

    public Team(String teamName, String teamHomepage, String teamLogo) {
        this.teamName = teamName;
        this.teamHomepage = teamHomepage;
        this.teamLogo = teamLogo;
    }
}
