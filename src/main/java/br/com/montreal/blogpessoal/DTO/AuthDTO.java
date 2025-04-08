package br.com.montreal.blogpessoal.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @Email(message = "O email deve ser válido")
        @NotBlank(message = "O email não pode estar vazio")
        String usuario,

        @NotBlank(message = "A senha não pode estar vazia")
        String senha) {}
