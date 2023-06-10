
/**
 * Clase TransportistaServices
 *
 * Esta clase es la encargada de manejar la lógica de negocio relacionada con los transportistas.
 *
 * Métodos:
 * - getAllTransportista(): devuelve una lista con todos los transportistas registrados en la base de datos.
 * - InscribirTransportista(): inscribe un nuevo transportista en el sistema.
 * - consultaTransportista(): consulta el estado de un transportista en el sistema.
 * - eliminarTransportista(): elimina un transportista del sistema.
 * - consultaDatos(): consulta los datos de un agricultor en la base de datos.
 * - encriptar(): encripta una cadena de texto utilizando AES.
 * - desencriptar(): desencripta una cadena de texto utilizando AES.
 *
 * Atributos:
 * - clave: clave secreta utilizada para encriptar y desencriptar contraseñas.
 * - TransportistaRepositories: repositorio de transportistas utilizado para acceder a la base de datos.
 *
 * Autor: Juan Camilo Peña Vahos - https://github.com/jcamilope
 * Fecha: Septiembre 2021
 */

package com.usuarios.usuarios.services;

import com.usuarios.usuarios.Dto.LoginDTO;
import com.usuarios.usuarios.Dto.TransportistaDto;
import com.usuarios.usuarios.models.Transportista;
import java.util.List;
import javax.transaction.Transactional;

import com.usuarios.usuarios.models.usuario;
import com.usuarios.usuarios.repositories.TransportistaRepositories;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional
public class TransportistaServices {
    private static final Logger logger = LogManager.getLogger(TransportistaServices.class);
    private static final String clave = "MiClaveSecreta12"; // Clave secreta para encriptar

    @Autowired
    TransportistaRepositories TransportistaRepositories;

    /**
     * Método getAllTransportista
     *
     * Este método devuelve una lista con todos los transportistas registrados en la base de datos.
     *
     * @return List<Transportista>: lista con todos los transportistas registrados en la base de datos.
     */
    @Transactional
    public List<Transportista> getAllTransportista(){
        return TransportistaRepositories.findAll();
    }

    /**
     * Método InscribirTransportista
     *
     * Este método inscribe un nuevo transportista en el sistema.
     *
     * @param dto: objeto TransportistaDto con los datos del transportista a inscribir.
     * @param nit: número de identificación del agricultor que realiza la inscripción.
     * @param contrasena: contraseña del agricultor que realiza la inscripción.
     * @return String: mensaje indicando si la inscripción fue exitosa o no.
     * @throws Exception: excepción lanzada si ocurre algún error durante la encriptación de la contraseña.
     */
    @Transactional
    public String InscribirTransportista (TransportistaDto dto, String nit, String contrasena) throws Exception{
        String a = nit;
        String b = this.encriptar(contrasena);
        String c = this.desencriptar(b);
        java.util.Date fecha = new Date();
        String passEncriptado = encriptar(contrasena);
        final Transportista Transportista = new Transportista ();
        Transportista.setNumero_licencia(dto.getNumero_licencia());
        Transportista.setNombres(dto.getNombres());
        Transportista.setApellidos(dto.getApellidos());
        Transportista.setTipo_licencia(dto.getTipo_licencia());
        Transportista.setFecha_inscripcion(fecha);
        Transportista.setEstado(1020);
        Transportista.setImagen(dto.getImagen());
        Transportista.setContrasena(this.encriptar(dto.getContrasena()));


        logger.info("Mensaje de información");
        logger.warn("Mensaje de advertencia");
        logger.error("Mensaje de error");
        if (this.consultaDatos(a,b)) {
            if(dto.getTipo_licencia().equals("A")){
                TransportistaRepositories.save(Transportista);
                return "El Transportista fue Inscrito Correctamente en el Beneficio";
            }else{
                return "El Transportista debe tener licencia tipo A";
            }
        }else {
            return "Estimado Agricultor, sus credenciales no son correctas.   Acceso no autorizado";
        }
    }

    /**
     * Método consultaTransportista
     *
     * Este método consulta el estado de un transportista en el sistema.
     *
     * @param dto: objeto TransportistaDto con los datos del transportista a consultar.
     * @param nit: número de identificación del agricultor que realiza la consulta.
     * @param contrasena: contraseña del agricultor que realiza la consulta.
     * @return String: mensaje indicando el estado del transportista consultado.
     * @throws Exception: excepción lanzada si ocurre algún error durante la encriptación de la contraseña.
     */
    public String consultaTransportista(TransportistaDto dto, String nit, String contrasena) throws Exception{
        String a = nit;
        String b = this.encriptar(contrasena);
        String c = this.desencriptar(b);
        String pLicencia = dto.getNumero_licencia();
        String numeroLicencia = this.TransportistaRepositories.consultaTransportista(pLicencia);
        if (this.consultaDatos(a,b)) {
            if(numeroLicencia==null || numeroLicencia.equals("")){
                return "No se obtuvieron los datos del Transportista Ingresado";
            }else{
                switch(numeroLicencia){
                    case "1028":
                        return "El Transportista con el numero de Licencia: "+pLicencia+" se encuentra Inactivo";
                    case "1020":
                        return "El Transportista con el numero de Licencia: "+pLicencia+" se encuentra Activo";
                    case "1030":
                        return "El Transportista con el numero de Licencia: "+pLicencia+" se encuentra Eliminado";
                    default :
                        return  "No se obtuvo el estado del Transportista con el numero de Licencia: "+pLicencia;
                }
            }
        }else {
            return "Estimado Agricultor, sus credenciales no son correctas.   Acceso no autorizado";
        }
    }

    /**
     * Método eliminarTransportista
     *
     * Este método elimina un transportista del sistema.
     *
     * @param dto: objeto TransportistaDto con los datos del transportista a eliminar.
     * @param nit: número de identificación del agricultor que realiza la eliminación.
     * @param contrasena: contraseña del agricultor que realiza la eliminación.
     * @return String: mensaje indicando si la eliminación fue exitosa o no.
     * @throws Exception: excepción lanzada si ocurre algún error durante la encriptación de la contraseña.
     */
    public String eliminarTransportista(TransportistaDto dto, String nit, String contrasena) throws Exception {
        String a = nit;
        String b = this.encriptar(contrasena);
        String plicencia = dto.getNumero_licencia();
        if (this.consultaDatos(a, b)) {
            int licencia = this.TransportistaRepositories.eliminarTransportista(plicencia);
            if (licencia > 0) {
                return "El transporte con las placas: "+ plicencia + " fue eliminado con exito";
            } else {
                return "Error al eliminar el transporte con las placas: "+ plicencia;
            }
        } else {
            return "Estimado Agricultor, sus credenciales no son correctas.   Acceso no autorizado";
        }
    }

    /**
     * Método consultaDatos
     *
     * Este método consulta los datos de un agricultor en la base de datos.
     *
     * @param a: número de identificación del agricultor.
     * @param b: contraseña del agricultor.
     * @return boolean: true si los datos del agricultor son válidos, false en caso contrario.
     */
    public boolean consultaDatos(String a, String b) {
        String pnit = a;
        String pcontrasena = b;
        String nit = this.TransportistaRepositories.consultaNit(pnit);
        String contrasena = this.TransportistaRepositories.consultaContrasena(pcontrasena);
        if (nit !=null && nit!="" && contrasena!=null && contrasena!="") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método encriptar
     *
     * Este método encripta una cadena de texto utilizando AES.
     *
     * @param texto: cadena de texto a encriptar.
     * @return String: cadena de texto encriptada.
     * @throws Exception: excepción lanzada si ocurre algún error durante la encriptación.
     */
    public static String encriptar(String texto) throws Exception {
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), "AES");
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
        byte[] textoEncriptado = cifrador.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    /**
     * Método desencriptar
     *
     * Este método desencripta una cadena de texto utilizando AES.
     *
     * @param textoEncriptado: cadena de texto encriptada a desencriptar.
     * @return String: cadena de texto desencriptada.
     * @throws Exception: excepción lanzada si ocurre algún error durante la desencriptación.
     */
    public static String desencriptar(String textoEncriptado) throws Exception {
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), "AES");
        Cipher cifrador = Cipher.getInstance("AES");
        cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
        byte[] textoDesencriptado = cifrador.doFinal(Base64.getDecoder().decode(textoEncriptado));
        return new String(textoDesencriptado, "UTF-8");
    }

    @Transactional
    public String authenticateTransportista(LoginDTO loginDto) throws Exception {
        String username = loginDto.getUser();
        String password = loginDto.getContrasena();

        //validacion de correo y contraseña en repositoriesUsuario.findAll()
        //si es correcto retorna el token
        //si es incorrecto retorna un mensaje de error
        password = encriptar(password);
        System.out.println("correo: "+username);
        System.out.println("contraseña: "+password);
        List<Transportista> Transportista = TransportistaRepositories.findAll();
        for (Transportista transportista : Transportista) {
            if(transportista.getNumero_licencia().equals(username) && transportista.getContrasena().equals(password)){
                //Devuelve todos los datos menos la clave

                String json = "{";
                json += "\"nombres\": \"" + transportista.getNombres() + "\",";
                json += "\"apellidos\": \"" + transportista.getApellidos() + "\",";
                json += "\"imagen\": \"" + transportista.getImagen() + "\",";
                json += "\"tipo_licencia\": \" " + transportista.getTipo_licencia() + "\",";
                json += "\"numero_licencia\": \"" + transportista.getNumero_licencia() + "\",";
                json += "\"estado\": \"" + transportista.getEstado() + "\",";
                json += "\"fecha_inscripcion\": \"" + transportista.getFecha_inscripcion() + "\",";
                json += "\"fecha_modificacion\": \"" + transportista.getFecha_modificacion() + "\"";
                json += "}";
                return json;


            }
        }
        return "{\"error\": \"Usuario o contraseña incorrectos\"}";

        // Lógica de autenticación
    }




}