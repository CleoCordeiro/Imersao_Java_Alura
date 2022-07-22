package br.com.cleo.AluraAPi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "languagens")
public class Language {

    @Id
    @Schema(example = "1", description = "Id da linguagem")
    String id;

    @Schema(example = "Java", description = "Nome da linguagem")
    String name;

    @Schema(example = "Linguagem do meu coração", description = "Descrição da linguagem")
    String description;

    @Schema(example = "1", description = "Posição da linguagem no ranking")
    Integer ranking;

    @Schema(example = "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_256x256.png")
    String image;

    public Language() {
    }

    public Language(String name, String description, Integer ranking, String image) {
        this.name = name;
        this.description = description;
        this.ranking = ranking;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
