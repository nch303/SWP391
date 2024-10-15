package koicare.koiCareProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FeedCoef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedCoefID;

    private int ageFrom;
    private int ageTo;
    private double low;
    private double medium;
    private double high;
}
