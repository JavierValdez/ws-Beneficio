/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.BeneficioDto;
import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.models.Beneficio;
import com.usuarios.usuarios.services.BeneficioServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fasp9
 */
@RestController
public class BeneficioController {
    @Autowired
    BeneficioServices BeneficioServices;
    
    @CrossOrigin(origins="*")
    @GetMapping(value="Beneficio/findAllRegistros")
    public List<Beneficio> getAllTransporte (){
        return BeneficioServices.getAllRegistros();
    }
    
    
    @CrossOrigin(origins="*")
    @PostMapping(value="Beneficio/RegistrarIngreso")
    public mensajeDto registrarIngreso(@RequestBody BeneficioDto dto){
        return BeneficioServices.registrarIngreso(dto);
    }
    
}
