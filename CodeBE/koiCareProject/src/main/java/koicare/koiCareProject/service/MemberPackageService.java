package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.MemberPackageRequest;
import koicare.koiCareProject.entity.MemberPackage;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.MemberPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberPackageService {

    @Autowired
    private MemberPackageRepository memberPackageRepository;


    public MemberPackage createMemberPackage(MemberPackageRequest request) {
        MemberPackage memberPackage = new MemberPackage();
        memberPackage.setDuration(request.getDuration());
        memberPackage.setPrice(request.getPrice());

        return memberPackageRepository.save(memberPackage);
    }

    public List<MemberPackage> getAllMemberPackages() {
        return memberPackageRepository.findAll();
    }

    public MemberPackage updateMemberPackage(long packageID, MemberPackageRequest request) {
        MemberPackage memberPackage = memberPackageRepository.findByPackageID(packageID);
        if (memberPackage != null) {
            memberPackage.setDuration(request.getDuration());
            memberPackage.setPrice(request.getPrice());
            return memberPackageRepository.save(memberPackage);
        }
        else {
            throw new AppException(ErrorCode.MEMBER_PACKAGE_IS_NOT_EXISTED);
        }

    }

    public void deleteMemberPackage(long packageID) {
        MemberPackage memberPackage = memberPackageRepository.findByPackageID(packageID);
        if (memberPackage != null) {
            memberPackageRepository.delete(memberPackage);
        }
        else{
            throw new AppException(ErrorCode.MEMBER_PACKAGE_IS_NOT_EXISTED);
        }
    }
}
