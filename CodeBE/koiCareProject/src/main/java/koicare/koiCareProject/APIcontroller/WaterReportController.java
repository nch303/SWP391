package koicare.koiCareProject.APIcontroller;

import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.WaterReportResponse;
import koicare.koiCareProject.entity.WaterReport;
import koicare.koiCareProject.service.WaterReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

        WaterReportResponse waterReportResponse = modelMapper.map(waterReportService.createWaterReport(request), WaterReportResponse.class);
        waterReportResponse.setPondID(request.getPondID());

        response.setResult(waterReportResponse);
        return response;

    }

    @GetMapping("/{waterReportID}")
    public APIResponse<WaterReportResponse> getWaterReportById(@PathVariable("waterReportID") long waterReportID) {
        APIResponse<WaterReportResponse> response = new APIResponse<>();

        WaterReportResponse waterReportResponse = modelMapper.map(waterReportService.getWaterReportByID(waterReportID), WaterReportResponse.class);
        response.setResult(waterReportResponse);
        return response;
    }

    @DeleteMapping("/delete/{waterReportID}")
    public APIResponse deleteWaterReport(@PathVariable("waterReportID") long waterReportID) {
        APIResponse response = new APIResponse();
        waterReportService.deleteWaterReport(waterReportID);
        response.setResult("DELETED SUCCESSFULLY");
        return response;
    }

    @GetMapping("/view/{pondID}")
    public List<WaterReportResponse> getWaterReportByPondID(@PathVariable("pondID") long pondID) {
        List<WaterReport> waterReports = waterReportService.getAllWaterReportsByPondID(pondID);

        List<WaterReportResponse> responses = new ArrayList<>();
        waterReports.forEach(r -> responses.add(modelMapper.map(r, WaterReportResponse.class)));
        return responses;


    }
}
