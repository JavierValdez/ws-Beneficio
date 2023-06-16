package com.usuarios.usuarios.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransportistaDto {
    private String numero_licencia ;
    private String nombres; 
    private String apellidos; 
    private String tipo_licencia;
    private String imagen;
    private String contrasena;
    //private Integer estado;
    //private Date fecha_inscripcion;
    //private Date fecha_modificacion;
}
