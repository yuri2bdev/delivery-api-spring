package br.com.delivery.deliveryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDto {
    private String username;
    private String password;
    private String email;
    private String telefone;
}
