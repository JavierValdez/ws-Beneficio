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
    
    @CrossOrigin(origins="*")
    @GetMapping(value="Transportista/EncuentraTransportista")
    public List<Transportista> getAllTransportista (){
        return TransportistaServices.getAllTransportista();
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="Transportista/InscribirTransportista")
    public mensajeDto InscribirTransportista(@RequestBody TransportistaDto dto) throws Exception {
        return TransportistaServices.InscribirTransportista(dto);
    }
    
    @CrossOrigin(origins="*")
    @GetMapping(value="Transportista/AsignacionTransportistas")
    public List<Transportista> getTransporteS (@RequestParam String a) throws Exception{
        return TransportistaServices.getAllTransportista(a);
    }

    @CrossOrigin(origins="*")
    @GetMapping(value="Transportista/AsignacionLicencia")
    public List<Transportista> getTrasportista(@RequestParam String a) throws Exception{
        return TransportistaServices.getTransportistaLicencia(a);
    }
    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @GetMapping(value="Transportista/consultaTransportista")
    public String consultaTransportista(@RequestBody TransportistaDto dto)throws Exception{
        return TransportistaServices.consultaTransportista(dto);
    }



    //metodo para consultar solo un transportista

    
    //metodo para crear un usuario
    @CrossOrigin(origins="*")
    @PostMapping(value="Transportista/eliminarTransportista")
    public mensajeDto eliminarTransportista(@RequestBody TransportistaDto dto)throws Exception{
        return TransportistaServices.eliminarTransportista(dto);
    }
    
}
