/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.repositories;


import com.usuarios.usuarios.models.Beneficio;
import java.util.Date;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fasp9
 */

@Repository
@Qualifier("")
public interface BeneficioRepositories extends CrudRepository<Beneficio,Integer>{
    
    @Transactional
    @Query(value = "select id_cuenta,estado_cuenta,usuario_agricultor,numero_pesajes_registrados,numero_parcialidades,peso_total_de_envio  from cuenta where id_cuenta= :pid_cuenta", nativeQuery = true)
    public String consultarCuenta(@Param("pid_cuenta") Integer pid_cuenta);
    
    @Transactional
    @Query(value = "select estado from transporte where matricula= :pmatricula and usuario_creo = :puser", nativeQuery = true)
    public String consultarMatricula(@Param("pmatricula") String matricula, @Param("puser") String user);
    
    @Transactional
    @Query(value = "select estado from transportista where numero_licencia= :pnumero_licencia and usuario_creo = :puser", nativeQuery = true)
    public String consultarLicencia(@Param("pnumero_licencia") String numero_licencia, @Param("puser") String user);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set  estado_cuenta= 'Cuenta Abierta', fecha_modificacion= :pfecha where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizaE(@Param("pid_cuenta") Integer pid_cuenta, @Param("pfecha") Date fecha);
}
