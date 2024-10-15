package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.entity.WaterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WaterReportRepository extends JpaRepository<WaterReport, Long> {
    WaterReport getWaterReportByWaterReportId(long waterReportId);
    List<WaterReport> getWaterReportByPond(Pond pond);
    List<WaterReport> getWaterReportByWaterReportUpdatedDate(Date updatedDate);
}
