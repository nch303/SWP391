package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.EmailDetail;
import koicare.koiCareProject.dto.request.MemberCreationRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Member;
import koicare.koiCareProject.entity.Shop;
import koicare.koiCareProject.repository.AccountRepository;
import koicare.koiCareProject.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmailService emailService;

    public Shop updateShop(MemberCreationRequest request){

        Account account = authenticationService.getCurrentAccount();
        Shop shop = shopRepository.getShopByAccount(account);
        shop.setName(request.getMemberName());
        shop.setPhone(request.getMemberPhone());
        shop.setEmail(request.getMemberEmail());
        account.setEmail(request.getMemberEmail());

        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setAccount(account);
        emailDetail.setSubject("You have changed your email!");
        emailDetail.setLink("http://103.90.227.68/shop");

        emailService.sendEmailUpdateShop(emailDetail);

        accountRepository.save(account);
        return shopRepository.save(shop);
    }
}
