package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.UpdateWaterReportRequest;
import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.entity.WaterReport;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.repository.WaterReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class WaterReportService {

    @Autowired
    private WaterReportRepository waterReportRepository;

    @Autowired
    private PondRepository pondRepository;


    public WaterReport createWaterReport(WaterReportRequest waterReportRequest) {

        WaterReport waterReport = new WaterReport();

        Pond pond = pondRepository.getPondByPondID(waterReportRequest.getPondID());
        if (pond != null) {
            //nếu trùng report sẽ báo lỗi
            Date date = waterReportRequest.getWaterReportUpdatedDate();
            WaterReport oldWaterReport = waterReportRepository.getWaterReportByWaterReportUpdatedDateAndPond(date, pond);
            if (oldWaterReport != null) {
                throw new AppException(ErrorCode.WATER_REPORT_EXISTED);
            } else {
                waterReport.setWaterReportUpdatedDate(waterReportRequest.getWaterReportUpdatedDate());
                waterReport.setWaterReportTemperature(waterReportRequest.getWaterReportTemperature());
                waterReport.setWaterReportSalt(waterReportRequest.getWaterReportSalt());
                waterReport.setWaterReportOxygen(waterReportRequest.getWaterReportOxygen());
                waterReport.setWaterReportNitrite(waterReportRequest.getWaterReportNitrite());
                waterReport.setWaterReportNitrate(waterReportRequest.getWaterReportNitrate());
                waterReport.setWaterReportHardness(waterReportRequest.getWaterReportHardness());
                waterReport.setWaterReportCarbonDioxide(waterReportRequest.getWaterReportCarbonDioxide());
                waterReport.setWaterReportCarbonate(waterReportRequest.getWaterReportCarbonate());
                waterReport.setWaterReportAmmonia(waterReportRequest.getWaterReportAmmonia());
                waterReport.setWaterReport_pH(waterReportRequest.getWaterReport_pH());
                waterReport.setPond(pond);
                return waterReportRepository.save(waterReport);
            }

        } else throw new AppException(ErrorCode.POND_NOT_EXISTED);


    }

    public WaterReport getWaterReportByID(Long waterReportID) {
        WaterReport waterReport = waterReportRepository.getWaterReportByWaterReportId(waterReportID);
        if (waterReport == null) {
            throw new AppException(ErrorCode.WATER_REPORT_NOT_EXISTED);
        } else {
            return waterReport;
        }

    }

    public void deleteWaterReport(Long waterReportId) {


        WaterReport waterReport = waterReportRepository.getWaterReportByWaterReportId(waterReportId);
        List<WaterReport> waterReports = waterReportRepository.getWaterReportByPond(waterReport.getPond());
        if(waterReports.size() == 1){
            waterReport.setWaterReportUpdatedDate(new Date());
            waterReport.setWaterReportTemperature(0);
            waterReport.setWaterReportSalt(0);
            waterReport.setWaterReportOxygen(0);
            waterReport.setWaterReportNitrite(0);
            waterReport.setWaterReportNitrate(0);
            waterReport.setWaterReportHardness(0);
            waterReport.setWaterReportCarbonDioxide(0);
            waterReport.setWaterReportCarbonate(0);
            waterReport.setWaterReportAmmonia(0);
            waterReport.setWaterReport_pH(0);
            waterReport.setPond(waterReport.getPond());
            waterReportRepository.save(waterReport);
        }
        else if (waterReport == null) {
            throw new AppException(ErrorCode.WATER_REPORT_NOT_EXISTED);
        } else {
            waterReportRepository.deleteById(waterReportId);
        }

    }

    //get all reports by pond

    public List<WaterReport> getAllWaterReportsByPondID(Long pondID) {
        List<WaterReport> waterReports = waterReportRepository.findAll();

        List<WaterReport> filteredWaterReports = new ArrayList<>();

        for (WaterReport waterReport : waterReports) {
            if (waterReport.getPond().getPondID() == pondID) {
                filteredWaterReports.add(waterReport);
            }
        }
        if (filteredWaterReports.isEmpty()) {
            throw new AppException(ErrorCode.LIST_NOT_EXISTED);
        } else return filteredWaterReports;
    }


    public WaterReport updaWaterReport(long waterReportID, WaterReportRequest waterReportRequest) {
        WaterReport waterReport = waterReportRepository.getWaterReportByWaterReportId(waterReportID);


        if (waterReport == null) {
            throw new AppException(ErrorCode.WATER_REPORT_NOT_EXISTED);
        } else {
            //nếu trùng report sẽ báo lỗi
            Date oldDate = waterReportRequest.getWaterReportUpdatedDate();
            WaterReport oldWaterReport = waterReportRepository.getWaterReportByWaterReportUpdatedDateAndPond(oldDate,waterReport.getPond());
            if (oldWaterReport != null) {
                throw new AppException(ErrorCode.WATER_REPORT_EXISTED);
            }

            waterReport.setWaterReportUpdatedDate(waterReport.getWaterReportUpdatedDate());
            waterReport.setWaterReportTemperature(waterReportRequest.getWaterReportTemperature());
            waterReport.setWaterReportSalt(waterReportRequest.getWaterReportSalt());
            waterReport.setWaterReportOxygen(waterReportRequest.getWaterReportOxygen());
            waterReport.setWaterReportNitrite(waterReportRequest.getWaterReportNitrite());
            waterReport.setWaterReportNitrate(waterReportRequest.getWaterReportNitrate());
            waterReport.setWaterReportHardness(waterReportRequest.getWaterReportHardness());
            waterReport.setWaterReportCarbonDioxide(waterReportRequest.getWaterReportCarbonDioxide());
            waterReport.setWaterReportCarbonate(waterReportRequest.getWaterReportCarbonate());
            waterReport.setWaterReportAmmonia(waterReportRequest.getWaterReportAmmonia());
            waterReport.setWaterReport_pH(waterReportRequest.getWaterReport_pH());

            return waterReportRepository.save(waterReport);

        }

    }

    public WaterReport updateLatestWaterReport(long pondID, UpdateWaterReportRequest request) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        List<WaterReport> waterReports = waterReportRepository.getWaterReportByPond(pond);
        if (waterReports.isEmpty()) {
            throw new AppException(ErrorCode.LIST_NOT_EXISTED);

        } else {

            WaterReport waterReport = waterReports.get(waterReports.size() - 1);

            waterReport.setWaterReportTemperature(request.getWaterReportTemperature());
            waterReport.setWaterReportSalt(request.getWaterReportSalt());
            waterReport.setWaterReportOxygen(request.getWaterReportOxygen());
            waterReport.setWaterReportNitrite(request.getWaterReportNitrite());
            waterReport.setWaterReportNitrate(request.getWaterReportNitrate());
            waterReport.setWaterReportHardness(request.getWaterReportHardness());
            waterReport.setWaterReportCarbonDioxide(request.getWaterReportCarbonDioxide());
            waterReport.setWaterReportCarbonate(request.getWaterReportCarbonate());
            waterReport.setWaterReportAmmonia(request.getWaterReportAmmonia());
            waterReport.setWaterReport_pH(request.getWaterReport_pH());
            return waterReportRepository.save(waterReport);
        }
    }

    public long dateBetween(Date date) {
        // Chuyển đổi từ Date sang LocalDate
        LocalDate dateFrom = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Date currentDate = new Date();
        LocalDate dateTo = currentDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Tính số ngày giữa hai ngày
        long daysBetween = ChronoUnit.DAYS.between(dateFrom, dateTo);
        return daysBetween;
    }

    public WaterReport getLatestWaterReport(long pondID) {
        Pond pond = pondRepository.getPondByPondID(pondID);
        List<WaterReport> waterReports = waterReportRepository.getWaterReportByPond(pond);

        long period = 0;
        long leastDate = Long.MAX_VALUE;

        if (waterReports.size() == 0) {
            throw new AppException(ErrorCode.WATER_REPORT_NOT_EXISTED);
        } else {
            WaterReport waterReportFinal = new WaterReport();
            for(WaterReport waterReport : waterReports){
                period = dateBetween(waterReport.getWaterReportUpdatedDate());
                if(period < leastDate){
                    waterReportFinal = waterReport;
                    leastDate = period;
                }
            }
            return waterReportFinal;
        }
    }
}
