package com.cleo.Aula03;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.cleo.Aula03.Model.ApiUrls;
import com.cleo.Aula03.Util.Consumer;
import com.cleo.Aula03.Util.MakeSticker;
import com.cleo.Aula03.Util.ContentExtrator.ContentExtractorIMDB;
import com.cleo.Aula03.Util.ContentExtrator.ContentExtractorNASA;
import com.cleo.Aula03.Util.ContentExtrator.ContentExtrator;

/**
 * Fazer uma conexão HTTP com IMDB e pegar os tops 250 filmes
 * Extrair as informações de cada filme (título, ano, classificação)
 * Exibir e manipular essas informações
 *
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ContentExtrator contentExtractorIMDB = new ContentExtractorIMDB(ApiUrls.IMDB.url());
        ContentExtrator contentExtractorNASA = new ContentExtractorNASA(ApiUrls.NASA.url());
        new Consumer(contentExtractorNASA).consumer();
    }

}
