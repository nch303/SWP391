package koicare.koiCareProject.APIcontroller;

import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.WaterReportResponse;
import koicare.koiCareProject.entity.WaterReport;
import koicare.koiCareProject.service.WaterReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("waterreport")
public class WaterReportController {
    @Autowired
    private WaterReportService waterReportService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public APIResponse<WaterReportResponse> createWaterReport(@RequestBody WaterReportRequest request) {

        APIResponse<WaterReportResponse> response = new APIResponse<>();

        WaterReportResponse waterReportResponse = modelMapper.map(request, WaterReportResponse.class);
        waterReportResponse.setPondID(request.getPondID());
        response.setResult(waterReportResponse);
        return response;

    }
}
