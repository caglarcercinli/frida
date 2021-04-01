package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;

import java.util.List;
import java.util.Optional;

public interface SausService {
    List<Saus> findAll();
    List<Saus> findByNaamBeginMet(char letter);
    Optional<Saus> findByID(long id);
}
