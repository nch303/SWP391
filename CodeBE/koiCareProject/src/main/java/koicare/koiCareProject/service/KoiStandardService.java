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
