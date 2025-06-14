package org.example.testcrawling.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogoRepository {
    int logoInsert(String teamName, String teamHomepage, String teamLogo);
}
