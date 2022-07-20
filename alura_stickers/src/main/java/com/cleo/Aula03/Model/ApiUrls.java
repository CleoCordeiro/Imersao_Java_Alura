package com.cleo.Aula03.Model;

public enum ApiUrls {
    IMDB("https://raw.githubusercontent.com/CleoCordeiro/Imersao_Java_Alura/main/assets/Top250Movies.Json"),
    NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-15&end_date=2022-07-20");

    private final String url;

    ApiUrls(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }

}
