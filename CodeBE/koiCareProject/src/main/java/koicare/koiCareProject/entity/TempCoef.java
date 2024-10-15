package koicare.koiCareProject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TempCoef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tempID;

    private int tempFrom;
    private int tempTo;
    private double coef;

}
