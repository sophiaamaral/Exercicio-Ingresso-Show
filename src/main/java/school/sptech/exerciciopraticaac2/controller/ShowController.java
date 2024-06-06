package school.sptech.exerciciopraticaac2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exerciciopraticaac2.entity.Show;
import school.sptech.exerciciopraticaac2.service.ShowService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping
    public ResponseEntity<List<Show>> listar() {
        List<Show> shows = showService.listar();
        if (shows.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> buscaPorId(@PathVariable Integer id) {
        Show show = showService.buscaPorId(id);
        if (show == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(show);
    }

    @PostMapping
    public ResponseEntity<Show> salvar(@RequestBody @Valid Show show){
        Show showSalvo = showService.salvar(show);
        URI uri = URI.create("/shows/" + showSalvo.getId());
        return ResponseEntity.status(200).body(showSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        Show show = showService.buscaPorId(id);
        if (show == null) {
            return ResponseEntity.notFound().build();
        }
        showService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
