package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.repositories.SausRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultSausService implements SausService {
    private final SausRepository sausRepository;

   DefaultSausService(@Qualifier("properties") SausRepository sausRepository) {

        this.sausRepository = sausRepository;
    }

    @Override
    public List<Saus> findAll() {
        return sausRepository.findAll();
    }

    @Override
    public List<Saus> findByNaamBeginMet(char letter) {
        return sausRepository.findAll().stream()
                .filter(saus -> saus.getNaam().charAt(0)==letter)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Saus> findByID(long id) {
        return sausRepository.findAll().stream()
                .filter(saus -> saus.getNummer()==id).findFirst();
    }
}
