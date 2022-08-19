package org.acme.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;

import javax.persistence.Entity;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Joke extends PanacheEntity {

    public String content;
    public String language;
    public String country;
    public String category;

    @JsonIgnore
    public boolean merged;
    public int update(String content, long id, String language) {
        return update("content= :content, merged=true where language= :language and id= :id",
                Parameters.with("content", content).and("language", language).and("id", id));
    }
}