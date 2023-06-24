/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.Agricultor;
import com.usuarios.usuarios.models.Cuenta;
import com.usuarios.usuarios.models.pesajePesoCabal;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fasp9
 */
public interface CuentaRepositories extends CrudRepository<Cuenta,Integer> {
    
    @Override
    public List<Cuenta> findAll();
    
    @Query(value = "select * from cuenta where id_cuenta= :idCuenta", nativeQuery = true)
    @Transactional(readOnly=true)
    public List<Cuenta>consultarCuenta(@Param("idCuenta") Integer idCuenta);
    
    @Query(value = "select * from cuenta where usuario_agricultor=:pa", nativeQuery = true)
    @Transactional 
    public List<Cuenta> consulta( @Param("pa")String a);
    
    @Query(value = "select estado_cuenta from cuenta where id_cuenta=:pa", nativeQuery = true)
    @Transactional 
    public String obtenerEstado( @Param("pa") String a);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set estado_cuenta ='Cuenta Cerrada' where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizar1(@Param("pid_cuenta") String pid_cuenta);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set estado_cuenta ='Cuenta Confirmada' where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizar2(@Param("pid_cuenta") String pid_cuenta);
    
}
