package com.cleo.Aula03;

import java.io.IOException;

import com.cleo.Aula03.Model.ApiUrls;
import com.cleo.Aula03.Util.Consumer;
import com.cleo.Aula03.Util.ContentExtrator.ContentExtrator;

/**
 * Fazer uma conexão HTTP com IMDB e pegar os tops 250 filmes
 * Extrair as informações de cada filme (título, ano, classificação)
 * Exibir e manipular essas informações
 *
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ContentExtrator contentExtractor = ApiUrls.NASA.contentExtrator();
        new Consumer(contentExtractor).consumer();
    }

}
