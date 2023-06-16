
package com.usuarios.usuarios.services;

import com.usuarios.usuarios.Dto.pesajePesoCabalDto;
import com.usuarios.usuarios.models.pesajePesoCabal;
import com.usuarios.usuarios.repositories.pesajePesoCabalRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class pesajePesoCabalServices {
    @Autowired
    pesajePesoCabalRepositories pesajePesoCabalRepositories;
    
    @Transactional
    public List<pesajePesoCabal> getAllPesajes(){
        return pesajePesoCabalRepositories.findAll();
    }
    
    @Transactional
    public String createPesaje(pesajePesoCabalDto dto) {
        final pesajePesoCabal pesajePesoCabal = new pesajePesoCabal();
        pesajePesoCabal.setId_peso(dto.getId_peso());
        pesajePesoCabal.setId_cuenta(dto.getId_cuenta());
        pesajePesoCabal.setMatricula(dto.getMatricula());
        pesajePesoCabal.setNumero_licencia(dto.getNumero_licencia());
        pesajePesoCabal.setPeso_marcado(dto.getPeso_marcado());
        pesajePesoCabal.setPeso_de_camion(dto.getPeso_de_camion());
        pesajePesoCabal.setPeso_cargamento((dto.getPeso_marcado()) - (dto.getPeso_de_camion()));

        if (dto.getPeso_marcado() < dto.getPeso_de_camion()) {
            return "El pesaje marcado no puede ser menor al peso del camion.";
        } else {
            pesajePesoCabalRepositories.save(pesajePesoCabal);
            return "El pesaje Fue registrado con exito.";
        }
    }
}
