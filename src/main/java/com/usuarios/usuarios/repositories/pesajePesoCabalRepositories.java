
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.Dto.CuentaDto;
import com.usuarios.usuarios.models.Cuenta;
import com.usuarios.usuarios.models.Transportista;
import com.usuarios.usuarios.models.pesajePesoCabal;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface pesajePesoCabalRepositories extends CrudRepository<pesajePesoCabal,Integer> {
    @Override
    public List<pesajePesoCabal> findAll();
   
    @Transactional
    @Query(value = "select id_cuenta,estado_cuenta,usuario_agricultor,numero_pesajes_registrados,numero_parcialidades,peso_total_de_envio  from cuenta where id_cuenta= :pid_cuenta", nativeQuery = true)
    public String consultarCuenta(@Param("pid_cuenta") String pid_cuenta);
    
    @Transactional
    @Query(value = "select sum(peso_cargamento)from pesaje_peso_cabal where id_cuenta=:pid", nativeQuery = true)
    public Integer consultaSumatoria(@Param("pid") String id);
    
    @Transactional
    @Query(value = "select matriculas_autorizadas from cuenta where id_cuenta=:pid_cuenta", nativeQuery = true)
    public String consultarMatriculas(@Param("pid_cuenta") String pid_cuenta);

    @Transactional
    @Query(value = "select estado_cuenta from cuenta where id_cuenta=:pid_cuenta", nativeQuery = true)
    public String consultarEstado(@Param("pid_cuenta") String pid_cuenta);

    @Transactional
    @Query(value = "select estado from transporte where matricula= :matricula_evaluada", nativeQuery = true)
    public Integer consultarestadoMatricula(@Param("matricula_evaluada") String matricula_evaluada);
    
    @Transactional
    @Query(value = "select estado from transportista where numero_licencia=:licencia", nativeQuery = true)
    public Integer consultarEstadoTransportista(@Param("licencia") String licencia);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set estado_cuenta= 'Pesaje Iniciado',numero_pesajes_registrados=numero_pesajes_registrados+1 where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizaPrimerPeso(@Param("pid_cuenta") String pid_cuenta);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set numero_pesajes_registrados=numero_pesajes_registrados+1 where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizaNumeroParcialidades(@Param("pid_cuenta") String pid_cuenta);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update cuenta set  estado_cuenta= 'Pesaje Finalizado',numero_pesajes_registrados=numero_pesajes_registrados+1 where id_cuenta=:pid_cuenta", nativeQuery = true)
    public int actualizaUltimoPesaje(@Param("pid_cuenta") String pid_cuenta);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update transporte set  disponibilidad=true where matricula=:plic", nativeQuery = true)
    public int actualizaDisTransporte(@Param("plic") String lic);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update transportista set  disponibilidad=true where numero_licencia=:plic", nativeQuery = true)
    public int actualizaDisLic(@Param("plic") String lic);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update beneficio set pesaje_realizado=true where id_ingreso=:pid", nativeQuery = true)
    public int actualizarPesaje(@Param("pid") String id);
    
    @Transactional
    @Query(value = "select ingreso_en_beneficio from agricultor where id_parcialidad=:pid", nativeQuery = true)
    public boolean consultaIngresos(@Param("pid") String id);
    
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update agricultor set usuario_registro_pesaje=:puser where id_ingreso=:pid", nativeQuery = true)
    public int actualizaAgricultor(@Param("puser") String user, @Param("pid") String id);
    
    
}
