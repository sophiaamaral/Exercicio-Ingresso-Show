package school.sptech.exerciciopraticaac2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.exerciciopraticaac2.entity.Ingresso;
import school.sptech.exerciciopraticaac2.repository.IngressoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    public List<Ingresso> listar() {
        return ingressoRepository.findAll();
    }

    public Ingresso buscarPorId(Integer id) {
        Optional<Ingresso> optionalIngresso = ingressoRepository.findById(id);

        return optionalIngresso.orElse(null);
    }

    public Ingresso salvar(Ingresso ingresso, Integer showId) {
        return ingressoRepository.save(ingresso);
    }

    public void deletar(Integer id) {
        ingressoRepository.deleteById(id);
    }
}
