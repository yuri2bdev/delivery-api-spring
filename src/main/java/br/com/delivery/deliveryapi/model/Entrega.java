package br.com.delivery.deliveryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "entrega")
public class Entrega {

    @Id
    @Column (name = "entrega_id")
    private Long id;

    @Column(name = "endereco")
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
