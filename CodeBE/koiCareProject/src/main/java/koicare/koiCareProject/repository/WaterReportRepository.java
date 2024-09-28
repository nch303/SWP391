package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.WaterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterReportRepository extends JpaRepository<WaterReport, Long> {
    WaterReport getWaterReportByWaterReportId(long waterReportId);
}
