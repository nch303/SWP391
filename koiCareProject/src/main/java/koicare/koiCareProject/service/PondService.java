package koicare.koiCareProject.service;

import koicare.koiCareProject.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PondService {
    @Autowired
    private PondRepository pondRepository;
}
