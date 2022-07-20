package com.cleo.Aula02;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Fazer uma conexão HTTP com IMDB e pegar os tops 250 filmes
 * Extrair as informações de cada filme (título, ano, classificação)
 * Exibir e manipular essas informações
 *
 */
public class Main {
    final static String URL = "https://raw.githubusercontent.com/CleoCordeiro/Imersao_Java_Alura/main/assets/Top250Movies.Json";

    public static void main(String[] args) throws IOException, InterruptedException {
        JsonParser jsonParser = new JsonParser();
        MakeSticker makeSticker = new MakeSticker();

        JsonNode json = jsonParser.pegarJsonDaUrl(URL);

        json.get("items").forEach(filme -> {
            PrintMovie.mostrarFilmes(filme);
            try (InputStream inputStream = new URL(filme.get("image")
                    .asText()).openStream()) {

                makeSticker.makeSticker(inputStream, filme.get("title").asText(),
                        filme.get("imDbRating").asDouble());

                System.out.println("Sticker criado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao criar Stream da imagem");
                e.printStackTrace();
            }
        });
    }

}
