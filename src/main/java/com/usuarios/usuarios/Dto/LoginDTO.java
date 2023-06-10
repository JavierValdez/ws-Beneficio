package com.usuarios.usuarios.Dto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LoginDTO {
    private String user;
    private String contrasena;
}
