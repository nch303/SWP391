package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.ApackageRequest;
import koicare.koiCareProject.dto.response.APIResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Apackage;
import koicare.koiCareProject.entity.Role;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.ApackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApackageService {

    @Autowired
    private ApackageRepository apackageRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ModelMapper modelMapper;

    public Apackage createApackage(ApackageRequest request) {
        Account account = authenticationService.getCurrentAccount();
        Apackage apackage = modelMapper.map(request, Apackage.class);
        apackage.setAccount(account);
        return apackageRepository.save(apackage);
    }

    public List<Apackage> getAllApackage() {
        return apackageRepository.findAll();
    }

    public List<Apackage> getAllMemberApackage() {
        return apackageRepository.findApackageByRole(Role.MEMBER);
    }

    public List<Apackage> getAllShopApackage() {
        return apackageRepository.findApackageByRole(Role.SHOP);
    }

    public Apackage getById(UUID uuid) {
        return apackageRepository.findApackageById(uuid);
    }

    public Apackage update(UUID uuid, ApackageRequest request) {
        Apackage apackage = apackageRepository.findApackageById(uuid);
        Account account = authenticationService.getCurrentAccount();
        if (apackage != null) {
            apackage = modelMapper.map(request, Apackage.class);
            apackage.setId(uuid);
            apackage.setAccount(account);

            return apackageRepository.save(apackage);
        } else throw new AppException(ErrorCode.PACKAGE_IS_NOT_EXISTED);
    }

    public void delete(UUID uuid){
        Apackage apackage = apackageRepository.findApackageById(uuid);
        if (apackage != null) {
            apackageRepository.delete(apackage);
        }else throw new AppException(ErrorCode.PACKAGE_IS_NOT_EXISTED);

    }
}
