package com.cleo.Aula03.Model;

import com.cleo.Aula03.Util.ContentExtrator.ContentExtractorIMDB;
import com.cleo.Aula03.Util.ContentExtrator.ContentExtractorNASA;
import com.cleo.Aula03.Util.ContentExtrator.ContentExtrator;

public enum ApiUrls {
    IMDB(new ContentExtractorIMDB(
            "https://raw.githubusercontent.com/CleoCordeiro/Imersao_Java_Alura/main/assets/Top250Movies.Json")),
    NASA(new ContentExtractorNASA(
            "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-15&end_date=2022-07-20"));

    private final ContentExtrator contentExtrator;

    ApiUrls(ContentExtrator contentExtrator) {
        this.contentExtrator = contentExtrator;
    }

    public ContentExtrator contentExtrator() {
        return contentExtrator;
    }

}
