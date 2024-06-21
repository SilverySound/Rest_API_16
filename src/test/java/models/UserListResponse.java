package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnore;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;
    @JsonIgnore
    private Support support; // Изменено на объект Support
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Support {
    private String url;
    private String text;
}
