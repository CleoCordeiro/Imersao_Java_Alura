package br.com.cleo.AluraAPi.form;

import br.com.cleo.AluraAPi.model.Language;
import io.swagger.v3.oas.annotations.media.Schema;

public class LanguageForm {

    @Schema(example = "Java", description = "Nome da linguagem")
    String name;

    @Schema(example = "Linguagem do meu coração", description = "Descrição da linguagem")
    String description;

    @Schema(example = "1", description = "Posição da linguagem no ranking")
    Integer ranking;

    @Schema(example = "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_256x256.png")
    String image;

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

    public Language toLanguage() {
        return new Language(this.name, this.description, this.ranking, this.image);
    }

}
