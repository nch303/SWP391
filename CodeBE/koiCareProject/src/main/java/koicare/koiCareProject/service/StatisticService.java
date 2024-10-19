package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.StatisticRequest;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiStandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class StatisticService {

    @Autowired
    KoiStandardRepository koiStandardRepository;

    @Autowired
    KoiFishRepository koiFishRepository;

    public KoiStandard getKoiStandardByDate(StatisticRequest request) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID());

        Date birthday = koiFish.getBirthday();
        // Chuyển đổi từ Date sang LocalDate
        LocalDate koiBirthday = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Date date = request.getDate();
        // Chuyển đổi từ Date sang LocalDate
        LocalDate requestDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Tính số ngày giữa hai ngày
        long daysBetween = ChronoUnit.DAYS.between(koiBirthday, requestDate);
        long period = (daysBetween / 100) * 100;

        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod(koiFish.getKoiVariety(), period);
        return koiStandard;
    }
}
