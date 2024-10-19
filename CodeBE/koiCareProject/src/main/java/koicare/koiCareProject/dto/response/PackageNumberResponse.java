package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class PackageNumberResponse {
    private int month;
    private int year;
    private int numberOfPackage;
    private String nameOfPackage;
}
