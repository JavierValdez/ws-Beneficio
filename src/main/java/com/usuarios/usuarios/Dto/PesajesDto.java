/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fasp9
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PesajesDto {
    private String id_cuenta ;
    private String matricula;
    private String numero_licencia;
    private Integer peso_marcado;
    private Integer peso_de_camion;
    private String agricultor;
    private String usuario_registro_pesaje;
    //private String id_parcialidad;
    
}
