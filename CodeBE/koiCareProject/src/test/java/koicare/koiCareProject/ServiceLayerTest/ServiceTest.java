package koicare.koiCareProject.ServiceLayerTest;

import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.dto.request.WaterReportRequest;
import koicare.koiCareProject.dto.request.UpdateWaterReportRequest;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import koicare.koiCareProject.service.AuthenticationService;
import koicare.koiCareProject.service.PondService;
import koicare.koiCareProject.service.WaterReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @Mock
    private PondRepository pondRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private WaterReportRepository waterReportRepository;
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private PondService pondService;
    @InjectMocks
    private WaterReportService waterReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePond() {
        PondCreationRequest request = new PondCreationRequest();
        request.setPondName("Test Pond");
        
        Account account = new Account();
        Member member = new Member();
        Pond pond = new Pond();
        pond.setPondID(1L);

        when(authenticationService.getCurrentAccount()).thenReturn(account);
        when(memberRepository.getMemberByAccount(account)).thenReturn(member);
        when(pondRepository.save(any(Pond.class))).thenReturn(pond);

        Pond result = pondService.createPond(request);

        assertNotNull(result);
        assertEquals(1L, result.getPondID());
        verify(pondRepository).save(any(Pond.class));
    }

    @Test
    void testGetAllPonds() {
        Account account = new Account();
        Member member = new Member();
        List<Pond> ponds = Arrays.asList(new Pond(), new Pond());

        when(authenticationService.getCurrentAccount()).thenReturn(account);
        when(memberRepository.getMemberByAccount(account)).thenReturn(member);
        when(pondRepository.findAllByMember(member)).thenReturn(ponds);

        List<Pond> result = pondService.getAllPonds();

        assertEquals(2, result.size());
        verify(pondRepository).findAllByMember(member);
    }

    @Test
    void testGetPondByIdNotFound() {
        Long pondId = 1L;
        when(pondRepository.getPondByPondID(pondId)).thenReturn(null);

        assertThrows(AppException.class, () -> pondService.getPondById(pondId));
    }

    @Test
    void testUpdatePondNotFound() {
        Long pondId = 1L;
        PondCreationRequest request = new PondCreationRequest();
        when(pondRepository.getPondByPondID(pondId)).thenReturn(null);

        assertThrows(AppException.class, () -> pondService.updatePond(pondId, request));
    }

    @Test
    void testDeletePondWithFish() {
        Long pondId = 1L;
        Pond pond = new Pond();
        pond.setAmountFish(5);
        when(pondRepository.getPondByPondID(pondId)).thenReturn(pond);

        assertThrows(AppException.class, () -> pondService.deletePond(pondId));
    }

    @Test
    void testCreateWaterReport() {
        WaterReportRequest request = new WaterReportRequest();
        request.setPondID(1L);
        Pond pond = new Pond();
        when(pondRepository.getPondByPondID(1L)).thenReturn(pond);
        when(waterReportRepository.save(any(WaterReport.class))).thenAnswer(i -> i.getArguments()[0]);

        WaterReport result = waterReportService.createWaterReport(request);

        assertNotNull(result);
        assertEquals(pond, result.getPond());
    }

    @Test
    void testGetWaterReportByIDNotFound() {
        Long reportId = 1L;
        when(waterReportRepository.getWaterReportByWaterReportId(reportId)).thenReturn(null);

        assertThrows(AppException.class, () -> waterReportService.getWaterReportByID(reportId));
    }

    @Test
    void testDeleteWaterReport() {
        Long reportId = 1L;
        WaterReport waterReport = new WaterReport();
        when(waterReportRepository.getWaterReportByWaterReportId(reportId)).thenReturn(waterReport);

        waterReportService.deleteWaterReport(reportId);

        verify(waterReportRepository).deleteById(reportId);
    }

    @Test
    void testGetAllWaterReportsByPondIDEmpty() {
        Long pondId = 1L;
        when(waterReportRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(AppException.class, () -> waterReportService.getAllWaterReportsByPondID(pondId));
    }

    @Test
    void testUpdateLatestWaterReportEmptyList() {
        Long pondId = 1L;
        Pond pond = new Pond();
        UpdateWaterReportRequest request = new UpdateWaterReportRequest();
        
        when(pondRepository.getPondByPondID(pondId)).thenReturn(pond);
        when(waterReportRepository.getWaterReportByPond(pond)).thenReturn(Arrays.asList());

        assertThrows(AppException.class, () -> waterReportService.updateLatestWaterReport(pondId, request));
    }
}
