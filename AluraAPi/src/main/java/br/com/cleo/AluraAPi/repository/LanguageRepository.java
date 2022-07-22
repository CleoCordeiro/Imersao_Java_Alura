package br.com.cleo.AluraAPi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cleo.AluraAPi.model.Language;

public interface LanguageRepository extends MongoRepository<Language, String> {

}
