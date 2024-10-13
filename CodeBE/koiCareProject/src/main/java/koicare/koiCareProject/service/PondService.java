package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PondService {
    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private MemberRepository memberRepository;



    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private WaterReportRepository waterReportRepository;

    @Autowired
    private KoiFishRepository koiFishRepository;

    @Autowired
    private KoiReportService koiReportService;

    //tạo Pond
    public Pond createPond(PondCreationRequest request) {

        Pond pond = new Pond();

        pond.setPondName(request.getPondName());
        pond.setPondImage(request.getPondImage());
        pond.setArea(request.getArea());
        pond.setDepth(request.getDepth());
        pond.setVolume(request.getVolume());
        pond.setPumpingCapacity(request.getPumpingCapacity());
        pond.setSkimmerCount(request.getSkimmerCount());
        pond.setDrainCount(request.getDrainCount());


        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);

        pond.setMember(member);
        pond = pondRepository.save(pond);

        //tạo 1 waterReport tương ứng với hồ, nhưng giá trị bằng 0
        WaterReport waterReport = new WaterReport();
        waterReport.setWaterReportAmmonia(0);
        waterReport.setWaterReportCarbonDioxide(0);
        waterReport.setWaterReportCarbonate(0);
        waterReport.setWaterReportHardness(0);
        waterReport.setWaterReportNitrate(0);
        waterReport.setWaterReportNitrite(0);
        waterReport.setWaterReportOxygen(0);
        waterReport.setWaterReportSalt(0);
        waterReport.setWaterReportTemperature(0);
        waterReport.setWaterReport_pH(0);
        waterReport.setPond(pondRepository.getPondByPondID(pond.getPondID()));
        waterReportRepository.save(waterReport);

        return pond;
    }

    //Lấy danh sách Pond theo Member
    public List<Pond> getAllPonds() {

        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);

        return pondRepository.findAllByMember(member);
    }

    //lấy Pond theo ID
    public Pond getPondById(Long pondID) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        if (pond == null) {
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
        } else return pond;
    }


    //Update Pond
    public Pond updatePond(long pondID, PondCreationRequest request) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        if (pond != null) {
            pond.setPondName(request.getPondName());
            pond.setPondImage(request.getPondImage());
            pond.setArea(request.getArea());
            pond.setDepth(request.getDepth());
            pond.setVolume(request.getVolume());
            pond.setDrainCount(request.getDrainCount());
            pond.setSkimmerCount(request.getSkimmerCount());
            pond.setPumpingCapacity(request.getPumpingCapacity());

            Account account = authenticationService.getCurrentAccount();
            Member member = memberRepository.getMemberByAccount(account);
            pond.setMember(member);

            pond.setPondID(pondID);
            return pondRepository.save(pond);
        } else
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
    }

    //delete Pond
    public void deletePond(long pondID) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        List<WaterReport> waterReports = waterReportRepository.getWaterReportByPond(pond);

        if (pond == null) {
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
        }

        long amountFish = pond.getAmountFish();
        if (amountFish > 0) {
            throw new AppException(ErrorCode.FISH_IS_EXISTED_IN_POND);
        } else {

            for (WaterReport waterReport : waterReports) {
                waterReportRepository.delete(waterReport);
            }
            pondRepository.deleteById(pondID);
        }
    }


    //tính tổng khối lượng cá trong 1 hồ
    public double calculateTotalWeight(long pondID) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        List<KoiFish> koiFishes = koiFishRepository.getAllByPond(pond);
        double totalWeight = 0;
        for (KoiFish koiFish : koiFishes) {
            KoiReport koiReport = koiReportService.getLatestKoiReport(koiFish.getKoiFishID());
            totalWeight += koiReport.getWeight();
        }
        return totalWeight;
    }
}
