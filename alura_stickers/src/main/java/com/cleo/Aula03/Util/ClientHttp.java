package com.cleo.Aula03.Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    public String pegarJsonDaUrl(String URL) {

        // Criar um objeto do tipo HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Criar um objeto do tipo HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL)).GET().build();

        // Criar um objeto do tipo HttpResponse
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}
