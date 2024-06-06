package school.sptech.exerciciopraticaac2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exerciciopraticaac2.entity.Show;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    //Optional<Show> findByLocalizacaoAndData(String localizacao, LocalDateTime data);
    boolean existsByLocalizacao(String localizacao);
    boolean existsByData(LocalDateTime data);

}
