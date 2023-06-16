
package com.usuarios.usuarios.services;

import com.usuarios.usuarios.Dto.LoginDTO;
import com.usuarios.usuarios.Dto.usuarioDto;
import com.usuarios.usuarios.models.usuario;
import com.usuarios.usuarios.repositories.repositoriesUsuario;
import java.util.Base64;
import java.util.List;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class usuarioServices {
    private static final String clave = "MiClaveSecreta12"; // Clave secreta para encriptar
   
    @Autowired
    repositoriesUsuario repositoriesUsuario;
    
    @Transactional
    public List<usuario> getAllUsuari(){
        return repositoriesUsuario.findAll();
    }
    
    @Transactional
    public usuario createUsuario(usuarioDto dto) throws Exception{
        String passEncriptada =  encriptar(dto.getContrasena());
        String pasDesencriptada = desencriptar(passEncriptada);
        java.util.Date fecha = new Date();
        final usuario usuario = new usuario();
        usuario.setNit(dto.getNit());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setContrasena(passEncriptada);
        usuario.setCorreo(dto.getCorreo());
        usuario.setEdad(dto.getEdad());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setEstado("1020");
        usuario.setFecha_inscripcion(fecha);
        usuario.setRol(dto.getRol());
        System.out.println("NOMBRE ENCRIPTADO....>>>>>>>>><<<<<<<: "+passEncriptada);
        System.out.println("NOMBRE Desencriptado....>>>>>>>>><<<<<<<: "+pasDesencriptada);
        return repositoriesUsuario.save(usuario);
    }
    
    @Transactional 
    public boolean eliminarUusario(Integer id){
        try{
            repositoriesUsuario.deleteById(id);
           return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static String encriptar(String texto) throws Exception {
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), "AES");
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
        byte[] textoEncriptado = cifrador.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(textoEncriptado);
    }
     
     public static String desencriptar(String textoEncriptado) throws Exception {
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), "AES");
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
        byte[] textoDesencriptado = cifrador.doFinal(Base64.getDecoder().decode(textoEncriptado));
        return new String(textoDesencriptado, "UTF-8");
    }
    @Transactional
    public String authenticateUser(LoginDTO loginDto) throws Exception {
            String username = loginDto.getUser();
            String password = loginDto.getContrasena();

            //validacion de correo y contraseña en repositoriesUsuario.findAll()
            //si es correcto retorna el token
            //si es incorrecto retorna un mensaje de error
        password = encriptar(password);
        System.out.println("correo: "+username);
        System.out.println("contraseña: "+password);
            List<usuario> usuarios = repositoriesUsuario.findAll();
            for (usuario usuario : usuarios) {
                if(usuario.getNit().equals(username) && usuario.getContrasena().equals(password)){
                    //Devuelve todos los datos menos la clave

                    String json = "{";
                    json += "\"nit\": \"" + usuario.getNit() + "\",";
                    json += "\"nombre\": \"" + usuario.getNombre() + "\",";
                    json += "\"apellido\": \"" + usuario.getApellido() + "\",";
                    json += "\"correo\": \"" + usuario.getCorreo() + "\",";
                    json += "\"edad\": " + usuario.getEdad() + ",";
                    json += "\"telefono\": \"" + usuario.getTelefono() + "\",";
                    json += "\"direccion\": \"" + usuario.getDireccion() + "\",";
                    json += "\"estado\": \"" + usuario.getEstado() + "\",";
                    json += "\"rol\": \"" + usuario.getRol() + "\",";
                    json += "\"fecha_inscripcion\": \"" + usuario.getFecha_inscripcion() + "\"";
                    json += "}";
                    return json;


                }
            }
            return "{\"error\": \"Usuario o contraseña incorrectos\"}";

            // Lógica de autenticación
        }




}
