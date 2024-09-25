package koicare.koiCareProject.APIcontroller;

import koicare.koiCareProject.dto.request.KoiReportRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiReportResponse;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.service.KoiReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("koireport")
public class KoiReportController {

    @Autowired
    KoiReportService koiReportService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("create")
    public APIResponse<KoiReportResponse> createKoiReport(@RequestBody KoiReportRequest request){
        APIResponse<KoiReportResponse> response = new APIResponse<>();

        KoiReport koiReport = koiReportService.createKoiReport(request);
        KoiReportResponse koiReportResponse = modelMapper.map(koiReport,KoiReportResponse.class);

        response.setResult(koiReportResponse);
        System.out.println("bao cao duọc tao thanh cong");
        return response;
    }
}
