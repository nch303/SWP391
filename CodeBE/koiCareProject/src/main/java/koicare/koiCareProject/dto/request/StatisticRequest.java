package koicare.koiCareProject.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class StatisticRequest {
    Date date;
    long koiFishID;
}
