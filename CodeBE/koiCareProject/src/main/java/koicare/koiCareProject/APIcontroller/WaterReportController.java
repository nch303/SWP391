package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.UpdateWaterReportRequest;
import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.dto.response.KoiReportResponse;
import koicare.koiCareProject.dto.response.WaterReportResponse;
import koicare.koiCareProject.entity.KoiReport;
import koicare.koiCareProject.entity.WaterReport;
import koicare.koiCareProject.service.WaterReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/waterreport")
//dán qua các controller thì mới xài được token
@SecurityRequirement(name = "api")
public class WaterReportController {
    @Autowired
    private WaterReportService waterReportService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity createWaterReport(@RequestBody WaterReportRequest request) {

        APIResponse<WaterReportResponse> response = new APIResponse<>();

        WaterReportResponse waterReportResponse = modelMapper.map(waterReportService.createWaterReport(request), WaterReportResponse.class);
        waterReportResponse.setPondID(request.getPondID());

        response.setResult(waterReportResponse);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{waterReportID}")
    public ResponseEntity getWaterReportById(@PathVariable("waterReportID") long waterReportID) {
        APIResponse<WaterReportResponse> response = new APIResponse<>();

        WaterReportResponse waterReportResponse = modelMapper.map(waterReportService.getWaterReportByID(waterReportID), WaterReportResponse.class);
        response.setResult(waterReportResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{waterReportID}")
    public ResponseEntity deleteWaterReport(@PathVariable("waterReportID") long waterReportID) throws ParseException {
        APIResponse response = new APIResponse();
        waterReportService.deleteWaterReport(waterReportID);
        response.setResult("DELETED SUCCESSFULLY");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view/{pondID}")
    public ResponseEntity getWaterReportByPondID(@PathVariable("pondID") long pondID) {
        List<WaterReport> waterReports = waterReportService.getAllWaterReportsByPondID(pondID);

        List<WaterReportResponse> responses = new ArrayList<>();
        waterReports.forEach(r -> responses.add(modelMapper.map(r, WaterReportResponse.class)));
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{waterReportID}")
    public ResponseEntity updateWaterReport(@PathVariable("waterReportID") long waterReportID, @RequestBody WaterReportRequest request) {
        APIResponse<WaterReportResponse> response = new APIResponse<>();
        WaterReportResponse waterReportResponse =
                modelMapper.map(waterReportService.updateWaterReport(waterReportID, request), WaterReportResponse.class);

        response.setResult(waterReportResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/latestreport/{pondID}")
    public ResponseEntity updateLatestWaterReport(@PathVariable("pondID") long pondID, @RequestBody UpdateWaterReportRequest request) {
        APIResponse<WaterReportResponse> response = new APIResponse<>();
        WaterReportResponse waterReportResponse =
                modelMapper.map(waterReportService.updateLatestWaterReport(pondID, request), WaterReportResponse.class);
        waterReportResponse.setPondID(pondID);

        response.setResult(waterReportResponse);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/view/latestreport/{pondID}")
    public ResponseEntity getLatestWaterReportByPondID(@PathVariable("pondID") long pondID) {
        APIResponse<WaterReportResponse> response = new APIResponse<>();
        WaterReport latestWaterReport = waterReportService.getLatestWaterReport(pondID);
        response.setResult(modelMapper.map(latestWaterReport, WaterReportResponse.class));
        return ResponseEntity.ok(response);
    }
}
