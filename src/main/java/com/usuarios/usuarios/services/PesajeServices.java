/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.services;

import com.usuarios.usuarios.Dto.PesajesDto;
import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.modelsPesaje.Pesajes;
import com.usuarios.usuarios.repositoriesPesaje.RepoPesajes;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fasp9
 */
@Service
@Transactional
public class PesajeServices {
    @Autowired
    RepoPesajes RepoPesajes;
    
    
    @Transactional
    public Pesajes almacenarPeso(PesajesDto dto) {
        java.util.Date fecha = new Date();
        final Pesajes Pesajes = new Pesajes();
        Pesajes.setId_cuenta(dto.getId_cuenta());
        Pesajes.setMatricula(dto.getMatricula());
        Pesajes.setNumero_licencia(dto.getNumero_licencia());
        Pesajes.setPeso_marcado(dto.getPeso_marcado());
        Pesajes.setPeso_de_camion(dto.getPeso_de_camion());
        Pesajes.setAgricultor(dto.getAgricultor());
        Pesajes.setUsuario_registro_pesaje(dto.getUsuario_registro_pesaje());
        Pesajes.setPeso_cargamento((dto.getPeso_marcado()) - (dto.getPeso_de_camion()));
        Pesajes.setFecha_creacion(fecha);
        //Pesajes.setId_parcialidad(dto.getId_parcialidad());
        return RepoPesajes.save(Pesajes);
        
    }
    
}
