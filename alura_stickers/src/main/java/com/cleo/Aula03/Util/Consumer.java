package com.cleo.Aula03.Util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.cleo.Aula03.Util.ContentExtrator.ContentExtrator;

public class Consumer {
    ContentExtrator contentExtractor;

    public Consumer(ContentExtrator contentExtractor) {
        this.contentExtractor = contentExtractor;
    }

    public void consumer() {

        contentExtractor.parse().forEach(content -> {
            try (InputStream inputStream = new URL(content.image()).openStream()) {
                System.out.println("Gerando sticker para " + content.title());
                new MakeSticker().makeSticker(inputStream, content.title(),
                        content.rating());

                System.out.println("Sticker criado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao criar Stream da imagem");
                e.printStackTrace();
            }
        });
    }
}
