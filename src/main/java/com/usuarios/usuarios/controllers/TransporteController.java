
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.TransporteDto;
import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.models.Transporte;
import com.usuarios.usuarios.services.TransporteServices;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransporteController {
    @Autowired
    TransporteServices TransporteServices;
    
    @CrossOrigin(origins="*")
    @GetMapping(value="Transporte/findAllTransporte")
    public List<Transporte> getAllTransporte (){
        return TransporteServices.getAllTransporte();
    }
    
    @CrossOrigin(origins="*")
    @GetMapping(value="Transporte/AsignacionTransporte")
    public List<Transporte> getTransporteS (@RequestParam String a) throws Exception{
        return TransporteServices.getAllTransporte(a);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="Transporte/InscribirTransporte")
    public mensajeDto InscribirTransporte(@RequestBody TransporteDto dto) throws Exception{
        return TransporteServices.InscribirTransporte(dto);
    }
     
    //metodo para Concultar un Transporte por medio de la matricula
    @CrossOrigin(origins="*")
    @GetMapping(value="Transporte/consultaTransporte")
    public mensajeDto consultarTransporte(@RequestBody TransporteDto dto)throws Exception{
        return TransporteServices.consultarTransporte(dto);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="Transporte/eliminarTransporte")
    public mensajeDto eliminarTransporte(@RequestBody TransporteDto dto)throws Exception{
        return TransporteServices.eliminarTransporte(dto);
    }
   
}
