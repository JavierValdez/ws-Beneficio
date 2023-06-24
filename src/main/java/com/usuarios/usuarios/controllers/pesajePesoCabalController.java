
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.Dto.pesajePesoCabalDto;
import com.usuarios.usuarios.models.Transportista;
import com.usuarios.usuarios.models.pesajePesoCabal;
import com.usuarios.usuarios.services.TransportistaServices;
import com.usuarios.usuarios.services.pesajePesoCabalServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pesajePesoCabalController {
    @Autowired
    pesajePesoCabalServices pesajePesoCabalServices;
    
    @CrossOrigin(origins="*")
    @GetMapping(value="pesajePesoCabal/findAllPesajes")
    public List<pesajePesoCabal> getAllPesajes (){
        return pesajePesoCabalServices.getAllPesajes();
    }
    
    
    //metodo para crear un usuario

    @CrossOrigin(origins="*")
    @PostMapping(value = "pesajePesoCabal/createPesaje")
    public mensajeDto createPesaje(@RequestBody pesajePesoCabalDto dto) {
        return pesajePesoCabalServices.createPesaje(dto);
    }

    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="pesajePesoCabal/consultaSumatoria")
    public Integer consultaSumatoria(@RequestBody pesajePesoCabalDto dto){
        return pesajePesoCabalServices.consultaSumatoria(dto);
    }


    @CrossOrigin(origins="*")
    @PostMapping(value="pesajePesoCabal/consultaEstado")
    public ResponseEntity<Map<String, String>> consultaEstado(@RequestBody pesajePesoCabalDto dto){
        String estado = pesajePesoCabalServices.consultaEstado(dto);
        Map<String, String> response = new HashMap<>();
        response.put("estado", estado);
        return ResponseEntity.ok(response);
    }
}
