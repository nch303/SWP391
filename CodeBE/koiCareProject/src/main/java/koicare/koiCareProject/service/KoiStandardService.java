package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiStandardByVarietyRequest;
import koicare.koiCareProject.dto.request.KoiStandardFullRequest;
import koicare.koiCareProject.dto.request.KoiStandardRequest;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiStandard;
import koicare.koiCareProject.entity.KoiVariety;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.KoiFishRepository;
import koicare.koiCareProject.repository.KoiStandardRepository;
import koicare.koiCareProject.repository.KoiVarietyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class KoiStandardService {

    @Autowired
    private KoiStandardRepository koiStandardRepository;

    @Autowired
    private KoiVarietyRepository koiVarietyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    KoiFishRepository koiFishRepository;


    // Lấy KoiStandard theo VarietyID và Period
    public KoiStandard getKoiStandardByVarietyAndPeriod(KoiStandardRequest request) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID());
        KoiVariety koiVariety = koiFish.getKoiVariety();


        Date koiBirthdayDate = koiFish.getBirthday();
        Date updateDateDate = request.getDate();

        // Chuyển đổi từ Date sang LocalDate
        LocalDate koiBirthday = koiBirthdayDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate updateDate = updateDateDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Tính số ngày giữa hai ngày
        long daysBetween = ChronoUnit.DAYS.between(koiBirthday, updateDate);

        long period = ((daysBetween / 100) * 100);

        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod(koiVariety, period);
        if (koiStandard != null) {
            KoiStandard nextKoiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod
                    (koiFishRepository.getKoiFishByKoiFishID(request.getKoiFishID()).getKoiVariety(), period + 100);

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

            koiStandard.setLowWeightMale(Math.round(koiStandard.getLowWeightMale() * 100.0) / 100.0);
            koiStandard.setHiWeightMale(Math.round(koiStandard.getHiWeightMale() * 100.0) / 100.0);
            koiStandard.setLowWeightFemale(Math.round(koiStandard.getLowWeightFemale() * 100.0) / 100.0);
            koiStandard.setHiWeightFemale(Math.round(koiStandard.getHiWeightFemale() * 100.0) / 100.0);

            koiStandard.setLowLengthMale(Math.round(koiStandard.getLowLengthMale() * 100.0) / 100.0);
            koiStandard.setHiLengthMale(Math.round(koiStandard.getHiLengthMale() * 100.0) / 100.0);
            koiStandard.setLowLengthFemale(Math.round(koiStandard.getLowLengthFemale() * 100.0) / 100.0);
            koiStandard.setHiLengthFemale(Math.round(koiStandard.getHiLengthFemale() * 100.0) / 100.0);


            return koiStandard;
        } else throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

    // Lấy KoiStandard theo VarietyID
    public KoiStandard getKoiStandardByVariety(KoiStandardByVarietyRequest request) {
        KoiVariety koiVariety = koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getVarietyID());


        Date koiBirthdayDate = request.getKoiBirthday();
        Date updateDateDate = new Date();

        // Chuyển đổi từ Date sang LocalDate
        LocalDate koiBirthday = koiBirthdayDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate updateDate = updateDateDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Tính số ngày giữa hai ngày
        long daysBetween = ChronoUnit.DAYS.between(koiBirthday, updateDate);

        long period = ((daysBetween / 100) * 100);

        KoiStandard koiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod(koiVariety, period);
        if (koiStandard != null) {
            KoiStandard nextKoiStandard = koiStandardRepository.getKoiStandardByKoiVarietyAndPeriod
                    (koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getVarietyID()), period + 100);

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

            koiStandard.setLowWeightMale(Math.round(koiStandard.getLowWeightMale() * 100.0) / 100.0);
            koiStandard.setHiWeightMale(Math.round(koiStandard.getHiWeightMale() * 100.0) / 100.0);
            koiStandard.setLowWeightFemale(Math.round(koiStandard.getLowWeightFemale() * 100.0) / 100.0);
            koiStandard.setHiWeightFemale(Math.round(koiStandard.getHiWeightFemale() * 100.0) / 100.0);

            koiStandard.setLowLengthMale(Math.round(koiStandard.getLowLengthMale() * 100.0) / 100.0);
            koiStandard.setHiLengthMale(Math.round(koiStandard.getHiLengthMale() * 100.0) / 100.0);
            koiStandard.setLowLengthFemale(Math.round(koiStandard.getLowLengthFemale() * 100.0) / 100.0);
            koiStandard.setHiLengthFemale(Math.round(koiStandard.getHiLengthFemale() * 100.0) / 100.0);

            return koiStandard;
        } else throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

    //create koiStandard
    public KoiStandard createKoiStandard(KoiStandardFullRequest request){
        KoiStandard koiStandard = modelMapper.map(request,KoiStandard.class);
        return koiStandardRepository.save(koiStandard);
    }

    // Lấy all KoiStandard
    public List<KoiStandard> getAllKoiStandard() {
        List<KoiStandard> koiStandards = koiStandardRepository.findAll();
        return koiStandards;
    }

    //lấy KoiStandard theo ID
    public KoiStandard getKoiStandardByID(long koiStandardID) {
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardBykoiStandID(koiStandardID);
        if (koiStandard != null) {
            return koiStandard;
        } else throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

    //update KoiStandard
    public KoiStandard updateKoiStandard(KoiStandardFullRequest request, long koiStandardID) {
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardBykoiStandID(koiStandardID);
        if (koiStandard != null) {
            koiStandard = modelMapper.map(request, KoiStandard.class);
            koiStandard.setKoiStandID(koiStandardID);
            return koiStandardRepository.save(koiStandard);
        } else throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }

    //deleteKoiStandard
    public void deleteKoiStandard(long koiStandardID){
        KoiStandard koiStandard = koiStandardRepository.getKoiStandardBykoiStandID(koiStandardID);
        if (koiStandard != null) {
            koiStandardRepository.delete(koiStandard);
        } else throw new AppException(ErrorCode.KOISTANDARD_NOT_EXISTED);
    }


}
