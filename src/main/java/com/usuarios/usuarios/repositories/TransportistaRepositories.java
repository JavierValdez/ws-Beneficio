
package com.usuarios.usuarios.repositories;

import com.usuarios.usuarios.models.Transportista;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("")
public interface TransportistaRepositories extends CrudRepository<Transportista,Integer>{
    java.util.Date fecha = new Date();
    
    
    @Override
    public List<Transportista> findAll();
    
    //Area de Consultas a BD
    @Query(value = "select transportista.estado from transportista  where transportista.numero_licencia= :pLicencia limit 1", nativeQuery = true)
    @Transactional 
    public String consultaTransportista(@Param("pLicencia") String pLicencia);
    
   //Area de Consultas a BD
    @Query(value = "select agricultor.estado from  agricultor where agricultor.nit= :pnit limit 1", nativeQuery = true)
    @Transactional 
    public String consultaNit(@Param("pnit") String pnit);
    
    //Area de Consultas a BD
    @Query(value = "select agricultor.estado from  agricultor where agricultor.contrasena = :pcontrasena limit 1", nativeQuery = true)
    @Transactional 
    public String consultaContrasena(@Param("pcontrasena") String pcontrasena);
    
    //Area de Consultas a BD
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update transportista  set estado=1030, fecha_modificacion= :pfecham where numero_licencia= :plicencia", nativeQuery = true)
    public int eliminarTransportista( @Param("plicencia") String plicencia, @Param("pfecham") Date pfecham);
    
    @Query(value = "select * from transportista where estado=1020 and usuario_creo=:pa", nativeQuery = true)
    @Transactional 
    public List<Transportista> consulta( @Param("pa")String a);

    @Query(value = "select * from transportista where estado=1020 and numero_licencia=:licencia", nativeQuery = true)
    @Transactional
    public List<Transportista> consultaLicencia( @Param("licencia")String a);


}
