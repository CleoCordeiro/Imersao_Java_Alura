package com.cleo.Aula02;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class MakeSticker {
    int originalHeight;
    int originalWidth;
    int newHeight;
    String stickerText;
    String stickerName;

    /**
     * Cria um novo sticker com o nome e texto passados
     * 
     * @param inputStream
     *                    Recebe um imageInputStream
     * @param stickerName
     *                    Nome do sticker
     * @param stickerText
     *                    Texto do sticker
     */
    public void makeSticker(InputStream inputStream, String stickerName, double imDbRating) {

        try {
            BufferedImage originalImage = ImageIO.read(inputStream);
            originalHeight = originalImage.getHeight();
            originalWidth = originalImage.getWidth();
            newHeight = (int) (originalHeight * 1.1);
            this.stickerText = getStickerText(imDbRating);
            this.stickerName = stickerName;
            drawSticker(originalImage);
        } catch (IOException e) {
            System.out.println("Não foi possível ler a imagem");
        }

    }

    /**
     * Grava a imagem nova no disco
     * 
     * @param originalImage
     *                      Imagem original
     * 
     */
    private void drawSticker(BufferedImage originalImage) {
        BufferedImage imageSticker = new BufferedImage(originalWidth, newHeight, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) imageSticker.getGraphics();
        g2d.drawImage(originalImage, 0, 0, null);
        drawStickerText(g2d);
        writeImage(imageSticker);
    }

    /**
     * Escreve o texto no sticker
     * 
     * @param graphics
     *                 Graphics da imagem
     * 
     */
    private void drawStickerText(Graphics2D g2d) {
        // Configuração da fonte de acordo com o tamanho da imagem
        int increaseHeight = newHeight - originalHeight;
        int fontSize = (int) Math.floor(increaseHeight * 0.9);
        Font font = new java.awt.Font("Comic Sans", java.awt.Font.BOLD, fontSize);
        g2d.setFont(font);

        // Calculando a posição do texto no sticker
        Rectangle2D fontBounds = font.getStringBounds(stickerText, g2d.getFontRenderContext());
        int StartDrawX = (originalWidth / 2) - (int) Math.floor(fontBounds.getWidth() / 2);
        int StartDrawY = (int) (newHeight - (fontBounds.getHeight() - increaseHeight));

        // Desenhando o texto no sticker
        TextLayout textLayout = new TextLayout(stickerText, g2d.getFont(), g2d.getFontRenderContext());
        Shape shape = textLayout.getOutline(null);
        g2d.setStroke(new BasicStroke(fontSize * 0.15f));
        g2d.translate(StartDrawX, StartDrawY);
        g2d.setColor(Color.BLACK);
        g2d.draw(shape);
        g2d.setColor(Color.YELLOW);
        g2d.fill(shape);

    }

    public String getStickerText(double imDbRating) {
        // Retornar o texto do sticket de acordo com a classificação
        // Se a classificação for maior que 9 retornar "TOPISSIMO"
        // Se a classificação for maior que 8.5 retornar "TOPZERA"
        // Se a classificação for menor que 8.5 retornar "TOP"

        if (imDbRating > 9) {
            return "TOPISSIMO";
        } else if (imDbRating > 8.5) {
            return "TOPZERA";
        } else {
            return "TOP";
        }

    }

    /**
     * Grava a imagem nova no disco
     * 
     * @param imageSticker
     *                     Imagem nova
     * 
     */
    private void writeImage(BufferedImage imageSticker) {
        try {
            String tratedStickerName = stickerName.replace(":", "");
            File newSticker = new File("stickers/" + tratedStickerName + ".png");
            newSticker.mkdirs();
            ImageIO.write(imageSticker, "png", newSticker);
        } catch (IOException e) {
            System.out.println("Não foi possível salvar a imagem " + stickerName + ".png em disco\n" + e.getMessage());
        }
    }

}
