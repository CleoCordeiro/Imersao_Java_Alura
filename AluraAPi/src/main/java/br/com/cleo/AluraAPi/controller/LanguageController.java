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
import org.springframework.web.bind.annotation.RestController;

import br.com.cleo.AluraAPi.model.Language;
import br.com.cleo.AluraAPi.repository.LanguageRepository;
import br.com.cleo.AluraAPi.utils.UpdateObjectValues;

@RestController
public class LanguageController {

    @Autowired
    LanguageRepository languageRepository;

    UpdateObjectValues updateObjectValues = new UpdateObjectValues();

    @GetMapping("/linguagens")
    public ResponseEntity<List<Language>> listaLinguagens() {
        return ResponseEntity.status(HttpStatus.OK).body(languageRepository.findAll(Sort.by("ranking")));
    }

    @GetMapping("/linguagens/{id}")
    public ResponseEntity<Language> buscaLinguagem(@PathVariable String id) {
        Optional<Language> langague = languageRepository.findById(id);
        if (langague.isPresent()) {
            return ResponseEntity.ok(langague.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Language> salvaLinguagem(@RequestBody Language language) {
        return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(language));
    }

    @PutMapping("/linguagens/{id}")
    public ResponseEntity<Language> atualizaLinguagem(@PathVariable String id, @RequestBody Language newLanguage) {
        Optional<Language> oldLanguage = languageRepository.findById(id);
        if (oldLanguage.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(languageRepository.save(newLanguage));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(newLanguage));
    }

    @PatchMapping("/linguagens/{id}")
    public ResponseEntity<Language> atualizaLinguagemParcial(@PathVariable String id,
            @RequestBody Language newLanguage) {
        Optional<Language> oldLanguage = languageRepository.findById(id);
        if (oldLanguage.isPresent()) {
            updateObjectValues.updateObject(newLanguage, oldLanguage.get());
            return ResponseEntity.status(HttpStatus.OK).body(languageRepository.save(oldLanguage.get()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(newLanguage));
    }

    @DeleteMapping("/linguagens/{id}")
    public ResponseEntity<String> deletaLinguagem(@PathVariable String id) {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            languageRepository.delete(language.get());
            return ResponseEntity.status(HttpStatus.OK).body("Linguagem deletada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Linguagem não encontrada!");
    }

}
