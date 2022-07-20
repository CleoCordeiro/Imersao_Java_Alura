package com.cleo.Aula03.Util.ContentExtrator;

import java.util.ArrayList;
import java.util.List;

import com.cleo.Aula03.Model.Content;
import com.cleo.Aula03.Util.ClientHttp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContentExtractorIMDB implements ContentExtrator {
    final private String URL;

    public ContentExtractorIMDB(String URL) {
        this.URL = URL;
    }

    /**
     * Recebe uma string JSON e retorna uma lista de Content
     * 
     * @param jsonString
     * @return List<Content>
     */
    public List<Content> parse() {
        final String jsonString = new ClientHttp().pegarJsonDaUrl(URL);
        final String tratedJsonString = jsonString.replaceAll("._V1_U(.+?).jpg", ".jpg");
        try {
            final List<Content> contents = new ArrayList<>();
            // Popular lista de Content com os dados do json
            new ObjectMapper().readTree(tratedJsonString)
                    .get("items").forEach(json -> {
                        contents.add(new Content(json.get("title").asText(), json.get("image").asText(),
                                json.get("imDbRating").asDouble()));
                    });
            return contents;
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
