package koicare.koiCareProject.dto.request;

import jakarta.persistence.Entity;
import koicare.koiCareProject.entity.Role;
import lombok.Data;

@Data
public class ApackageRequest {
    private String name;
    private Role role;
    private int duration;
    private float price;
    private String description;
    private int numberOfPosts;
}
