package koicare.koiCareProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class PondReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pondReportID;
    private Date updatedDate;
    private double pondTemp;
    private double pond_pH;
    private double pondHardness;
    private double pondAmmonia;
    private double pondNitrite;
    private double pondNitrate;



}
