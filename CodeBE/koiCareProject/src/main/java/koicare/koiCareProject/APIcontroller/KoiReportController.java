package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import koicare.koiCareProject.dto.request.KoiReportRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiReportResponse;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.entity.KoiStatus;
import koicare.koiCareProject.repository.KoiStatusRepository;
import koicare.koiCareProject.service.KoiReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/koireport")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiReportController {

    @Autowired
    KoiReportService koiReportService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    KoiStatusRepository koiStatusRepository;

    //tạo KoiReport
    @PostMapping("create")
    public ResponseEntity createKoiReport(@Valid @RequestBody KoiReportRequest request) {
        APIResponse<KoiReportResponse> response = new APIResponse<>();

        KoiReport koiReport = koiReportService.createKoiReport(request);
        KoiReportResponse koiReportResponse = modelMapper.map(koiReport, KoiReportResponse.class);

        response.setResult(koiReportResponse);

        return ResponseEntity.ok(response);
    }

    //lấy danh sách KoiReport theo KoiFishID từ DB
    @GetMapping("koiReports/{koiFishID}")
    public ResponseEntity getKoiReports(@PathVariable("koiFishID")long koiFishID) {
        APIResponse<List<KoiReportResponse>> response = new APIResponse<>();

        List<KoiReport> koiReports = koiReportService.getKoiReports(koiFishID);

        List<KoiReportResponse> koiReportResponses = koiReports.stream()
                .map(koiReport -> modelMapper.map(koiReport, KoiReportResponse.class))
                .collect(Collectors.toList());

        for(KoiReportResponse koiReportResponse:koiReportResponses){
            koiReportResponse.setKoiStatus(koiStatusRepository.getKoiStatusByKoiStatusID(koiReportResponse.getKoiStatusID()).getStatusContent());
        }
        
        response.setResult(koiReportResponses);
        return ResponseEntity.ok(response);
    }

    //lấy KoiReport mới nhất của 1 koiFishID
    @GetMapping("latestKoiReport/{koiFishID}")
    public ResponseEntity getLatestKoiReport(@PathVariable("koiFishID")long koiFishID) {
        APIResponse<KoiReportResponse> response = new APIResponse<>();

        KoiReport latestKoiReport = koiReportService.getLatestKoiReport(koiFishID);

        response.setResult(modelMapper.map(latestKoiReport, KoiReportResponse.class));
        return ResponseEntity.ok(response);
    }

    //lấy KoiReport theo KoiReportID
    @GetMapping("{koiReportID}")
    public ResponseEntity getKoiReport(@PathVariable("koiReportID") long koiReportID) {
        APIResponse<KoiReportResponse> response = new APIResponse<>();

        response.setResult(modelMapper.map(koiReportService.getKoiReport(koiReportID), KoiReportResponse.class));

        return ResponseEntity.ok(response);
    }


    //update KoiReport
    @PutMapping("{koiReportID}")
    public ResponseEntity updateKoiReport(@Valid @PathVariable("koiReportID") long koiReportID
                                                        , @RequestBody KoiReportRequest request) {
        APIResponse<KoiReportResponse> response = new APIResponse<>();

        response.setResult(modelMapper.map(koiReportService.updateKoiReport(koiReportID,request), KoiReportResponse.class));

        return ResponseEntity.ok(response);
    }

    //Xóa KoiReport khỏi danh sách
    @DeleteMapping("{koiReportID}")
    public ResponseEntity deleteKoiReport(@PathVariable("koiReportID") long koiReportID) {
        koiReportService.deleteKoiReport(koiReportID);
        return ResponseEntity.ok("Detele koiReport " + koiReportID + " successfully!!!") ;
    }
}
