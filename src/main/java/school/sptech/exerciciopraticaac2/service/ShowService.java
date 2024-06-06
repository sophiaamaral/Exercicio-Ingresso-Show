package school.sptech.exerciciopraticaac2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exerciciopraticaac2.entity.Ingresso;
import school.sptech.exerciciopraticaac2.entity.Show;
import school.sptech.exerciciopraticaac2.repository.IngressoRepository;
import school.sptech.exerciciopraticaac2.repository.ShowRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private IngressoRepository ingressoRepository;
    public List<Show> listar() {
        return showRepository.findAll();
    }

    public Show buscaPorId(Integer id) {
        Optional<Show> optionalShow = showRepository.findById(id);
        return optionalShow.orElse(null);
    }

    public Show salvar(Show show){
        if (!showRepository.existsByLocalizacao(show.getLocalizacao()) && showRepository.existsByData(show.getData())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um show com a mesma localização e data.");
        }

        return showRepository.save(show);
    }

    public void deletar(Integer id) {
        Show show = showRepository.findById(id).orElse(null);
        if (show != null){
            List<Ingresso> ingressos = ingressoRepository.findByShow(show);
            ingressoRepository.deleteAll(ingressos);
            showRepository.deleteById(id);

        }
    }
}
