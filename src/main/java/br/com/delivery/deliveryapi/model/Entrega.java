package br.com.delivery.deliveryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "entrega")
public class Entrega {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "entrega_id")
    private Long idEntrega;
    @Column(name = "endereco")
    private String endereco;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
