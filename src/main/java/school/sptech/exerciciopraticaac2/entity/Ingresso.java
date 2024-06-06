package school.sptech.exerciciopraticaac2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Data
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @DecimalMin("30.0")
    private Double preco;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoIngressoEnum tipoIngresso;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    public Double getPrecoFinal() {
        Double porcentagem;
        switch (this.tipoIngresso) {
            case INTEIRA:
                porcentagem = 1.0;
                break;
            case MEIA:
                porcentagem = 0.5;
                break;
            case CORTESIA:
                porcentagem = 0.0;
                break;
            default:
                throw new IllegalArgumentException("Tipo de ingresso inválido");
        }
        return this.preco * porcentagem;
    }

}
