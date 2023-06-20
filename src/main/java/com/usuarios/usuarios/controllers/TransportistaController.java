package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.TransportistaDto;
import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.models.Transportista;
import com.usuarios.usuarios.services.TransportistaServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransportistaController {
    @Autowired
    TransportistaServices TransportistaServices;

    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @GetMapping(value="Transportista/EncuentraTransportista")
    public List<Transportista> getAllTransportista (){
        return TransportistaServices.getAllTransportista();
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @PostMapping(value="Transportista/InscribirTransportista")
    public mensajeDto InscribirTransportista(@RequestBody TransportistaDto dto) throws Exception {
        return TransportistaServices.InscribirTransportista(dto);
    }

    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @GetMapping(value="Transportista/AsignacionTransportistas")
    public List<Transportista> getTransporteS (@RequestParam String a) throws Exception{
        return TransportistaServices.getAllTransportista(a);
    }
    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @GetMapping(value="Transportista/AsignacionLicencia")
    public List<Transportista> getTransportista (@RequestParam String a) throws Exception{
        return TransportistaServices.getAllTransportistaLicencia(a);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @GetMapping(value="Transportista/consultaTransportista")
    public String consultaTransportista(@RequestBody TransportistaDto dto)throws Exception{
        return TransportistaServices.consultaTransportista(dto);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins={"http://localhost:4200", "https://cafetalito-3af53.web.app"})
    @PostMapping(value="Transportista/eliminarTransportista")
    public String eliminarTransportista(@RequestBody TransportistaDto dto)throws Exception{
        return TransportistaServices.eliminarTransportista(dto);
    }
    
}
