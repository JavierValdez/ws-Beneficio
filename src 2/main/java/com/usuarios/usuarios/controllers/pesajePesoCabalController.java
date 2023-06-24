
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.Dto.pesajePesoCabalDto;
import com.usuarios.usuarios.modelsPesaje.pesajePesoCabal;
import com.usuarios.usuarios.services.pesajePesoCabalServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pesajePesoCabalController {
    @Autowired
     pesajePesoCabalServices pesajePesoCabalServices;

    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @GetMapping(value="pesajePesoCabal/findAllPesajes")
    public List<pesajePesoCabal> getAllPesajes (){
        return pesajePesoCabalServices.getAllPesajes();
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @PostMapping(value="pesajePesoCabal/createPesaje")
    public mensajeDto createPesaje(@RequestBody pesajePesoCabalDto dto){
        return pesajePesoCabalServices.createPesaje(dto);
    }
}
