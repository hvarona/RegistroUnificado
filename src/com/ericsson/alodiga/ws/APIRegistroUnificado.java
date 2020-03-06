package com.ericsson.alodiga.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.ericcson.alodiga.bean.APIOperations;
import com.ericsson.alodiga.model.PreguntaSecreta;
import com.ericsson.alodiga.respuestas.Respuesta;
import com.ericsson.alodiga.respuestas.RespuestaCodigoRandom;
import com.ericsson.alodiga.respuestas.RespuestaGuardarDireccionConfianza;
import com.ericsson.alodiga.respuestas.RespuestaGuardarUsuario;
import com.ericsson.alodiga.respuestas.RespuestaListadoAplicaciones;
import com.ericsson.alodiga.respuestas.RespuestaListadoBancos;
import com.ericsson.alodiga.respuestas.RespuestaListadoEmpresa;
import com.ericsson.alodiga.respuestas.RespuestaListadoEstados;
import com.ericsson.alodiga.respuestas.RespuestaListadoOcupaciones;
import com.ericsson.alodiga.respuestas.RespuestaListadoTarjetas;
import com.ericsson.alodiga.respuestas.RespuestaListadoTipoCuentaBancaria;
import com.ericsson.alodiga.respuestas.RespuestaListadoTipoDocumento;
import com.ericsson.alodiga.respuestas.RespuestaListadoTipoEmpresa;
import com.ericsson.alodiga.respuestas.RespuestaListadoUsuarios;

import com.ericsson.alodiga.respuestas.RespuestaNuevoToken;
import com.ericsson.alodiga.respuestas.RespuestaPreguntasSecretas;
import com.ericsson.alodiga.respuestas.RespuestaPreguntasSecretasUsuario;
import com.ericsson.alodiga.respuestas.RespuestaToken;
import com.ericsson.alodiga.respuestas.RespuestaUsuario;
import java.sql.Timestamp;
import javax.net.ssl.SSLSession;

@WebService
public class APIRegistroUnificado {

    
    
    private static final Logger logger = Logger
            .getLogger(APIRegistroUnificado.class);

    @EJB
    private APIOperations operations;
    
   

    @WebMethod
    public RespuestaListadoAplicaciones getAplicaciones(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getAplicaciones(usuarioApi, passwordApi);
    }

    @WebMethod
    public RespuestaListadoOcupaciones getOcupaciones(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getOcupaciones(usuarioApi, passwordApi);
    }

    @WebMethod
    public RespuestaListadoTipoDocumento getTiposDocumento(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getTiposDocumento(usuarioApi, passwordApi);
    }

    @WebMethod
    public RespuestaListadoTipoEmpresa getTiposEmpresa(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getTiposEmpresa(usuarioApi, passwordApi);
    }

    @WebMethod
    public RespuestaListadoEmpresa getListaEmpresaPorPosTipoPersona(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "tipoPersona") String tipoPersona) {
        return operations.getListaEmpresaPorPosTipoPersona(usuarioApi,
                passwordApi, descripcion, tipoPersona);
    }

    @WebMethod
    public RespuestaGuardarUsuario guardarUsuario(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "credencial") String credencial,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "fechaNacimiento") String fechaNacimiento,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "paisId") String paisId,
            @WebParam(name = "estadoId") String estadoId,
            @WebParam(name = "ciudadId") String ciudadId,
            @WebParam(name = "condadoId") String condadoId,
            @WebParam(name = "codigoPostal") String codigoPostal,
            @WebParam(name = "codigoValidacionMovil") String codigoValidacionMovil,
            @WebParam(name = "nombreImagen") String nombreImagen,
            @WebParam(name = "imagenBytes") byte[] imagenBytes,
            @WebParam(name = "link") String link) {
        logger.debug("guardar usuario");
        return operations.guardarUsuario(usuarioApi, passwordApi, usuarioId,
                nombre, apellido, credencial, email, movil, fechaNacimiento,
                direccion, paisId, estadoId, ciudadId, condadoId, codigoPostal,
                codigoValidacionMovil, nombreImagen, imagenBytes, link,false);

    }
    
   @WebMethod
    public RespuestaGuardarUsuario guardarUsuarioAplicacionMovil(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "credencial") String credencial,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "fechaNacimiento") String fechaNacimiento,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "paisId") String paisId,
            @WebParam(name = "estadoId") String estadoId,
            @WebParam(name = "ciudadId") String ciudadId,
            @WebParam(name = "condadoId") String condadoId,
            @WebParam(name = "codigoPostal") String codigoPostal,
            @WebParam(name = "codigoValidacionMovil") String codigoValidacionMovil,
            @WebParam(name = "nombreImagen") String nombreImagen,
            @WebParam(name = "imagenBytes") byte[] imagenBytes,
            @WebParam(name = "link") String link,
            @WebParam(name = "pin") String pin) {
        logger.debug("guardar usuario");

        return operations.guardarUsuarioAplicacionMovil(usuarioApi, passwordApi, usuarioId,
                nombre, apellido, credencial, email, movil, fechaNacimiento,
                direccion, paisId, estadoId, ciudadId, condadoId, codigoPostal,
                codigoValidacionMovil, nombreImagen, imagenBytes, link,pin,false);

    } 

    @WebMethod
    public RespuestaGuardarUsuario guardarPerfilAloPos(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "genero") String genero,
            @WebParam(name = "telefonoResidencial") String telefonoResidencial,
            @WebParam(name = "ocupacionId") Integer ocupacionId,
            @WebParam(name = "tipoDocumentoId") String tipoDocumentoId,
            @WebParam(name = "numeroDocumento") String numeroDocumento,
            @WebParam(name = "nombreEmpresa") String nombreEmpresa,
            @WebParam(name = "emailEmpresa") String emailEmpresa,
            @WebParam(name = "tipoEmpresaId") Integer tipoEmpresaId,
            @WebParam(name = "einEmpresa") String einEmpresa,
            @WebParam(name = "tipoPersona") String tipoPersona,
            @WebParam(name = "fechavencimientoDocumento") String fechaVencimientoDocumento,
            @WebParam(name = "telefonoEmpresa") String telefonoEmpresa,
            @WebParam(name = "ingresosEstimadosEmpresa") String ingresosEstimadosEmpresa,
            @WebParam(name = "direccionEmpresa") String direccionEmpresa,
            @WebParam(name = "paisEmpresaId") Integer paisEmpresaId,
            @WebParam(name = "estadoEmpresaId") Integer estadoEmpresaId,
            @WebParam(name = "ciudadEmpresaId") Integer ciudadEmpresaId,
            @WebParam(name = "codigoPostalEmpresa") String codigoPostalEmpresa,
            @WebParam(name = "tipoCuentaBancariaId") Integer tipoCuentaBancariaId,
            @WebParam(name = "numeroRutaBancaria") String numeroRutaBancaria,
            @WebParam(name = "numeroCuentaBancaria") String numeroCuentaBancaria,
            @WebParam(name = "bancoId") Integer bancoId) {
        return operations.guardarPerfilAloPos(usuarioApi, passwordApi,
                usuarioId, genero, telefonoResidencial, ocupacionId,
                tipoDocumentoId, numeroDocumento, nombreEmpresa, emailEmpresa,
                tipoEmpresaId, einEmpresa, telefonoEmpresa, tipoPersona,
                fechaVencimientoDocumento, ingresosEstimadosEmpresa,
                direccionEmpresa, paisEmpresaId, estadoEmpresaId,
                ciudadEmpresaId, codigoPostalEmpresa, tipoCuentaBancariaId,
                numeroRutaBancaria, numeroCuentaBancaria, bancoId);
    }

    @WebMethod
    public RespuestaGuardarDireccionConfianza guardarDireccionConfianza(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "ip") String ip) {
        return operations.guardarDireccionConfianza(usuarioApi, passwordApi,
                usuarioId, ip);
    }

    @WebMethod
    public RespuestaPreguntasSecretas getPreguntasSecretas(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "IdIdioma") Integer IdIdioma) {
        return operations.getPreguntaIdioma(usuarioApi, passwordApi, IdIdioma);
    }

    @WebMethod
    public RespuestaPreguntasSecretasUsuario getPreguntasSecretasUsuario(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId) {
        return operations.getPreguntasSecretasUsuario(usuarioApi, passwordApi,
                usuarioId);
    }

    @WebMethod
    public RespuestaUsuario getUsuarioporemail(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "email") String email) {
        return operations.getUsuarioporemail(usuarioApi, passwordApi, email);
    }

    @WebMethod
    public RespuestaUsuario getUsuarioporcuenta(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "cuenta") String cuenta) {
        return operations.getUsuarioPorCuenta(usuarioApi, passwordApi, cuenta);
    }

    @WebMethod
    public RespuestaUsuario getUsuarioportarjeta(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "tarjeta") String tarjeta) {
        return operations.getUsuarioPorTarjeta(usuarioApi, passwordApi, tarjeta);
    }

    @WebMethod
    public RespuestaUsuario getUsuarioporAplicacion(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "aplicacionId") Integer aplicacionId) {
        return operations.getUsuarioporAplicacion(usuarioApi, passwordApi,
                usuarioId, aplicacionId);
    }

    @WebMethod
    public RespuestaUsuario getUsuariopormovil(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "movil") String movil) {
        return operations.getUsuariopormovil(usuarioApi, passwordApi, movil);
    }

    @WebMethod
    public RespuestaUsuario getUsuarioporId(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId) {
        return operations.getUsuarioporId(usuarioApi, passwordApi, usuarioId);
    }

    /*
     * @WebMethod public RespuestaListadoUsuarios getAplicacionUsuario(
     * 
     * @WebParam(name = "usuarioApi") String usuarioApi,
     * 
     * @WebParam(name = "passwordApi") String passwordApi,
     * 
     * @WebParam(name = "IdAplicacion") Integer IdAplicaciones) { return
     * operations.getAplicacionUsuario(usuarioApi, passwordApi, IdAplicaciones);
     * }
     */
    @WebMethod
    public RespuestaListadoUsuarios getUsuarios(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getUsuarios(usuarioApi, passwordApi);
    }

    @WebMethod
    public RespuestaGuardarUsuario guardarPerfilAloEsp(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "tipoCuenta") String tipoCuenta,
            @WebParam(name = "nombreTienda") String nombreTienda,
            @WebParam(name = "montoMaximo") Double montoMaximo,
            @WebParam(name = "einTienda") String einTienda,
            @WebParam(name = "taxTienda") String taxTienda,
            @WebParam(name = "razonSocial") String razonSocial,
            @WebParam(name = "telefonoEstablecimiento") String telefonoEstablecimiento,
            @WebParam(name = "idiomaEnvio") String idiomaEnvio) {
        return operations.guardarPerfilAloEsp(usuarioApi, passwordApi,
                usuarioId, tipoCuenta, nombreTienda, montoMaximo, einTienda,
                taxTienda, razonSocial, telefonoEstablecimiento, idiomaEnvio);
    }

    @WebMethod
    public RespuestaGuardarUsuario guardarPerfilAloBenefit(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId) {
        return operations.guardarPerfilAloBenefit(usuarioApi, passwordApi,
                usuarioId);
    }

    @WebMethod
    public RespuestaGuardarUsuario guardarPerfilAloCash(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "genero") String genero,
            @WebParam(name = "TelefonoResidencial") String TelefonoResidencial,
            @WebParam(name = "ocupacion") String ocupacion,
            @WebParam(name = "tipoDocumentoId") String tipoDocumentoId,
            @WebParam(name = "numeroDocumento") String numeroDocumento) {
        return operations.guardarPerfilAloCash(usuarioApi, passwordApi,
                usuarioId, genero, TelefonoResidencial, ocupacion,
                tipoDocumentoId, numeroDocumento);
    }

    @WebMethod
    public RespuestaGuardarUsuario guardarPerfilAloRRP(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "kitAfiliacion") boolean kitAfiliacion,
            @WebParam(name = "empresaId") String empresaId,
            @WebParam(name = "distribuidorPadreId") String distribuidorPadreId) {
        return operations.guardarPerfilAloRRP(usuarioApi, passwordApi,
                usuarioId, kitAfiliacion, empresaId, distribuidorPadreId);
    }

    @WebMethod
    public Respuesta desbloquearUsuario(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "preguntasSecretasEnviadas") List<PreguntaSecreta> preguntasSecretasEnviadas) {
        return operations.desbloquearUsuario(usuarioApi, passwordApi,
                usuarioId, preguntasSecretasEnviadas);
    }

    public Respuesta logout(@WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "token") String token) {
        return operations.logout(usuarioApi, passwordApi, token);
    }

    @WebMethod
    public RespuestaCodigoRandom generarCodigoMovil(
            @WebParam(name = "movil") String movil) {
        return operations.generarCodigoMovil(movil);
    }

    @WebMethod
    public Respuesta activarUsuario(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "token") Integer usuarioId) {
        return operations.activarUsuario(usuarioApi, passwordApi, usuarioId);
    }

    @WebMethod
    public Respuesta activarUsuarioPorCorreo(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId) {
        return operations.activarUsuarioPorCorreo(usuarioApi, passwordApi,
                usuarioId);
    }

    public Respuesta guardarBitacora(
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "ip") String ip,
            @WebParam(name = "accion") String accion) {
        return operations.guardarBitacora(usuarioId, ip, accion);
    }

    public RespuestaNuevoToken login(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "credencial") String credencial,
            @WebParam(name = "ip") String ip) {
        return operations.login(usuarioApi, passwordApi, email, movil,
                credencial, ip);
    }
    
    
    public RespuestaUsuario loginAplicacionMovil(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "credencial") String credencial,
            @WebParam(name = "ip") String ip) {
        return operations.loginAplicacionMovil(usuarioApi, passwordApi, email, movil,
                credencial, ip);
    }

    public RespuestaUsuario logintemp(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "credencial") String credencial,
            @WebParam(name = "ip") String ip) {
        return operations.loginp(usuarioApi, passwordApi, email, movil,
                credencial, ip);
    }

    @WebMethod
    public RespuestaToken getEstadoToken(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "token") String Token) {
        return operations.getEstadoToken(usuarioApi, passwordApi, Token);
    }

    @WebMethod
    public Respuesta setPreguntasSecretasUsuario(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "preguntasSecretas") List<PreguntaSecreta> preguntasSecretas) {
        return operations.setPreguntasSecretasUsuario(usuarioId,
                preguntasSecretas);
    }
    
    
    @WebMethod
    public Respuesta setPreguntasSecretasUsuarioAplicacionMovil(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "preguntaId1") String preguntaId1,
            @WebParam(name = "repuestaId1") String repuestaId1,
            @WebParam(name = "preguntaId2") String preguntaId2,
            @WebParam(name = "repuestaId2") String repuestaId2,
            @WebParam(name = "preguntaId3") String preguntaId3,
            @WebParam(name = "repuestaId3") String repuestaId3) {
        return operations.setPreguntasSecretasUsuarioAplicacionMovil(usuarioApi, passwordApi, usuarioId, preguntaId1, repuestaId1, 
        preguntaId2, repuestaId2, preguntaId3, repuestaId3);
    }
    

    @WebMethod
    public Respuesta recuperarCredencial(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "idioma") String idioma,
            @WebParam(name = "email") String email) {
        return operations.recuperarCredencial(usuarioApi, passwordApi, idioma,
                email);
    }

    @WebMethod
    public Respuesta cambiarCredencial(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "credencial") String credencial) {
        return operations.cambiarCredencial(usuarioApi, passwordApi, usuarioId,
                credencial);
    }

    @WebMethod
    public Respuesta cambiarCredencialPorCorreo(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "email") String email,
            @WebParam(name = "credencial") String credencial) {
        return operations.cambiarCredencialPorCorreo(usuarioApi, passwordApi,
                email, credencial);
    }

    @WebMethod
    public Respuesta generarCodigoMovilSMS(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "movil") String movil) {
        return operations.generarCodigoMovilSMS(usuarioApi, passwordApi, movil);
    }
    
    @WebMethod
    public Respuesta generarCodigoMovilSMSAplicacionMovil(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "movil") String movil, 
            @WebParam(name = "email") String email) {
        return operations.generarCodigoMovilSMSAplicacionMovil(usuarioApi, passwordApi, movil,email);
    }
    
    
    
    @WebMethod
    public Respuesta validarExisteNumero(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "movil") String movil) {
        return operations.validarExisteNumero(usuarioApi, passwordApi, movil);
    }

    @WebMethod
    public RespuestaListadoTipoCuentaBancaria getTipoCuentasBancarias(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getTipoCuentasBancarias(usuarioApi, passwordApi);
    }

    @WebMethod
    public RespuestaListadoBancos getBancosDisponibles(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getBancosDisponibles(usuarioApi, passwordApi);
    }

    @WebMethod
    public RespuestaListadoTarjetas getTarjetasDisponibles(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {
        return operations.getTarjetasDisponibles(usuarioApi, passwordApi);

    }

    @WebMethod
    public Respuesta solicitarTarjeta(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "emailContacto") String emailContacto,
            @WebParam(name = "direccionContacto") String direccionContacto,
            @WebParam(name = "telefonoContacto") String telefonoContacto
    ) {
        return operations.solicitarTarjeta(usuarioApi, passwordApi, usuarioId, emailContacto, direccionContacto, telefonoContacto);
    }

    @WebMethod
    public Respuesta activarTarjeta(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "pin") String pin
    ) {
        return operations.activarTarjeta(usuarioApi, passwordApi, usuarioId, pin);
    }

    @WebMethod
    public Respuesta lexis(
            @WebParam(name = "usuarioId") String usuarioId) throws Exception {
        return operations.lexis(usuarioId);
    }

    @WebMethod
    public Respuesta afiliarTarjeta(@WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "numeroTarjeta") String numeroTarjeta,
            @WebParam(name = "fechaVencimiento") String fechaVencimiento,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "cvv") String cvv,
            @WebParam(name = "paisId") String paisId,
            @WebParam(name = "estadoId") String estadoId,
            @WebParam(name = "ciudadId") String ciudadId,
            @WebParam(name = "condadoId") String condadoId,
            @WebParam(name = "codigoPostal") String codigoPostal,
            @WebParam(name = "direccion") String direccion) throws Exception {

        return operations.afiliarTarjeta(usuarioApi, passwordApi, usuarioId, paisId, estadoId, ciudadId, condadoId, codigoPostal, direccion, nombre, numeroTarjeta, fechaVencimiento, tipo, cvv);

    }

    @WebMethod
    public Respuesta afiliarCuenta(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "bancoId") String BancoId,
            @WebParam(name = "cuentaBancariaId") String cuentaBancariaId,
            @WebParam(name = "numeroRuta") String numeroRuta,
            @WebParam(name = "numeroCuenta") String numeroCuenta) throws Exception {

        return operations.afiliarCuenta(usuarioApi, passwordApi, usuarioId,
                BancoId, cuentaBancariaId, numeroRuta, numeroCuenta);
    }

    @WebMethod
    public RespuestaListadoUsuarios getAgentesComercialesPorEmpresa(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "empresaId") String empresaId,
            @WebParam(name = "tipoPersona") String tipoPersona) {
        return operations.getAgentesComercialesPorEmpresa(usuarioApi,
                passwordApi, empresaId, tipoPersona);
    }

    @WebMethod
    public RespuestaGuardarUsuario guardarAgenteComercial(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "usuarioEmpresaId") String usuarioEmpresaId,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "credencial") String credencial,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "fechaNacimiento") String fechaNacimiento,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "paisId") String paisId,
            @WebParam(name = "estadoId") String estadoId,
            @WebParam(name = "ciudadId") String ciudadId,
            @WebParam(name = "condadoId") String condadoId,
            @WebParam(name = "codigoPostal") String codigoPostal,
            @WebParam(name = "link") String link,
            @WebParam(name = "fechaVencimientoDocumento") String fechaVencimientoDocumento,
            @WebParam(name = "estado") String estado,
            @WebParam(name = "tipoDocumento") String tipoDocumento,
            @WebParam(name = "numeroDocumento") String numeroDocumento,
            @WebParam(name = "numeroIdentificacion") String numeroIdentificacion,
            @WebParam(name = "telefono") String telefono) {
        logger.debug("guardar usuario");
        return operations.guardarAgenteComercial(usuarioApi, passwordApi,
                usuarioId, usuarioEmpresaId, nombre, apellido, credencial,
                email, movil, fechaNacimiento, direccion, paisId, estadoId,
                ciudadId, condadoId, codigoPostal, link,
                fechaVencimientoDocumento, estado, tipoDocumento,
                numeroDocumento, numeroIdentificacion, telefono);

    }

    @WebMethod
    public RespuestaListadoEstados getEstadosUsuario(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi) {

        return operations.obtenerEstadosUsuario(usuarioApi, passwordApi);
    }

    @WebMethod
    public Respuesta compraAlocoins(
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "cantidad") Double cantidad) throws Exception {
        logger.debug("acreditarAlocoins");
        return operations.compraAlocoins(usuarioId, cantidad);
    }

    @WebMethod
    public Respuesta transferirAlocoins(
            @WebParam(name = "usuarioId1") String usuarioId1,
            @WebParam(name = "usuarioId2") String usuarioId2,
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "alocoins") String alocoins
    ) {
        logger.debug("transferirAlocoins");
        return operations.transferirAlocoins(usuarioId1, usuarioId2, usuarioApi, passwordApi, alocoins);

    }

    @WebMethod
    public Respuesta recuperarPassword(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "idioma") String idioma,
            @WebParam(name = "email") String email,
            @WebParam(name = "cliente") String clente) {
        return operations.recuperarPassword(usuarioApi, passwordApi, idioma,
                email, clente);
    }
        @WebMethod
        public RespuestaNuevoToken loginSocialNetwork(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "ip") String ip) {
        return operations.loginSocialNetwork(usuarioApi, passwordApi, email, movil, ip);
    }

        
    @WebMethod
    public RespuestaGuardarUsuario guardarUsuarioSocialNetwork(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") String usuarioId,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "email") String email,
            @WebParam(name = "movil") String movil,
            @WebParam(name = "codigoValidacionMovil") String codigoValidacionMovil){
         byte [] b = new byte[0];
        
         
         return operations.guardarUsuario(usuarioApi, passwordApi, null,
                nombre, apellido, "Alo123+", email, movil, "01-08-1975",
                "", "1", "", "", "", "",
                codigoValidacionMovil, null,b,"http://llamadas.alodiga.com/site/welcome",true);

    }
    
        @WebMethod
    public RespuestaUsuario validarPin(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "pin") String pin) {
        return operations.validarPin(usuarioApi, passwordApi, usuarioId,pin);
    }
    
    
    @WebMethod
    public String testEcnript(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "textValue") String textValue) {
        return operations.testEncript(usuarioApi, passwordApi,textValue);
    }
    
    @WebMethod
    public String testDesEcnript(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "textValue") String textValue) {
        return operations.testDesencript(usuarioApi, passwordApi,textValue);
    }
    
     @WebMethod
    public RespuestaUsuario listadoProductosPorUsuario(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId) {
        return operations.listadoProductosPorUsuario(usuarioApi, passwordApi,usuarioId);
    }
    
    @WebMethod
    public void sendMailTest()
            {
         operations.sendmailTest();
    }
    
    @WebMethod
    public void sendSmsTest()
            {
         operations.sendSmsTest();
    }
    
    @WebMethod
    public Respuesta sendSmsSimbox(
            
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "number") String number,
            @WebParam(name = "text") String text){
        return operations.sendSmsSimbox(usuarioApi, passwordApi, number, text);
    }
    
    
    @WebMethod
    public Respuesta cambiarCredencialAplicacionMovil(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "credencial") String credencial) {
        return operations.cambiarCredencialAplicacionMovil(usuarioApi, passwordApi, usuarioId,
                credencial);
    }
    
    @WebMethod
    public Respuesta cambiarCredencialAplicacionMovilEmailOrPhone(
            @WebParam(name = "usuarioApi") String usuarioApi,
            @WebParam(name = "passwordApi") String passwordApi,
            @WebParam(name = "phoneOrEmail") String phoneOrEmail,
            @WebParam(name = "credencial") String credencial) {
        return operations.cambiarCredencialAplicacionMovilEmailOrPhone(usuarioApi, passwordApi, phoneOrEmail,
                credencial);
    }
    
    
}
