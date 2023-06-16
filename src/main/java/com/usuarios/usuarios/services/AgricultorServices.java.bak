package com.usuarios.usuarios.services;

import com.usuarios.usuarios.Dto.AgricultorDto;
import com.usuarios.usuarios.models.Agricultor;
import com.usuarios.usuarios.repositories.AgricultorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AgricultorServices {

    private static final String clave = "MiClaveSecreta12"; // Clave secreta para encriptar

    private final AgricultorRepositories agricultorRepositories;

    private final DataSource dataSource;

    @Autowired
    public AgricultorServices(AgricultorRepositories agricultorRepositories, DataSource dataSource) {
        this.agricultorRepositories = agricultorRepositories;
        this.dataSource = dataSource;
    }

    public List<Agricultor> getAllAgricultor() {
        return agricultorRepositories.findAll();
    }

    public Agricultor crearAgricultor(AgricultorDto dto) throws Exception {
        String passEncriptada = encriptar(dto.getContrasena());
        String pasDesencriptada = desencriptar(passEncriptada);
        Date fecha = new Date();
        Agricultor agricultor = new Agricultor();
        agricultor.setNit(dto.getNit());
        agricultor.setNombre_comercial(dto.getNombre_comercial());
        agricultor.setContrasena(passEncriptada);
        agricultor.setCorreo(dto.getCorreo());
        agricultor.setEdad(dto.getEdad());
        agricultor.setTelefono(dto.getTelefono());
        agricultor.setDireccion(dto.getDireccion());
        agricultor.setEstado("1020");
        agricultor.setFecha_inscripcion(fecha);
        return agricultorRepositories.save(agricultor);
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
}
