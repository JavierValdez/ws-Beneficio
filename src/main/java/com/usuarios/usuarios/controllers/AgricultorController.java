/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.AgricultorDto;
import com.usuarios.usuarios.models.Agricultor;
import com.usuarios.usuarios.services.AgricultorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author fasp9
 */
@RestController
public class AgricultorController {
    @Autowired
    AgricultorServices AgricultorServices;
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value="Agricultor/EncuentraTodos")
    public List<Agricultor> getAllAgricultor (){
        return AgricultorServices.getAllAgricultor();
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="Agricultor/CrearAgricultor")
    public Agricultor crearAgricultor(@RequestBody AgricultorDto dto) throws Exception{
        return AgricultorServices.crearAgricultor(dto);
    }
}
