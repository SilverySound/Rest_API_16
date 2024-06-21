package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String job;

    @JsonProperty("per_page")
    private int perPage;

    // другие поля, геттеры и сеттеры

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }
}


