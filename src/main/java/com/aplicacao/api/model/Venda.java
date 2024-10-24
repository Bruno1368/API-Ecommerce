package com.aplicacao.api.model;
import com.aplicacao.api.dto.DtoVenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Venda")
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itensVenda;
    @OneToOne
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    public Venda(DtoVenda venda) {
        this.data = LocalDate.now();
        this.id = venda.id();
    }
}
