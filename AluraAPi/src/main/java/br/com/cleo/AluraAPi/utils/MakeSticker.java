package br.com.cleo.AluraAPi.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class MakeSticker {
    int originalHeight;
    int originalWidth;
    int newHeight;
    String stickerText;

    /**
     * Cria um novo sticker com o nome e texto passados
     * 
     * @param inputStream
     *                    Recebe um imageInputStream
     * @param stickerText
     *                    Texto do sticker
     * @return Retorna um ImageInputStream com o sticker
     */
    public ByteArrayInputStream makeSticker(InputStream inputStream, String textSticker) {

        try {
            BufferedImage originalImage = ImageIO.read(inputStream);
            originalHeight = originalImage.getHeight();
            originalWidth = originalImage.getWidth();
            newHeight = (int) (originalHeight * 1.2);
            this.stickerText = textSticker;
            BufferedImage imageSticker = drawSticker(originalImage);
            return writeImage(imageSticker);
        } catch (IOException e) {
            System.out.println("Não foi possível ler a imagem");
        }
        return null;

    }

    /**
     * Grava a imagem nova no disco
     * 
     * @param originalImage
     *                      Imagem original
     * 
     */
    private BufferedImage drawSticker(BufferedImage originalImage) {
        BufferedImage imageSticker = new BufferedImage(originalWidth, newHeight, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) imageSticker.getGraphics();
        g2d.drawImage(originalImage, 0, 0, null);
        drawStickerText(g2d);
        return imageSticker;
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
        int fontSize = (int) Math.floor(originalWidth * 0.07);
        Font font = new java.awt.Font("Comic Sans", java.awt.Font.BOLD, fontSize);
        while (fontSize > 0) {
            font = new java.awt.Font("Comic Sans", java.awt.Font.BOLD, fontSize);
            TextLayout textLayout = new TextLayout(stickerText, font, g2d.getFontRenderContext());
            Rectangle2D bounds = textLayout.getBounds();
            if (bounds.getWidth() < originalWidth) {
                break;
            }
            fontSize--;
        }

        g2d.setFont(font);

        // Calculando a posição do texto no sticker
        Rectangle2D fontBounds = font.getStringBounds(stickerText, g2d.getFontRenderContext());

        int StartDrawX = (originalWidth / 2) - (int) Math.floor(fontBounds.getWidth() / 2);
        int StartDrawY = (int) originalHeight + (increaseHeight / 2);

        TextLayout textLayout = new TextLayout(stickerText, g2d.getFont(), g2d.getFontRenderContext());
        Shape shape = textLayout.getOutline(null);
        g2d.setStroke(new BasicStroke(fontSize * 0.15f));
        g2d.translate(StartDrawX, StartDrawY);
        g2d.setColor(Color.BLACK);
        g2d.draw(shape);
        g2d.setColor(Color.YELLOW);
        g2d.fill(shape);

    }

    /**
     * Converte a imagem para um array de bytes
     *
     * @param imageSticker
     *                     Imagem nova
     * @return ImageInputStream
     *
     */
    private ByteArrayInputStream writeImage(BufferedImage imageSticker) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imageSticker, "png", baos);
            return new ByteArrayInputStream(baos.toByteArray());

        } catch (IOException e) {
            System.out.println("Não foi possível gerar o array de bytes" + e.getMessage());
        }
        return null;
    }

}
