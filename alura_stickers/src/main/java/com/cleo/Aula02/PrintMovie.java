package com.cleo.Aula02;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class PrintMovie {
    final static Map<String, String> CORESTERMINAL = new HashMap<>(
            Map.of("red", "\u001B[31m",
                    "green", "\u001B[32m",
                    "yellow", "\u001B[33m",
                    "blue", "\u001B[34m",
                    "magenta", "\u001B[35m",
                    "cyan", "\u001B[36m",
                    "reset", "\u001B[0m"));

    /**
     * @param json
     *             Recebe um JSON e exibe os filmes
     *             Mostrar os filmes do top 250
     *             Exibir o título, ano e classificação
     */

    static void mostrarFilmes(JsonNode filme) {

        int imDbRating = (int) Math.floor(filme.get("imDbRating").asDouble());

        System.out.println(CORESTERMINAL.get("blue") + "-".repeat(60) + "\u001b[0m");
        System.out.print(CORESTERMINAL.get("green"));
        System.out.println(filme.get("title"));
        System.out.println(filme.get("year"));
        System.out.println(filme.get("imDbRating"));
        System.out.println(CORESTERMINAL.get("yellow") + "⭐".repeat(imDbRating) + "\u001b[0m");
        System.out.println(CORESTERMINAL.get("blue") + "-".repeat(60) + "\u001b[0m");
        System.out.print(CORESTERMINAL.get("reset"));

    }

}
