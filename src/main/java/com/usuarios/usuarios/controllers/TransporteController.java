
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.TransporteDto;
import com.usuarios.usuarios.models.Transporte;
import com.usuarios.usuarios.services.TransporteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransporteController {
    @Autowired
    TransporteServices TransporteServices;
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value="Transporte/findAllTransporte")
    public List<Transporte> getAllTransporte (){
        return TransporteServices.getAllTransporte();
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="Transporte/InscribirTransporte")
    public String InscribirTransporte(
            @RequestBody TransporteDto dto,
            @RequestParam(required=false)String nit,
            @RequestParam(required=false)String contrasena
    )throws Exception{
        return TransporteServices.InscribirTransporte(dto, nit, contrasena);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="Transporte/consultaTransporte")
    public String consultarTransporte(
            @RequestBody TransporteDto dto,
            @RequestParam(required=false)String nit,
            @RequestParam(required=false)String contrasena
    )throws Exception{
        return TransporteServices.consultarTransporte(dto, nit, contrasena);
    }
    //metodo para crear un usuario
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="Transporte/eliminarTransporte")
    public String eliminarTransporte(
            @RequestBody TransporteDto dto,
            @RequestParam(required=false)String nit,
            @RequestParam(required=false)String contrasena
    )throws Exception{
        return TransporteServices.eliminarTransporte(dto, nit, contrasena);
    }
   
}
