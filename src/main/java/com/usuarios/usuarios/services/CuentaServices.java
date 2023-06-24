/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.services;

import com.usuarios.usuarios.Dto.CuentaDto;
import com.usuarios.usuarios.Dto.mensajeDto;
import com.usuarios.usuarios.models.Cuenta;
import com.usuarios.usuarios.repositories.CuentaRepositories;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fasp9
 */
@Service
@Transactional
public class CuentaServices {
    private String completada= "Pesaje Finalizado";
    private String cerrada = "Cuenta Cerrada";
    @Autowired
    CuentaRepositories CuentaRepositories;
    
    public int getFiveDigitsNumber() {
    double fiveDigits = 10000 + Math.random() * 90000;
    return (int) fiveDigits;
}
    @Transactional
    public List<Cuenta> getAllCuenta() {
        return CuentaRepositories.findAll();
    }
     
    @Transactional
    public List<Cuenta> getAllCuentas(String a) {
        return this.CuentaRepositories.consulta(a);  
    }
    
    @Transactional
    public mensajeDto actualizarCuenta(CuentaDto dto) {
        mensajeDto mensaje = new mensajeDto();
        final Cuenta Cuentas = new Cuenta();
        String estadoCuenta = CuentaRepositories.obtenerEstado(dto.getId_cuenta());
        if (estadoCuenta != null) {
            if (estadoCuenta.equals(completada)) {
                int estadoCuentaCerrada1 = CuentaRepositories.actualizar1(dto.getId_cuenta());
                if (estadoCuentaCerrada1 > 0) {
                    mensaje.setMensaje("Cuenta Actualizada Exitosamente");
                    return mensaje;
                } else {
                    mensaje.setMensaje("Error, no fue Posible Actualizar la Cuenta");
                    return mensaje;
                }
            } else if (estadoCuenta.equals(cerrada)) {
                int estadoCuentaCerrada2 = CuentaRepositories.actualizar2(dto.getId_cuenta());
                if (estadoCuentaCerrada2 > 0) {
                    mensaje.setMensaje("Cuenta Actualizada Exitosamente");
                    return mensaje;
                } else {
                    mensaje.setMensaje("Error, no fue Posible Actualizar la Cuenta");
                    return mensaje;
                }
            } else {
                mensaje.setMensaje("Error, No permitido Actualizar en el Estado Actual.");
                return mensaje;
            }
        } else {
            mensaje.setMensaje("No se Obtubo Informacion de la Cuenta ");
            return mensaje;
        }
    }
    
    
    @Transactional
    public mensajeDto crearCuenta(CuentaDto dto) {
        mensajeDto mensaje = new mensajeDto();
        int valor = this.getFiveDigitsNumber();
        java.util.Date fecha = new Date();
        final Cuenta Cuenta = new Cuenta();
        Cuenta.setId_cuenta(dto.getId_cuenta());
        Cuenta.setFecha_creacion(fecha);
        Cuenta.setEstado_cuenta("Cuenta Creada");
        Cuenta.setPeso_total_de_envio(dto.getPeso_total_de_envio());
        Cuenta.setNumero_parcialidades(dto.getNumero_parcialidades());
        Cuenta.setMatriculas_autorizadas(dto.getMatriculas_autorizadas());
        Cuenta.setUsuario_agricultor(dto.getUsuario_agricultor());
        Cuenta.setNumero_pesajes_registrados(0);
        Cuenta.setParcialidades_generadas(0);
        CuentaRepositories.save(Cuenta);
        mensaje.setMensaje("Cuenta Creada Exitosamente");
        return  mensaje;
    }
}
