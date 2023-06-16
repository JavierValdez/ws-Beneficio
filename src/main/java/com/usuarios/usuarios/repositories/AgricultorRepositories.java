/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.Agricultor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author fasp9
 */
public interface AgricultorRepositories extends CrudRepository<Agricultor,Integer> {
    
    @Override
    public List<Agricultor> findAll();
}
