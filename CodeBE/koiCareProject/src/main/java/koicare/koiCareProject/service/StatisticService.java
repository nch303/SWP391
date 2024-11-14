package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.StatisticRequest;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiStandardRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

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

        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod
                (koiFish.getKoiVariety(), period);

        KoiStandard nextKoiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod
                (koiFish.getKoiVariety(), period + 100);

        if (daysBetween % 100 <= 31) {
            return koiStandard;
        } else if (daysBetween % 100 <= 61 && daysBetween % 100 > 31) {
            koiStandard.setHiWeightFemale(koiStandard.getHiWeightFemale()
                    + (nextKoiStandard.getHiWeightFemale() - koiStandard.getHiWeightFemale()) * 0.3333);
            koiStandard.setMedWeightFemale(koiStandard.getMedWeightFemale()
                    + (nextKoiStandard.getMedWeightFemale() - koiStandard.getMedWeightFemale()) * 0.3333);
            koiStandard.setLowWeightFemale(koiStandard.getLowWeightFemale()
                    + (nextKoiStandard.getLowWeightFemale() - koiStandard.getLowWeightFemale()) * 0.3333);

            koiStandard.setHiLengthFemale(koiStandard.getHiLengthFemale()
                    + (nextKoiStandard.getHiLengthFemale() - koiStandard.getHiLengthFemale()) * 0.3333);
            koiStandard.setMedLengthFemale(koiStandard.getMedLengthFemale()
                    + (nextKoiStandard.getMedLengthFemale() - koiStandard.getMedLengthFemale()) * 0.3333);
            koiStandard.setLowLengthFemale(koiStandard.getLowLengthFemale()
                    + (nextKoiStandard.getLowLengthFemale() - koiStandard.getLowLengthFemale()) * 0.3333);

            koiStandard.setHiWeightMale(koiStandard.getHiWeightMale()
                    + (nextKoiStandard.getHiWeightMale() - koiStandard.getHiWeightMale()) * 0.3333);
            koiStandard.setMedWeightMale(koiStandard.getMedWeightMale()
                    + (nextKoiStandard.getMedWeightMale() - koiStandard.getMedWeightMale()) * 0.3333);
            koiStandard.setLowWeightMale(koiStandard.getLowWeightMale()
                    + (nextKoiStandard.getLowWeightMale() - koiStandard.getLowWeightMale()) * 0.3333);

            koiStandard.setHiLengthMale(koiStandard.getHiLengthMale()
                    + (nextKoiStandard.getHiLengthMale() - koiStandard.getHiLengthMale()) * 0.3333);
            koiStandard.setMedLengthMale(koiStandard.getMedLengthMale()
                    + (nextKoiStandard.getMedLengthMale() - koiStandard.getMedLengthMale()) * 0.3333);
            koiStandard.setLowLengthMale(koiStandard.getLowLengthMale()
                    + (nextKoiStandard.getLowLengthMale() - koiStandard.getLowLengthMale()) * 0.3333);
        } else {
            koiStandard.setHiWeightFemale(koiStandard.getHiWeightFemale()
                    + (nextKoiStandard.getHiWeightFemale() - koiStandard.getHiWeightFemale()) * 0.6666);
            koiStandard.setMedWeightFemale(koiStandard.getMedWeightFemale()
                    + (nextKoiStandard.getMedWeightFemale() - koiStandard.getMedWeightFemale()) * 0.6666);
            koiStandard.setLowWeightFemale(koiStandard.getLowWeightFemale()
                    + (nextKoiStandard.getLowWeightFemale() - koiStandard.getLowWeightFemale()) * 0.6666);

            koiStandard.setHiLengthFemale(koiStandard.getHiLengthFemale()
                    + (nextKoiStandard.getHiLengthFemale() - koiStandard.getHiLengthFemale()) * 0.6666);
            koiStandard.setMedLengthFemale(koiStandard.getMedLengthFemale()
                    + (nextKoiStandard.getMedLengthFemale() - koiStandard.getMedLengthFemale()) * 0.6666);
            koiStandard.setLowLengthFemale(koiStandard.getLowLengthFemale()
                    + (nextKoiStandard.getLowLengthFemale() - koiStandard.getLowLengthFemale()) * 0.6666);

            koiStandard.setHiWeightMale(koiStandard.getHiWeightMale()
                    + (nextKoiStandard.getHiWeightMale() - koiStandard.getHiWeightMale()) * 0.6666);
            koiStandard.setMedWeightMale(koiStandard.getMedWeightMale()
                    + (nextKoiStandard.getMedWeightMale() - koiStandard.getMedWeightMale()) * 0.6666);
            koiStandard.setLowWeightMale(koiStandard.getLowWeightMale()
                    + (nextKoiStandard.getLowWeightMale() - koiStandard.getLowWeightMale()) * 0.6666);

            koiStandard.setHiLengthMale(koiStandard.getHiLengthMale()
                    + (nextKoiStandard.getHiLengthMale() - koiStandard.getHiLengthMale()) * 0.6666);
            koiStandard.setMedLengthMale(koiStandard.getMedLengthMale()
                    + (nextKoiStandard.getMedLengthMale() - koiStandard.getMedLengthMale()) * 0.6666);
            koiStandard.setLowLengthMale(koiStandard.getLowLengthMale()
                    + (nextKoiStandard.getLowLengthMale() - koiStandard.getLowLengthMale()) * 0.6666);
        }


        return koiStandard;
    }
}
