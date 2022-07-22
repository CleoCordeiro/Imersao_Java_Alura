package br.com.cleo.AluraAPi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleo.AluraAPi.form.LanguageForm;
import br.com.cleo.AluraAPi.model.Language;
import br.com.cleo.AluraAPi.repository.LanguageRepository;
import br.com.cleo.AluraAPi.utils.UpdateObjectValues;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/linguages")
@Tag(name = "Linguages De Programação", description = "Consulte, Cadastre, Edite ou Exclua Linguages De Programação")
public class LanguageController {

    @Autowired
    LanguageRepository languageRepository;

    UpdateObjectValues updateObjectValues = new UpdateObjectValues();

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @Operation(summary = "Listar Linguagens", description = "Lista Todas As Linguagens", method = "GET")
    @GetMapping
    public ResponseEntity<List<Language>> listaLinguagens() {
        return ResponseEntity.status(HttpStatus.OK).body(languageRepository.findAll(Sort.by("ranking")));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @Operation(summary = "Buscar Lingagem Por ID", description = "Busca uma Linguagem Por ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Language> buscaLinguagem(@PathVariable String id) {
        Optional<Language> langague = languageRepository.findById(id);
        if (langague.isPresent()) {
            return ResponseEntity.ok(langague.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @Operation(summary = "Cadastrar Lingagem", description = "Cadastra uma Linguagem", method = "POST")
    @PostMapping
    public ResponseEntity<Language> salvaLinguagem(@RequestBody LanguageForm languagefForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(languagefForm.toLanguage()));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @Operation(summary = "Atualizar Lingagem", description = "Atualiza uma Linguagem", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Language> atualizaLinguagem(@PathVariable String id,
            @RequestBody LanguageForm languagefForm) {
        Optional<Language> oldLanguage = languageRepository.findById(id);
        if (oldLanguage.isPresent()) {
            Language language = languagefForm.toLanguage();
            language.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(languageRepository.save(language));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(languagefForm.toLanguage()));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @Operation(summary = "Atualizar Parcialmente", description = "Atualiza Parcialmente Uma Linguagem", method = "PATCH")
    @PatchMapping("/{id}")
    public ResponseEntity<Language> atualizaLinguagemParcial(@PathVariable String id,
            @RequestBody LanguageForm languagefForm) {
        Optional<Language> oldLanguage = languageRepository.findById(id);
        if (oldLanguage.isPresent()) {
            updateObjectValues.updateObject(languagefForm, oldLanguage.get());
            return ResponseEntity.status(HttpStatus.OK).body(languageRepository.save(oldLanguage.get()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(languagefForm.toLanguage()));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @Operation(summary = "Deletar Lingagem", description = "Deleta uma Linguagem", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaLinguagem(@PathVariable String id) {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            languageRepository.delete(language.get());
            return ResponseEntity.status(HttpStatus.OK).body("Linguagem deletada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Linguagem não encontrada!");
    }

    // @PostMapping("/download")
    // public ResponseEntity<Resource> download(@RequestBody JsonNode image) throws
    // IOException, URISyntaxException {

    // String filename = Paths.get(new URI(image.get("url").asText()).getPath())
    // .getFileName().toString();

    // InputStreamResource resource = new InputStreamResource(new
    // URL(image.get("url").asText())
    // .openStream());

    // HttpHeaders headers = new HttpHeaders();
    // headers.add("Content-Disposition", "attachment; filename=\"" + filename +
    // "\"");

    // return ResponseEntity.ok()
    // .headers(headers)
    // .contentType(MediaType.APPLICATION_OCTET_STREAM)
    // .body(resource);
    // }

    // @GetMapping("/download/{url}")
    // public ResponseEntity<String> download1(@PathVariable String url) throws
    // IOException, URISyntaxException {
    // return ResponseEntity.ok().body(url);
    // String filename = Paths.get(new URI(image.get("url").asText()).getPath())
    // .getFileName().toString();

    // InputStreamResource resource = new InputStreamResource(new
    // URL(image.get("url").asText())
    // .openStream());

    // HttpHeaders headers = new HttpHeaders();
    // headers.add("Content-Disposition", "attachment; filename=\"" + filename +
    // "\"");

    // return ResponseEntity.ok()
    // .headers(headers)
    // .contentType(MediaType.APPLICATION_OCTET_STREAM)
    // .body(resource);
    // }
}
