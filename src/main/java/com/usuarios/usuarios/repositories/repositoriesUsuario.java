
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface repositoriesUsuario extends CrudRepository<usuario,Integer>  {
    @Override
    public List<usuario> findAll();



}
