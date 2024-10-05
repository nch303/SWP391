package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Account, Long> {
    Account findByUsernameAndPassword(String username, String password);

    Account findByUsername(String username);

    Account findAccountByAccountID(long accountID);
}
