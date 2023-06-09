/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.CuentaDto;
import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.models.Cuenta;
import com.usuarios.usuarios.models.Transportista;
import com.usuarios.usuarios.services.CuentaServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fasp9
 */
@RestController
public class CuentaController {
    
    @Autowired
    CuentaServices CuentaServices;
    
    @CrossOrigin(origins="*")
    @GetMapping(value="Cuenta/CuentasAlmacenadas")
    public List<Cuenta> getAllAgricultor (){
        return CuentaServices.getAllCuenta();
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="Cuenta/CrearCuenta")
    public mensajeDto crearAgricultor(@RequestBody CuentaDto dto) {
        return CuentaServices.crearCuenta(dto);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="Cuenta/ActualizarCuenta")
    public mensajeDto actualizarCuenta(@RequestBody CuentaDto dto) {
        return CuentaServices.actualizarCuenta(dto);
    }
    
    @CrossOrigin(origins="*")
    @GetMapping(value="Cuenta/ListadoCuentas")
    public List<Cuenta> getTransporteS (@RequestParam String a) throws Exception{
        return CuentaServices.getAllCuentas(a);
    }
    

    
}
