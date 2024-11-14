package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class PackageNumberResponse {
    private int numberOfPackage;
    private String nameOfPackage;
    private int month;
    private int year;

    public PackageNumberResponse( String nameOfPackage, int numberOfPackage, int month, int year) {
        this.numberOfPackage = numberOfPackage;
        this.nameOfPackage = nameOfPackage;
        this.month = month;
        this.year = year;
    }
}
