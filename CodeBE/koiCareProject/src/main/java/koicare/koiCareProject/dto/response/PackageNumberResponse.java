package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class PackageNumberResponse {
    private int numberOfPackage;
    private String nameOfPackage;

    public PackageNumberResponse(String nameOfPackage, int numberOfPackage) {
        this.nameOfPackage = nameOfPackage;
        this.numberOfPackage = numberOfPackage;
    }
}
