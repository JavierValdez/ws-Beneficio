/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.PesajesDto;
import com.usuarios.usuarios.modelsPesaje.Pesajes;
import com.usuarios.usuarios.services.PesajeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fasp9
 */
@RestController
public class PesajesController {
    @Autowired
    PesajeServices PesajeServices;
    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="Pesaje/AlmacenarPesaje")
    public Pesajes createPesaje(@RequestBody PesajesDto dto){
        return PesajeServices.almacenarPeso(dto);
    }
}
