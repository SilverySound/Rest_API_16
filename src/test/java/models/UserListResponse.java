package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;
}
