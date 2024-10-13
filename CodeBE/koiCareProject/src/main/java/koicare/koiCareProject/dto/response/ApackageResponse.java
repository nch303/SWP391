package koicare.koiCareProject.dto.response;

import koicare.koiCareProject.entity.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class ApackageResponse {

    private UUID id;
    private String name;
    private Role role;
    private int duration;
    private float price;
    private String description;
    private int numberOfPosts;
    private long accountID;
}
