package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.KoiFish;
import koicare.koiCareProject.entity.KoiReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface KoiReportRepository extends JpaRepository<KoiReport, Long> {
    KoiReport getKoiReportByKoiReportID(long koiReportID);

    List<KoiReport> getKoiReportsByKoiFish(KoiFish koiFish);

    List<KoiReport> getKoiReportsByUpdateDate(Date updateDate);

}
