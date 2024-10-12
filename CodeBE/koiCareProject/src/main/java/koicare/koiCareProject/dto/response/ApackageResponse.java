package koicare.koiCareProject.dto.response;

import koicare.koiCareProject.entity.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class ApackageResponse {

    UUID id;
    String name;
    Role role;
    int duration;
    float price;
    String description;
    int numberOfPosts;
    long accountID;
}
