package koicare.koiCareProject.ServiceLayerTest;

import koicare.koiCareProject.dto.request.PondCreationRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Pond;
import koicare.koiCareProject.repository.MemberRepository;
import koicare.koiCareProject.repository.PondRepository;
import koicare.koiCareProject.repository.WaterReportRepository;
import koicare.koiCareProject.service.AuthenticationService;
import koicare.koiCareProject.service.PondService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import koicare.koiCareProject.exception.AuthException;


@ExtendWith(MockitoExtension.class)
public class PondServiceTest {

    @InjectMocks
    private PondService pondService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PondRepository pondRepository;

    @Mock
    private WaterReportRepository waterReportRepository;

    @Test
    public void testCreatePond_Success() throws ParseException {
        PondCreationRequest request = new PondCreationRequest();
        request.setPondName("Pond A");
        request.setPondImage("image_url");
        request.setArea(100);
        request.setDepth(10);
        request.setVolume(1000);
        request.setPumpingCapacity(500);
        request.setSkimmerCount(2);
        request.setDrainCount(1);

        Account mockAccount = authenticationService.getCurrentAccount();

        Member mockMember = memberRepository.getMemberByAccount(mockAccount);
        Pond savedPond = new Pond();
        savedPond.setPondID(1L);
        savedPond.setPondName(request.getPondName());
        savedPond.setPondImage(request.getPondImage());
        savedPond.setArea(request.getArea());
        savedPond.setDepth(request.getDepth());
        savedPond.setVolume(request.getVolume());
        savedPond.setPumpingCapacity(request.getPumpingCapacity());
        savedPond.setSkimmerCount(request.getSkimmerCount());
        savedPond.setDrainCount(request.getDrainCount());

        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);
        when(memberRepository.getMemberByAccount(mockAccount)).thenReturn(mockMember);
        when(pondRepository.save(any(Pond.class))).thenReturn(savedPond);
        when(pondRepository.getPondByPondID(savedPond.getPondID())).thenReturn(savedPond);

        Pond resultPond = pondService.createPond(request);

        assertNotNull(resultPond);
        assertEquals("Pond A", resultPond.getPondName());
        assertEquals(mockMember, resultPond.getMember());

    }

    @Test
    public void testCreatePond_Failure_UnauthenticatedUser() {
        when(authenticationService.getCurrentAccount()).thenThrow(new AuthException("User is not logged in"));

        PondCreationRequest request = new PondCreationRequest();
        request.setPondName("Pond A");
        request.setPondImage("image_url");
        request.setArea(100);
        request.setDepth(10);
        request.setVolume(1000);
        request.setPumpingCapacity(500);
        request.setSkimmerCount(2);
        request.setDrainCount(1);

        AuthException exception = assertThrows(AuthException.class, () -> {
            pondService.createPond(request);
        });

        assertEquals("User is not logged in", exception.getMessage());
    }

}

