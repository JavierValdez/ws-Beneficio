
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.pesajePesoCabalDto;
import com.usuarios.usuarios.models.pesajePesoCabal;
import com.usuarios.usuarios.services.pesajePesoCabalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class pesajePesoCabalController {
    @Autowired
    pesajePesoCabalServices pesajePesoCabalServices;
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value="pesajePesoCabal/findAllPesajes")
    public List<pesajePesoCabal> getAllPesajes (){
        return pesajePesoCabalServices.getAllPesajes();
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="pesajePesoCabal/createPesaje")
    public String createPesaje(@RequestBody pesajePesoCabalDto dto){
        return pesajePesoCabalServices.createPesaje(dto);
    }
}
