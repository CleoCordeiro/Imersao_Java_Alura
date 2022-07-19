package com.cleo.Aula01;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Fazer uma conexão HTTP com IMDB e pegar os tops 250 filmes
 * Extrair as informações de cada filme (título, ano, classificação)
 * Exibir e manipular essas informações
 *
 */
public class Main {
    final static Map<String, String> CORESTERMINAL = new HashMap<>(
            Map.of("red", "\u001B[31m",
                    "green", "\u001B[32m",
                    "yellow", "\u001B[33m",
                    "blue", "\u001B[34m",
                    "magenta", "\u001B[35m",
                    "cyan", "\u001B[36m",
                    "reset", "\u001B[0m"));

    public static void main(String[] args) throws IOException, InterruptedException {
        final String URL = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        JsonNode json = pegarJsonDaUrl(URL);
        mostrarFilmes(json);
    }

    private static JsonNode pegarJsonDaUrl(String URL) throws IOException, InterruptedException {

        // Criar um objeto do tipo HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Criar um objeto do tipo HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL)).GET().build();

        // Criar um objeto do tipo HttpResponse
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Parsear o JSON retornado pelo servidor
        return new JsonParser().parse(response.body());
    }


    /**
     * @param json
     * Recebe um JSON e exibe os filmes
     * Mostrar os filmes do top 250
     * Exibir o título, ano e classificação
     */

    private static void mostrarFilmes(JsonNode json) {

        // Percorrer o JSON e exibir os filmes

        json.get("items").forEach(filme -> {
            int imDbRating = (int) Math.floor(filme.get("imDbRating").asDouble());

            System.out.println(CORESTERMINAL.get("blue") + "-".repeat(60) + "\u001b[0m");
            System.out.print(CORESTERMINAL.get("green"));
            System.out.println(filme.get("title"));
            System.out.println(filme.get("year"));
            System.out.println(filme.get("imDbRating"));
            System.out.println(CORESTERMINAL.get("yellow") + "⭐".repeat(imDbRating) + "\u001b[0m");
            System.out.println(CORESTERMINAL.get("blue") + "-".repeat(60) + "\u001b[0m");
            System.out.print(CORESTERMINAL.get("reset"));
        });
    }
}
