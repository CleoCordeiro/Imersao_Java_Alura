package br.com.cleo.AluraAPi.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Sticker {

    @Schema(example = "StickerBadass", description = "Nome do sticker")
    private String name;

    @Schema(example = "https://assets.pokemon.com/assets/cms2-pt-br/img/cards/web/SM12/SM12_PT-BR_214.png", description = "URL da imagem do sticker")
    private String imageUrl;

    @Schema(example = "BLASTOISEEEEE", description = "Easter Egg, Meme das antigas rsrs")
    private String textoSticker;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTextoSticker() {
        return textoSticker;
    }

}
