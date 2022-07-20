package com.cleo.Aula02;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    public JsonNode parse(String jsonString) {
        try {
            // Filter image url from json replace "@(.+?).jpg" to "@.jpg"
            String tratedJsonString = jsonString.replaceAll("._V1_U(.+?).jpg", ".jpg");
            return new ObjectMapper().readTree(tratedJsonString);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao parsear o JSON");
        }

        return null;
    }

    public JsonNode pegarJsonDaUrl(String URL) throws IOException, InterruptedException {

        // Criar um objeto do tipo HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Criar um objeto do tipo HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL)).GET().build();

        // Criar um objeto do tipo HttpResponse
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Parsear o JSON retornado pelo servidor
        return parse(response.body());
    }
}
