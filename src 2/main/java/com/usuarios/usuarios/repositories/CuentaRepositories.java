/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.Cuenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fasp9
 */
@Repository
@Qualifier("")
public interface CuentaRepositories extends CrudRepository<Cuenta,Integer> {
    
    @Override
    public List<Cuenta> findAll();
    
    
    
    @Query(value = "select * from cuenta where id_cuenta= :idCuenta", nativeQuery = true)
    @Transactional(readOnly=true)
    public List<Cuenta>consultarCuenta(@Param("idCuenta") Integer idCuenta);
    
    @Query(value = "select * from cuenta where usuario_agricultor=:pa  ", nativeQuery = true)
    @Transactional 
    public List<Cuenta> consulta( @Param("pa")String a);
    
}
