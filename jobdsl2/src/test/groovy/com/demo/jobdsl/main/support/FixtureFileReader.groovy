package com.demo.jobdsl.main.support

class FixtureFileReader {
    String readFileFromWorkspace(String fileName){
        return new File("fixture/" + fileName).text
    }
}
