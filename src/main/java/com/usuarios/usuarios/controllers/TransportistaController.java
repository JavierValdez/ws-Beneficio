package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.LoginDTO;
import com.usuarios.usuarios.Dto.TransportistaDto;
import com.usuarios.usuarios.models.Transportista;
import com.usuarios.usuarios.services.TransportistaServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//logs de errores
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
@RestController
public class TransportistaController {
    //logs de errores
    Logger logger = LoggerFactory.getLogger(TransportistaController.class);
    @Autowired
    TransportistaServices TransportistaServices;

    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @GetMapping(value="Transportista/EncuentraTransportista")
    public List<Transportista> getAllTransportista (){
        return TransportistaServices.getAllTransportista();
    }
    
    //metodo para crear un usuario


    @ResponseBody
    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @PostMapping(value = "Transportista/InscribirTransportista", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> InscribirTransportista(
            @RequestBody TransportistaDto dto,
            @RequestParam(required = false) String nit,
            @RequestParam(required = false) String contrasena
    ) {
        List<String> errores = new ArrayList<>();
        if(dto.getNumero_licencia() == null || dto.getNumero_licencia().isEmpty()) {
            errores.add("El número de licencia es obligatorio.<br/>");
        }
        if(dto.getNombres() == null || dto.getNombres().isEmpty()) {
            errores.add("El nombre es obligatorio.<br/>");
        }
        if(dto.getApellidos() == null || dto.getApellidos().isEmpty()) {
            errores.add("El apellido es obligatorio.<br/>");
        }
        if(dto.getTipo_licencia() == null || dto.getTipo_licencia().isEmpty()) {
            errores.add("El tipo de licencia es obligatorio.<br/>");
        }
        if(dto.getImagen() == null || dto.getImagen().isEmpty()) {
            errores.add("La imagen es obligatoria.<br/>");
        }

        if(nit == null || nit.isEmpty()) {
            errores.add("El parámetro nit es obligatorio.<br/>");
        }
        if(contrasena == null || contrasena.isEmpty()) {
            errores.add("El parámetro contrasena es obligatorio.<br/>");
        }

        if(!errores.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"errores\": \"" + String.join(",", errores) + "\"}");
        }

        //log
        System.out.println("dto: " + dto);
        //log debug
        logger.debug("dto: " + dto);
        logger.debug("nit: " + nit);
        logger.debug("contrasena: " + contrasena);
        try {
            String resultado = TransportistaServices.InscribirTransportista(dto, nit, contrasena);
            return ResponseEntity.ok().body("{\"resultado\": \"" + resultado + "\"}");
        } catch (Exception e) {
            String mensajeError = "Se produjo un error al inscribir el transportista: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"" + mensajeError + "\"}");
        }
    }


    //metodo para crear un usuario
    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @PostMapping(value="Transportista/consultaTransportista")
    public String consultaTransportista(
            @RequestBody TransportistaDto dto,
            @RequestParam(required=false)String nit,
            @RequestParam(required=false)String contrasena
    )throws Exception{
        //log
        System.out.println("dto: " + dto);
        return TransportistaServices.consultaTransportista(dto, nit, contrasena);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @PostMapping(value="Transportista/eliminarTransportista")
    public String eliminarTransportista(
            @RequestBody TransportistaDto dto,
            @RequestParam(required=false)String nit,
            @RequestParam(required=false)String contrasena
    )throws Exception{
        return TransportistaServices.eliminarTransportista(dto, nit, contrasena);
    }

    //Metodo login
    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @PostMapping(value="Transportista/login")
    public String login(
            @RequestBody LoginDTO loginRequest
    )throws Exception{
        return TransportistaServices.authenticateTransportista(loginRequest);
    }
    
}
