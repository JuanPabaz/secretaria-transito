package secretaria_transito.project.services;

import org.springframework.stereotype.Service;
import secretaria_transito.project.entities.Owner;
import secretaria_transito.project.repositories.OwnerRepository;

import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Optional<Owner> findOwnerById(Long id){
        return ownerRepository.findById(id);
    }
}
