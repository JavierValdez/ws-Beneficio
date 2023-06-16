
package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.Dto.LoginDTO;
import com.usuarios.usuarios.Dto.usuarioDto;
import com.usuarios.usuarios.models.usuario;
import com.usuarios.usuarios.services.usuarioServices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//Logs de errores
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class usuarioController {
    //Logs de errores
    Logger logger = LoggerFactory.getLogger(usuarioController.class);
    @Autowired
    usuarioServices usuarioServices;
   
    //metodo que obtienen la lista de usuarios 

    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @GetMapping(value="getAll/user")
    public List<usuario> getAllUsuario (){
        return usuarioServices.getAllUsuari();
    }
    //metodo para crear un usuario
    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @PostMapping(value="create/user")
    public usuario createUsuario(@RequestBody usuarioDto dto) throws Exception{
        return usuarioServices.createUsuario(dto);
    }
    
    //metodo para actualizar un usuario
    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @PostMapping(value="update/user")
    public usuario updateUsuario(@RequestBody usuarioDto dto) throws Exception {
        return usuarioServices.createUsuario(dto);
    }
    @CrossOrigin(origins = {"http://localhost:4200", "https://cafetalito-3af53.web.app/"})
    @PostMapping(value = "login")
    public String login(@RequestBody LoginDTO loginRequest) throws Exception {
        // Lógica de inicio de sesión
        return usuarioServices.authenticateUser(loginRequest);
    }










}   

