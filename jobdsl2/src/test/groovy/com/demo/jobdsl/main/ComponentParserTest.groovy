package com.demo.jobdsl.main

import com.demo.jobdsl.main.domain.Repository
import com.demo.jobdsl.main.support.FixtureFileReader

class ComponentParserTest {
    public static void main(String[] args) {
        List<Repository> repositories = new ComponentParser().parse(new FixtureFileReader())
    }
}
