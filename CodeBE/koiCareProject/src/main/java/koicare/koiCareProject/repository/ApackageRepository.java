package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Apackage;
import koicare.koiCareProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApackageRepository extends JpaRepository<Apackage, UUID> {

    Apackage findApackageById(UUID id);

    List<Apackage> findApackageByRole(Role role);
}
