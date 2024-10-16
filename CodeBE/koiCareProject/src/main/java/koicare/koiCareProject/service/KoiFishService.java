package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.KoiFishRequest;
import koicare.koiCareProject.dto.response.KoiReportResponse;
import koicare.koiCareProject.dto.response.PondResponse;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class KoiFishService {

    @Autowired
    private KoiFishRepository koiFishRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private KoiVarietyRepository koiVarietyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private KoiReportRepository koiReportRepository;

    @Autowired
    private PondService pondService;

    @Autowired
    private KoiReportService koiReportService;

    @Autowired
    private KoiStatusRepository koiStatusRepository;


    //tạo cá koi
    public KoiFish createKoiFish(KoiFishRequest request) throws ParseException {

        KoiFish koiFish = new KoiFish();

        Pond pond = pondRepository.getPondByPondID(request.getPondID());
        PondResponse pondResponse = modelMapper.map(pondService.getPondById(request.getPondID()), PondResponse.class);

        if (pondResponse != null) {
            koiFish.setKoiSex(request.getKoiSex());
            koiFish.setKoiName(request.getKoiName());
            koiFish.setImage(request.getImage());
            koiFish.setBirthday(request.getBirthday());
            koiFish.setAge(Math.round(koiReportService.dateBetween(request.getBirthday()) / 365.0f));
            koiFish.setKoiVariety(koiVarietyRepository.getKoiVarietyByKoiVarietyID(request.getKoiVarietyID()));
            koiFish.setPond(modelMapper.map(pondResponse, Pond.class));

            Account account = authenticationService.getCurrentAccount();
            Member member = memberRepository.getMemberByAccount(account);
            koiFish.setMember(member);

            //sau khi tạo cá sẽ tăng số lượng cá trong hồ lên 1
            //Pond pond = pondRepository.getPondByPondID(request.getPondID());
            pond.setAmountFish(pond.getAmountFish() + 1);
            pondRepository.save(pond);


            koiFishRepository.save(koiFish);

            //tao koiReport
            KoiReport koiReport = new KoiReport();
            koiReport.setLength(0);
            koiReport.setWeight(0);
            koiReport.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(11));
            koiReport.setKoiFish(koiFish);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date today = formatter.parse(formatter.format(new Date()));

            // Sử dụng Calendar để thêm giờ vào Date
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.set(Calendar.HOUR_OF_DAY, 7);  // Đặt giờ thành 7
            cal.set(Calendar.MINUTE, 0);       // Đặt phút thành 0
            cal.set(Calendar.SECOND, 0);       // Đặt giây thành 0
            cal.set(Calendar.MILLISECOND, 0);  // Đặt milli giây thành 0

            // Đặt lại giá trị ngày đã thêm giờ
            koiReport.setUpdateDate(cal.getTime());
            koiReportRepository.save(koiReport);


            return koiFish;
        } else {
            throw new AppException(ErrorCode.POND_NOT_EXISTED);
        }


    }

    //lấy lên danh sách cá koi theo MemberID
    public List<KoiFish> getKoiFishes() {

        Account account = authenticationService.getCurrentAccount();
        Member member = memberRepository.getMemberByAccount(account);
        List<KoiFish> koiFishList = koiFishRepository.findAllByMember(member);
        for (KoiFish koiFish : koiFishList) {
            koiFish.setAge(Math.round(koiReportService.dateBetween(koiFish.getBirthday()) / 365.0f));
            koiFishRepository.save(koiFish);
        }

        return koiFishList;
    }

    //lấy cá koi theo koiFishID
    public KoiFish getKoiFish(long koiFishID) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);
        if (koiFish != null)
            return koiFish;
        else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }

    //lấy cá koi theo pondID
    public List<KoiFish> getKoiFishWithPondID(long pondID) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        if (pond != null) {
            List<KoiFish> koiFishes = koiFishRepository.getAllByPond(pond);
            if (!koiFishes.isEmpty()) {
                return koiFishes;
            } else throw new AppException(ErrorCode.FISH_IS_NOT_EXISTED_IN_POND);
        } else throw new AppException(ErrorCode.POND_NOT_EXISTED);
    }

    //update cá koi
    public KoiFish updateKoiFish(long koiFishID, KoiFishRequest request) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);
        if (koiFish != null) {
            Pond oldPond = pondRepository.getPondByPondID(koiFish.getPond().getPondID());
            oldPond.setAmountFish(oldPond.getAmountFish() - 1);
            pondRepository.save(oldPond);

            koiFish = modelMapper.map(request, KoiFish.class);
            koiFish.setKoiFishID(koiFishID);

            Pond newPond = pondRepository.getPondByPondID(koiFish.getPond().getPondID());
            newPond.setAmountFish(newPond.getAmountFish() + 1);
            pondRepository.save(newPond);

            Account account = authenticationService.getCurrentAccount();
            Member member = memberRepository.getMemberByAccount(account);
            koiFish.setMember(member);


            return koiFishRepository.save(koiFish);
        } else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }

    //xóa cá khỏi danh sách
    public void deleteKoiFish(long koiFishID) {
        KoiFish koiFish = koiFishRepository.getKoiFishByKoiFishID(koiFishID);


        if (koiFish != null) {
            List<KoiReport> koiReports = koiReportRepository.getKoiReportsByKoiFish(koiFish);
            if (koiReports != null) {
                for (KoiReport koiReport : koiReports) {
                    koiReportRepository.delete(koiReport);
                }
            }
            Pond pond = pondRepository.getPondByPondID(koiFish.getPond().getPondID());
            pond.setAmountFish(pond.getAmountFish() - 1);
            koiFishRepository.deleteById(koiFishID);
        } else
            throw new AppException(ErrorCode.KOIFISH_NOT_EXISTED);
    }
}
