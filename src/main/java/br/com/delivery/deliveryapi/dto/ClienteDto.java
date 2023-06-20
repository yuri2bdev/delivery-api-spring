package br.com.delivery.deliveryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {
    private String nome;
    private String email;
    private String telefone;
}
