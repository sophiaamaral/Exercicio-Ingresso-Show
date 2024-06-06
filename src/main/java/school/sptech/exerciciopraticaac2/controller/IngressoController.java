package school.sptech.exerciciopraticaac2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.exerciciopraticaac2.entity.Ingresso;
import school.sptech.exerciciopraticaac2.entity.Show;
import school.sptech.exerciciopraticaac2.service.IngressoService;
import school.sptech.exerciciopraticaac2.service.ShowService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingressos")
public class IngressoController {
    @Autowired
    private IngressoService ingressoService;

    @Autowired
    private ShowService showService;

    @GetMapping
    public ResponseEntity<List<Ingresso>> listar() {
        List<Ingresso> ingressos = ingressoService.listar();
        if (ingressos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingressos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingresso> buscarPorId(@PathVariable int id) {
        Ingresso ingresso = ingressoService.buscarPorId(id);
        if (ingresso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingresso);
    }

    @PostMapping("/shows/{showId}")
    public ResponseEntity<Ingresso> salvar(
            @PathVariable int showId,
            @RequestBody @Valid Ingresso ingresso
    ) {
        Show show = showService.buscaPorId(showId);
        if (show == null) {
            return ResponseEntity.notFound().build();
        }
        ingresso.setShow(show);
        Ingresso ingressoSalvo = ingressoService.salvar(ingresso, showId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingressoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        Ingresso ingresso = ingressoService.buscarPorId(id);
        if (ingresso == null){
            return ResponseEntity.notFound().build();
        }
        ingressoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
