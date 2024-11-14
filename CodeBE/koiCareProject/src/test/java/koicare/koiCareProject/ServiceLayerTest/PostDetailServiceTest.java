package koicare.koiCareProject.ServiceLayerTest;

import koicare.koiCareProject.dto.request.PostDetailRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.PostDetail;
import koicare.koiCareProject.entity.ProductType;
import koicare.koiCareProject.entity.Shop;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.PostDetailRepository;
import koicare.koiCareProject.repository.ProductTypeRepository;
import koicare.koiCareProject.repository.ShopRepository;
import koicare.koiCareProject.service.AdminService;
import koicare.koiCareProject.service.AuthenticationService;
import koicare.koiCareProject.service.EmailService;
import koicare.koiCareProject.service.PostDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostDetailServiceTest {

    @InjectMocks
    private PostDetailService postDetailService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private ShopRepository shopRepository;

    @Mock
    private PostDetailRepository postDetailRepository;

    @Mock
    private ProductTypeRepository productTypeRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private AdminService adminService;
    private PostDetail post1;

    @Test
    public void testCreatePostDetail() {
        PostDetailRequest request = new PostDetailRequest();
        request.setProductName("Sample Product");
        request.setDescription("Sample Description");
        request.setImage("sample_image.jpg");
        request.setLink("http://sample.com/product");
        request.setProductPrice(150.0);
        request.setProducTypeID(1L);

        Account mockAccount = new Account();
        Shop mockShop = new Shop();
        mockShop.setNumberOfPosts(1);
        mockShop.setExpiredDate(Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant())); // Hạn chưa hết

        ProductType mockProductType = new ProductType();

        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);
        when(shopRepository.getShopByAccount(mockAccount)).thenReturn(mockShop);
        when(productTypeRepository.findByProductTypeID(request.getProducTypeID())).thenReturn(mockProductType);
        when(postDetailRepository.save(any(PostDetail.class))).thenAnswer(i -> i.getArgument(0));

        PostDetail postDetail = postDetailService.createPostDetail(request);

        assertNotNull(postDetail);
        assertEquals("Sample Product", postDetail.getProductName());
        assertEquals("Sample Description", postDetail.getDescription());
        assertEquals(mockProductType, postDetail.getProductType());
        assertEquals(mockShop, postDetail.getShop());

        assertEquals(0, mockShop.getNumberOfPosts());
        verify(shopRepository, times(1)).save(mockShop);

    }

    @Test
    public void testCreatePostDetail_RunOutOfPosts() {
        PostDetailRequest request = new PostDetailRequest();
        request.setProductName("Sample Product");

        Account mockAccount = new Account();
        Shop mockShop = new Shop();
        mockShop.setNumberOfPosts(0);
        mockShop.setExpiredDate(Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant())); // Hạn chưa hết

        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);
        when(shopRepository.getShopByAccount(mockAccount)).thenReturn(mockShop);

        AppException exception = assertThrows(AppException.class, () -> {
            postDetailService.createPostDetail(request);
        });

        assertEquals(ErrorCode.RUN_OUT_POST, exception.getErrorCode());

    }

    @Test
    public void testGetAllPostDetails_NoExpiredDatePost() {
        List<PostDetail> postDetails = new ArrayList<>();
        PostDetail post1 = new PostDetail();
        post1.setExpiredDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        postDetails.add(post1);

        when(postDetailRepository.findAll()).thenReturn(postDetails);

        List<PostDetail> result = postDetailService.getAllPostDetails();

        assertEquals(1, result.size());
        verify(postDetailRepository, never()).delete(any(PostDetail.class));
        verify(emailService, never()).sendEmailForExpiredPost(any());
    }

    @Test
    public void testGetAllPostDetails_ExpiredDatePost() {
        List<PostDetail> postDetails = new ArrayList<>();
        PostDetail post1 = new PostDetail();
        post1.setExpiredDate(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        postDetails.add(post1);

        Account mockAccount = new Account();
        when(postDetailRepository.findAll()).thenReturn(postDetails);
        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);

        List<PostDetail> result = postDetailService.getAllPostDetails();

        assertEquals(0, result.size());
        verify(postDetailRepository, times(1)).delete(post1);

        verify(emailService, times(1)).sendEmailForExpiredPost(any());
    }


}
