package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.EmailDetail;
import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Shop;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.AccountRepository;
import koicare.koiCareProject.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Shop updateShop(MemberCreationRequest request) {

        Account account = authenticationService.getCurrentAccount();
        Shop shop = shopRepository.getShopByAccount(account);
        shop.setName(request.getName());
        shop.setPhone(request.getPhone());

        if(!shop.getEmail().equals(request.getEmail())){
            shop.setEmail(request.getEmail());
            account.setEmail(request.getEmail());

            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setAccount(account);
            emailDetail.setSubject("You have changed your email!");
            emailDetail.setLink("http://103.90.227.68/shop");

            emailService.sendEmailUpdateShop(emailDetail);
        }


        accountRepository.save(account);
        return shopRepository.save(shop);
    }

    public Shop getShopByID(long shopID){
        Shop shop = shopRepository.getShopByShopID(shopID);
        return shop;
    }
}
