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
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "pedido_id")
    private Long idPedido;
    @Column(name = "descricao")
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
