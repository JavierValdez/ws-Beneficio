
package com.usuarios.usuarios.additional.repositories;


import com.usuarios.usuarios.modelsPesaje.pesajePesoCabal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface pesajePesoCabalRepositories extends CrudRepository<pesajePesoCabal,Integer> {
    @Override
    @PersistenceContext(unitName = "additional")
    @Qualifier("additionalEntityManagerFactory")
    public List<pesajePesoCabal> findAll();
   
    
    @Transactional
    @Query(value = "select id_cuenta,estado_cuenta,usuario_agricultor,numero_pesajes_registrados,numero_parcialidades,peso_total_de_envio  from cuenta where id_cuenta= :pid_cuenta", nativeQuery = true)
    public String consultarCuenta(@Param("pid_cuenta") Integer pid_cuenta);
    
   
    @Transactional
    @Query(value = "select matriculas_autorizadas from cuenta where id_cuenta= :pid_cuenta", nativeQuery = true)
    public String consultarMatriculas(@Param("pid_cuenta") Integer pid_cuenta);
    
    @Transactional
    @Query(value = "select estado from transporte where matricula= :matricula_evaluada", nativeQuery = true)
    public Integer consultarestadoMatricula(@Param("matricula_evaluada") String matricula_evaluada);
    
    @Transactional
    @Query(value = "select estado from transportista where numero_licencia= :licencia", nativeQuery = true)
    public int consultarEstadoTransportista(@Param("licencia") String licencia);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set estado_cuenta= 'Pesaje Iniciado',numero_pesajes_registrados=numero_pesajes_registrados+1 where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizaPrimerPeso(@Param("pid_cuenta") Integer pid_cuenta);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set numero_pesajes_registrados=numero_pesajes_registrados+1 where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizaNumeroParcialidades(@Param("pid_cuenta") Integer pid_cuenta);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set  estado_cuenta= 'Pesaje Finalizado',numero_pesajes_registrados=numero_pesajes_registrados+1 where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizaUltimoPesaje(@Param("pid_cuenta") Integer pid_cuenta);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update transporte set  disponibilidad=true where matricula=:plic", nativeQuery = true)
    public int actualizaDisTransporte(@Param("plic") String lic);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update transportista set  disponibilidad=true where numero_licencia=:plic", nativeQuery = true)
    public int actualizaDisLic(@Param("plic") String lic);
    
    
    
}
