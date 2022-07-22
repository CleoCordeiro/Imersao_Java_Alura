package br.com.cleo.AluraAPi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "languagens")
public class Language {
    @Id
    String id;

    String name;

    String description;

    Integer ranking;

    String image;

    public String getId() {
        return id;
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

    public void Update(Language updateValues) {
        if (updateValues.getName() != null) {
            this.setName(updateValues.getName());
        }
        if (updateValues.getDescription() != null) {
            this.setDescription(updateValues.getDescription());
        }
        if (updateValues.getRanking() != 0) {
            this.setRanking(updateValues.getRanking());
        }
        if (updateValues.getImage() != null) {
            this.setImage(updateValues.getImage());
        }
    }

    @Override
    public String toString() {
        return "Language [description=" + description + ", id=" + id + ", image=" + image + ", name=" + name
                + ", ranking=" + ranking + "]";
    }

}
