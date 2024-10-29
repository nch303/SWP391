package koicare.koiCareProject.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BlogRequest {
    private String title;
    private String content;
    private String author;
    private String mainImage;
}
