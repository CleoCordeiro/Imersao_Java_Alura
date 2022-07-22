package br.com.cleo.AluraAPi.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleo.AluraAPi.model.Sticker;
import br.com.cleo.AluraAPi.utils.MakeSticker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/stickers")
@Tag(name = "Stickers", description = "Crie Lindos Stickers Para Compartilhar Pelo Mundo")
public class GeradorSitcker {

        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
        @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
        @Operation(summary = "Gerador De Stickers", description = "Gera Um Sticker Com O Nome, Imagem e Texto", tags = {
                        "Stickers" }, method = "POST")
        @PostMapping
        public ResponseEntity<Resource> download(@RequestBody Sticker sticker)
                        throws IOException, URISyntaxException {
                String stickerName = sticker.getName() + ".png";
                MakeSticker makeSticker = new MakeSticker();
                ByteArrayInputStream stickerImage = makeSticker.makeSticker(
                                new URL(sticker.getImageUrl()).openStream(), sticker.getTextoSticker());
                Resource resource = new InputStreamResource(stickerImage);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename=\"" + stickerName +
                                "\"");

                return ResponseEntity.ok()
                                .headers(headers)
                                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                .body(resource);
        }

}
