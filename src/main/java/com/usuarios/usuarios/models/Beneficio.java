/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usuarios.usuarios.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fasp9
 */
@Entity
@Table(name="Beneficio", schema="public")
public class Beneficio {
    private String id_ingreso;
    private String id_cuenta;
    private String usuario_agricultor;
    private String matricula_autorizada;
    private String licencia_autorizada;
    private Date fecha_asignacion;
    private String usuario_beneficio;
    private boolean pesaje_realizado;

    public Beneficio() {
    }

    public Beneficio(String id_ingreso, String id_cuenta, String usuario_agricultor, String matricula_autorizada, String licencia_autorizada, Date fecha_asignacion, String usuario_beneficio, Boolean pesaje_realizado) {
        this.id_ingreso = id_ingreso;
        this.id_cuenta = id_cuenta;
        this.usuario_agricultor = usuario_agricultor;
        this.matricula_autorizada = matricula_autorizada;
        this.licencia_autorizada = licencia_autorizada;
        this.fecha_asignacion = fecha_asignacion;
        this.usuario_beneficio = usuario_beneficio;
        this.pesaje_realizado=pesaje_realizado;
    }

    @Id
    @Column (name="id_ingreso")
    public String getId_ingreso() {
        return id_ingreso;
    }

    @Column (name="id_cuenta", length=50)
    public String getId_cuenta() {
        return id_cuenta;
    }

    @Column (name="usuario_agricultor", length=25)
    public String getUsuario_agricultor() {
        return usuario_agricultor;
    }

    @Column (name="matricula_autorizada", length=13)
    public String getMatricula_autorizada() {
        return matricula_autorizada;
    }

    @Column (name="licencia_autorizada", length=13)
    public String getLicencia_autorizada() {
        return licencia_autorizada;
    }

    @Column (name="fecha_asignacion", length=13)
    public Date getFecha_asignacion() {
        return fecha_asignacion;
    }
    
    @Column (name="usuario_beneficio", length=13)
    public String getUsuario_beneficio() {
        return usuario_beneficio;
    }

    @Column (name="pesaje_realizado")
    public boolean getPesaje_realizado() {
        return pesaje_realizado;
    }

    public void setId_ingreso(String id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public void setId_cuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public void setUsuario_agricultor(String usuario_agricultor) {
        this.usuario_agricultor = usuario_agricultor;
    }

    public void setMatricula_autorizada(String matricula_autorizada) {
        this.matricula_autorizada = matricula_autorizada;
    }

    public void setLicencia_autorizada(String licencia_autorizada) {
        this.licencia_autorizada = licencia_autorizada;
    }

    public void setFecha_asignacion(Date fecha_asignacion) {
        this.fecha_asignacion = fecha_asignacion;
    }

    public void setUsuario_beneficio(String usuario_beneficio) {
        this.usuario_beneficio = usuario_beneficio;
    }

    public void setPesaje_realizado(boolean pesaje_realizado) {
        this.pesaje_realizado = pesaje_realizado;
    }
      
}

