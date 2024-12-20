package koicare.koiCareProject.repository;


import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //lấy account bằng username
    Account findAccountByUsername(String username);
    Account findAccountByAccountID(long accountID);
    Account findAccountByEmail(String email);
    Account findAccountByRole(Role role);
}
