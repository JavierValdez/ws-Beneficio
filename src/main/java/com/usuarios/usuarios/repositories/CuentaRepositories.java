/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.Agricultor;
import com.usuarios.usuarios.models.Cuenta;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fasp9
 */
public interface CuentaRepositories extends CrudRepository<Cuenta,Integer> {
    
    @Override
    public List<Cuenta> findAll();
}
