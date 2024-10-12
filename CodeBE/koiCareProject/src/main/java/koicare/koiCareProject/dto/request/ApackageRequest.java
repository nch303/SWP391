package koicare.koiCareProject.dto.request;

import jakarta.persistence.Entity;
import koicare.koiCareProject.entity.Role;
import lombok.Data;

@Data
public class ApackageRequest {
    String name;
    Role role;
    int duration;
    float price;
    String description;
    int numberOfPosts;
}
