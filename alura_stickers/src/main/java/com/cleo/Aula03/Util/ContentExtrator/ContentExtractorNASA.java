package com.cleo.Aula03.Util.ContentExtrator;

import java.util.ArrayList;
import java.util.List;

import com.cleo.Aula03.Model.Content;
import com.cleo.Aula03.Util.ClientHttp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContentExtractorNASA implements ContentExtrator {
    final private String URL;

    public ContentExtractorNASA(String URL) {
        this.URL = URL;
    }

    /**
     * Recebe uma string JSON e retorna uma lista de Content
     * 
     * @param jsonString
     * @return List<Content>
     */
    public List<Content> parse() {
        String jsonString = new ClientHttp().pegarJsonDaUrl(URL);
        // String tratedJsonString = jsonString.replaceAll("._V1_U(.+?).jpg", ".jpg");
        try {
            List<Content> contents = new ArrayList<>();
            // Popular lista de Content com os dados do json
            new ObjectMapper().readTree(jsonString)
                    .forEach(content -> {
                        contents.add(new Content(content.get("title").asText(), content.get("url").asText(), 10.0));
                    });
            return contents;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
