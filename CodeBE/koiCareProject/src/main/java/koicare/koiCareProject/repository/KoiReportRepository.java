package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiReport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KoiReportRepository extends JpaRepository<KoiReport, Long> {
    KoiReport getKoiReportByKoiReportID(long koiReportID);


}
