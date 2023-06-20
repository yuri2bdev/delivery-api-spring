package br.com.delivery.deliveryapi.dto;

import br.com.delivery.deliveryapi.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntregaDto {
    private Long id;
    private String endereco;
    private Pedido pedido;
}
