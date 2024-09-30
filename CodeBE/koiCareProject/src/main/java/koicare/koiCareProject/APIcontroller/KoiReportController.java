package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.KoiReportRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiReportResponse;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.service.KoiReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("koireport")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class KoiReportController {

    @Autowired
    KoiReportService koiReportService;

    @Autowired
    ModelMapper modelMapper;

    //tạo KoiReport
    @PostMapping("create")
    public APIResponse<KoiReportResponse> createKoiReport(@RequestBody KoiReportRequest request) {
        APIResponse<KoiReportResponse> response = new APIResponse<>();

        KoiReport koiReport = koiReportService.createKoiReport(request);
        KoiReportResponse koiReportResponse = modelMapper.map(koiReport, KoiReportResponse.class);

        response.setResult(koiReportResponse);

        return response;
    }

    //lấy danh sách KoiReport từ DB
    @GetMapping("")
    public APIResponse<List<KoiReportResponse>> getKoiReports() {
        APIResponse<List<KoiReportResponse>> response = new APIResponse<>();

        List<KoiReport> koiReports = koiReportService.getKoiReports();
        List<KoiReportResponse> koiReportResponses = koiReports.stream()
                .map(koiReport -> modelMapper.map(koiReport, KoiReportResponse.class))
                .collect(Collectors.toList());

        response.setResult(koiReportResponses);
        return response;
    }

    //lấy KoiReport theo ID
    @GetMapping("{koiReportID}")
    public APIResponse<KoiReportResponse> getKoiReport(@PathVariable("koiReportID") long koiReportID) {
        APIResponse<KoiReportResponse> response = new APIResponse<>();

        response.setResult(modelMapper.map(koiReportService.getKoiReport(koiReportID), KoiReportResponse.class));

        return response;
    }

    //Xóa KoiReport khỏi danh sách
    @DeleteMapping("{koiReportID}")
    public String deleteKoiReport(@PathVariable("koiReportID") long koiReportID) {
        koiReportService.deleteKoiReport(koiReportID);
        return "Detele koiReport " + koiReportID + " successfully!!!";
    }
}
