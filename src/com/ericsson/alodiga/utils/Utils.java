package com.ericsson.alodiga.utils;


import com.alodiga.wallet.ws.Transaction;
import com.ericcson.alodiga.bean.APIOperations;
import com.ericsson.alodiga.model.Cuenta;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.ericsson.alodiga.model.Direccion;
import com.ericsson.alodiga.model.Ocupacion;
import com.ericsson.alodiga.model.TipoDocumento;
import com.ericsson.alodiga.model.Usuario;
import com.ericsson.alodiga.respuestas.CodigoRespuesta;
import com.ericsson.alodiga.respuestas.Respuesta;
import com.icon.mw.ws.AloDiga;
import com.icon.mw.ws.AloDigaServiceLocator;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.AdditionalInfoType;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.AssignmentInfo;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.AssignmentType;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.BasicHttpBinding_ISearchStub;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.ClientContext;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.Date;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.GenderType;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.IDType;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.ISearch;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputAdditionalInfo;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputAddress;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputEntity;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputEntityType;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputID;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputName;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputPhone;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputRecord;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.InputText;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.SearchConfiguration;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.SearchInput;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.SearchResults;
import com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.XGServicesLocator;
import com.mysql.jdbc.BalanceStrategy;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Utils {
    
    static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){

	        public boolean verify(String string, SSLSession ssls) {
    return true;
}
	    });
	}

    private static Properties messages;
    private static Properties validationMessages;
    private static String ALODIGA_WALLET_ADDRESS = "alodigawallet@alodiga.com";

    private static final Logger logger = Logger.getLogger(Utils.class);

    public static String generarCodigoRandom(int longitud) {
        String codigoGenerado = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            codigoGenerado += "" + r.nextInt(10);
            i++;
        }
        System.out.println("codGenerado: ----> " + codigoGenerado);
        return codigoGenerado;
    }

    public static String generarNumeroDeCuenta() {
        return "256344" + Utils.generarCodigoRandom(10);

    }

    private static AloDiga wsaPort;

    /**
     * Devuelve una instancia para la comunicación con el web service de AloDiga
     *
     * @return {@link AuthPos}
     * @throws ServiceException
     */
    public static AloDiga getWsaPort() {
        try {
            if (wsaPort == null) {
                AloDigaServiceLocator locator = new AloDigaServiceLocator();
                locator.setAloDigaPortEndpointAddress(obtienePropiedad("webServiceAlodigaUrl"));
                wsaPort = locator.getAloDigaPort();
            }
        } catch (ServiceException se) {
            logger.error("Error en el webservice", se);
        }
        return wsaPort;
    }

    public static String obtienePropiedad(String key) {
        try {
            if (messages == null) {
                messages = new Properties();
                String path = Utils.class.getClassLoader()
                        .getResource("config.properties").getFile();
                messages.load(new FileInputStream(path));
            }
            return messages.getProperty(key).trim();
        } catch (IOException ex) {
            return "(El sistema no puede encontrar el archivo .properties)";
        }
    }

    public static String obtieneMensaje(String key) {
        try {
            if (validationMessages == null) {
                validationMessages = new Properties();
                String path = Utils.class.getClassLoader()
                        .getResource("ValidationMessages.properties").getFile();
                validationMessages.load(new FileInputStream(path));
            }
            return validationMessages.getProperty(key).trim();
        } catch (IOException ex) {
            return "(El sistema no puede encontrar el archivo .properties)";
        }
    }

    private static boolean enviarCorreoConfirmacionAloBenefit(String idioma,
            Usuario usuario) {
        File imagenMembresia = null;
        File imagenMembresiaBorrar = null;
        String mensajeConfirmacion = "ES";
        if (idioma.equalsIgnoreCase("ES")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_BENEFIT_ES");
            imagenMembresia = new File(Utils.class.getClassLoader()
                    .getResource("tarjeta_alobenefit_es.png").getFile());
        }
        if (idioma.equalsIgnoreCase("EN")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_BENEFIT_EN");
            imagenMembresia = new File(Utils.class.getClassLoader()
                    .getResource("tarjeta_alobenefit_en.png").getFile());
        }
        if (imagenMembresia != null) {
            BufferedImage image;
            try {
                image = ImageIO.read(imagenMembresia);
            } catch (IOException e) {
                logger.error("Problema al leer imagen de membresia Alobenefit",
                        e);
                return false;
            }
            Graphics g = image.getGraphics();
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString(usuario.getMovil(), 280, 170);
            g.drawString(usuario.getNombre() + " " + usuario.getApellido(), 55,
                    125);
            g.dispose();
            try {
                ImageIO.write(image, "png", imagenMembresiaBorrar = new File(
                        imagenMembresia.getAbsolutePath()
                        + generarCodigoRandom(10) + ".png"));
            } catch (IOException e) {
                logger.error(
                        "Problema al escribir imagen de membresia Alobenefit",
                        e);
                return false;
            }
        }
        String cuerpoCorreo = String.format(mensajeConfirmacion,
                usuario.getNombre() + " " + usuario.getApellido(), "");
        logger.debug("alobenefit-registro-Mensaje-a-enviar: " + cuerpoCorreo);
        boolean correoEnviado = EnvioCorreo.enviarCorreoHtml(
                new String[]{usuario.getEmail()}, "Registro AloBenefit",
                cuerpoCorreo, Utils.obtienePropiedad("mail.user"),
                imagenMembresiaBorrar);
        imagenMembresiaBorrar.delete();
        return correoEnviado;
    }

    private static boolean enviarCorreoConfirmacionPorAplicacion(String idioma,
            Usuario usuario, String aplicacion) {
        String mensajeConfirmacion = "ES";
        if (idioma.equalsIgnoreCase("ES")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_"
                    + aplicacion + "_ES");

        }
        if (idioma.equalsIgnoreCase("EN")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_"
                    + aplicacion + "_EN");

        }
        String cuerpoCorreo = String.format(mensajeConfirmacion,
                usuario.getNombre() + " " + usuario.getApellido(), "");
        logger.debug(aplicacion + "-registro-Mensaje-a-enviar: " + cuerpoCorreo);
        EnvioCorreo.enviarCorreoHtml(new String[]{usuario.getEmail()},
                "Registro " + aplicacion, cuerpoCorreo,
                Utils.obtienePropiedad("mail.user"), null);
        return true;
    }

    private static boolean enviarCorreoConfirmacionAloCash(String idioma,
            Usuario usuario) {
        File imagenMembresia = null;
        File imagenMembresiaBorrar = null;
        String mensajeConfirmacion = "ES";
        String numeroCuenta = usuario.getCuenta().getNumeroCuenta();
        if (idioma.equalsIgnoreCase("ES")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_ALOCASH_ES");
            imagenMembresia = new File(Utils.class.getClassLoader()
                    .getResource("tarjeta_rrp_es.png").getFile());
        }
        if (idioma.equalsIgnoreCase("EN")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_ALOCASH_EN");
            imagenMembresia = new File(Utils.class.getClassLoader()
                    .getResource("tarjeta_rrp_en.png").getFile());
        }
        if (imagenMembresia != null) {
            BufferedImage image;
            try {
                image = ImageIO.read(imagenMembresia);
            } catch (IOException e) {
                logger.error(
                        "Problema al escribir imagen de membresia Alocash", e);
                return false;
            }
            Graphics g = image.getGraphics();
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.drawString(String.format(
                    Utils.obtienePropiedad("FORMATO_TARJETA_ALOCASH"),
                    numeroCuenta.substring(0, 2), numeroCuenta.substring(2, 6),
                    numeroCuenta.substring(6, 10)), 105, 400);
            String sNombreTarjetaHabiente = usuario.getNombre().toUpperCase()
                    + " " + usuario.getApellido().toUpperCase();

            if (sNombreTarjetaHabiente.length() > 26) {
                sNombreTarjetaHabiente = sNombreTarjetaHabiente
                        .substring(0, 25);
                g.setFont(new Font("Arial", Font.BOLD, 40));
            } else {
                g.setFont(new Font("Arial", Font.BOLD, 45));
            }
            g.drawString(sNombreTarjetaHabiente, 135, 600);
            g.setFont(new Font("Arial", Font.PLAIN, 50));
            g.drawString("EXPIRES", 135, 510);
            g.setFont(new Font("Arial", Font.BOLD, 50));
			// TODO Colocar fecha vencimiento?
            // TODO Colocar codigo Tarjeta?
            // g.drawString(sFechaVencimiento, 400, 510);
            // g.drawString(sCodigoTarjeta, 1830, 235);
            // insertar nombre, pin y fecha vencimiento
            g.dispose();
            try {
                ImageIO.write(image, "png", imagenMembresiaBorrar = new File(
                        imagenMembresia.getAbsolutePath()
                        + generarCodigoRandom(10) + ".png"));
            } catch (IOException e) {
                logger.error(
                        "Problema al escribir imagen de membresia Alocash", e);
                return false;
            }
        }
        String cuerpoCorreo = String.format(mensajeConfirmacion,
                usuario.getNombre() + " " + usuario.getApellido(), "");
        logger.debug("alobenefit-registro-Mensaje-a-enviar: " + cuerpoCorreo);
        EnvioCorreo.enviarCorreoHtml(new String[]{usuario.getEmail()},
                "Registro AloBenefit", cuerpoCorreo,
                Utils.obtienePropiedad("mail.user"), imagenMembresiaBorrar);
        imagenMembresiaBorrar.delete();
        return true;
    }

    public static boolean enviarCorreoConfirmacion(String idioma,
            Usuario usuario, String aplicacion) {
        if (aplicacion.equals("ALOBENEFIT")) {
            return enviarCorreoConfirmacionAloBenefit(idioma, usuario);
        } else if (aplicacion.equals("ALOCASH")) {
            return enviarCorreoConfirmacionAloCash(idioma, usuario);
        } else {
            return enviarCorreoConfirmacionPorAplicacion(idioma, usuario,
                    aplicacion);
        }
    }

    public static void enviarCorreoBloqueo(String idioma, Usuario usuario) {
        String mensaje = null;
        String subject = null;
        if (idioma.equalsIgnoreCase("ES")) {
            mensaje = obtienePropiedad("INTENTOS_LOGIN_ES");
            subject = "Cuenta bloqueada";
        } else if (idioma.equalsIgnoreCase("EN")) {
            mensaje = obtienePropiedad("INTENTOS_LOGIN_EN");
            subject = "Blocked account";
        }

        EnvioCorreo.enviarCorreoHtml(new String[]{usuario.getEmail()},
                subject, mensaje, Utils.obtienePropiedad("mail.user"), null);
    }

    public static void enviarCorreoSolicitarTarjeta(String idioma, Usuario usuario) {
        String mensaje = null;
        String subject = null;
        if (idioma.equalsIgnoreCase("ES")) {
            mensaje = "Solicitud de Tarjeta";
            subject = "Solicitud de Tarjeta";
        }

        EnvioCorreo.enviarCorreoHtml(new String[]{usuario.getEmail()},
                subject, mensaje, Utils.obtienePropiedad("mail.user"), null);
    }

    public static void enviarCorreoActivacionTarjeta(String idioma, Usuario usuario) {
        String mensaje = null;
        String subject = null;
        if (idioma.equalsIgnoreCase("ES")) {
            mensaje = "Activacion de Tarjeta";
            subject = "Activacion de Tarjeta";
        }

        EnvioCorreo.enviarCorreoHtml(new String[]{usuario.getEmail()},
                subject, mensaje, Utils.obtienePropiedad("mail.user"), null);
    }

    public static void enviarCorreoTransferenciaAlocoins(String idioma, Usuario usuario, String mensaje) {

        String subject = null;
        if (idioma.equalsIgnoreCase("ES")) {

            subject = "Transferencia de Alocoins";
        }

        EnvioCorreo.enviarCorreoHtml(new String[]{usuario.getEmail()},
                subject, mensaje, Utils.obtienePropiedad("mail.user"), null);
    }

    public static boolean isNumeroValido(String numero) {
        return (numero.matches("[0-9]+"));
    }

    public static void enviarCorreoRecuperarCredencial(String idioma,
            String email) {
        String mensaje = null;
        String subject = null;
        if (idioma.equalsIgnoreCase("ES")) {
            mensaje = obtienePropiedad("MENSAJE_RECUPERAR_CREDENCIAL_ES");
            subject = "Cambiar credencial";
        } else if (idioma.equalsIgnoreCase("EN")) {
            mensaje = obtienePropiedad("MENSAJE_RECUPERAR_CREDENCIAL_EN");
            subject = "Password reset";
        }

        String correoEncriptado = Encryptor.encrypt(
                obtienePropiedad("ENC_KEY"),
                obtienePropiedad("ENC_INIT_VALUE"), email);

        mensaje = String.format(mensaje, correoEncriptado, correoEncriptado);

        EnvioCorreo.enviarCorreoHtml(new String[]{email}, subject, mensaje,
                Utils.obtienePropiedad("mail.user"), null);
    }

    public static boolean isDecimalValido(String numero) {
        return numero.matches("[0-9]+(\\.[0-9][0-9]?)?");
    }

    public static boolean isStringValido(String string) {
        return !(string == null || string.equals(""));
    }

    static XGServicesLocator xglocator = new XGServicesLocator();

    static BasicHttpBinding_ISearchStub searchStub;

    public static void main(String[] args) {

        Usuario usuario = new Usuario();
        usuario.setApellido("Frank");
        usuario.setNombre("Diaz");
        usuario.setFechaNacimiento(new java.util.Date());
        usuario.setEmail("kerwin2821@gmail.com");
        usuario.setGenero("M");

        TipoDocumento td = new TipoDocumento();
        td.setNombre("PASAPORTE");
        td.setTipoDocumentoId(2);
        usuario.setTipoDocumento(td);
        usuario.setNumeroDocumento("1234567890");

        Direccion direccion = new Direccion();
        direccion.setCiudadId(1);
        direccion.setCodigoPostal("1234");
        direccion.setDireccion("14th St.");
        direccion.setEstadoId(1);
        direccion.setPaisId(1);
        usuario.setDireccion(direccion);

        Ocupacion ocupacion = new Ocupacion();
        ocupacion.setNombre("None");
        usuario.setOcupacion(ocupacion);

        try {
            System.out.println(esUsuarioFraudulento(usuario));
        } catch (Exception e) {
            e.printStackTrace();
        }
        enviarCorreUsuarioNuevo("ES", usuario, "alodiga");

    }

    public static boolean esUsuarioFraudulento(Usuario usuario)
            throws Exception {
        xglocator.setBasicHttpBinding_ISearchEndpointAddress(xglocator
                .getBasicHttpBinding_ISearchAddress());
        try {
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("https.proxyHost", "192.168.15.7");
            System.getProperties().put("https.proxyPort", "8080");
            ISearch s = xglocator.getBasicHttpBinding_ISearch();
            System.out.println("setting client context");
            ClientContext cc = new ClientContext(
                    obtienePropiedad("LN_CLIENT_ID"), null, null, null,
                    obtienePropiedad("LN_PASSWORD"), null,
                    obtienePropiedad("LN_USER_ID"));
            System.out.println("setting user data");
            Calendar cal = Calendar.getInstance();
            cal.setTime(usuario.getFechaNacimiento());
            Date fechaNacimiento = new Date();
            fechaNacimiento.setDay(cal.get(Calendar.DAY_OF_MONTH));
            fechaNacimiento.setMonth(cal.get(Calendar.MONTH));
            fechaNacimiento.setYear(cal.get(Calendar.YEAR));
            System.out.println("setting iaiDOB");
            InputAdditionalInfo iaiDOB = new InputAdditionalInfo(
                    fechaNacimiento, "", AdditionalInfoType.DOB, "");
            System.out.println("setting iaiOcupacion");
            InputAdditionalInfo iaiOcupacion = new InputAdditionalInfo(null,
                    "", AdditionalInfoType.Occupation, usuario.getOcupacion()
                    .getNombre());
            System.out.println("setting user data");
            InputEntity ie = new InputEntity();
            ie.setGender(usuario.getGenero().equalsIgnoreCase("M") ? GenderType.Male
                    : GenderType.Female);
            String primerNombre = "";
            String segundoNombre = "";
            if (usuario.getNombre().contains(" ")) {
                String[] nombres = usuario.getNombre().split(" ");
                if (nombres.length > 1) {
                    primerNombre = nombres[0];
                    segundoNombre = nombres[1];
                }
            }
            ie.setName(new InputName(primerNombre, "", "", usuario.getApellido(), segundoNombre, ""));

            ie.setEntityType(InputEntityType.Individual);
            ie.setAdditionalInfo(new InputAdditionalInfo[]{iaiDOB,
                iaiOcupacion});
            InputAddress ia = new InputAddress();
            ia.setFullAddress(usuario.getDireccion().getDireccion());
			// TODO obtener ciudad no id .... obtener pais no id ... obtener
            // estado no id
            ia.setPostalCode(usuario.getDireccion().getCodigoPostal());

            ie.setAddresses(new InputAddress[]{ia});

            InputID ii = new InputID();
            ii.setType(obtenerTipoDocumento(usuario.getTipoDocumento()));
            ii.setNumber(usuario.getNumeroDocumento());
            ie.setIDs(new InputID[]{ii});

            InputPhone ip = new InputPhone();
            ip.setNumber(usuario.getMovil());
            ie.setPhones(new InputPhone[]{ip});
            InputText it = new InputText();
            InputRecord ir = new InputRecord(null, ie, 0l, it);
            AssignmentInfo ai = new AssignmentInfo(null, false,
                    new String[]{obtienePropiedad("LN_ROLE")},
                    AssignmentType.Role);

            SearchConfiguration sc = new SearchConfiguration(ai, null, null,
                    null, obtienePropiedad("LN_PREDEFINED_SEARCH_NAME"), null,
                    false);

            SearchInput si = new SearchInput("1", new InputRecord[]{ir});
            System.out.println("pre search");
            SearchResults searchResult = s.search(cc, sc, si);
            return (searchResult.getRecords() != null && searchResult
                    .getRecords().length > 0);

        } catch (Exception e) {
            logger.error("Error al verificar usuario con LexisNexis", e);
            throw e;
        }
    }

    private static String obtenerCiudad(String ciudadId) {
        // TODO
        return "";
    }

    private static String obtenerEstado(String ciudadId) {
        // TODO
        return "";
    }

    private static String obtenerPais(String ciudadId) {
        // TODO
        return "";
    }

    private static IDType obtenerTipoDocumento(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getNombre().equalsIgnoreCase("LICENCIA")) {
            return IDType.DriversLicense;
        } else if (tipoDocumento.getNombre().equalsIgnoreCase("PASAPORTE")) {
            return IDType.Passport;
        } else if (tipoDocumento.getNombre().equalsIgnoreCase("SSN")) {
            return IDType.SSN;
        }
        return IDType.TaxID;
    }

    public static boolean enviarCorreUsuarioNuevo(String idioma, Usuario usuario, String link) {
        // TODO Auto-generated method stub
        String mensajeConfirmacion = "ES";
        if (idioma.equalsIgnoreCase("ES")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_ES");

        }
        if (idioma.equalsIgnoreCase("EN")) {
            mensajeConfirmacion = obtienePropiedad("MENSAJE_CONFIRMACION_EN");

        }

        String correoEncriptado = Encryptor.encrypt(
                obtienePropiedad("ENC_KEY"),
                obtienePropiedad("ENC_INIT_VALUE"), usuario.getEmail());

        String cuerpoCorreo = String.format(mensajeConfirmacion,
                usuario.getNombre() + " " + usuario.getApellido(), link, correoEncriptado, link, correoEncriptado);
        logger.debug("-registro-Mensaje-a-enviar: " + cuerpoCorreo);
        EnvioCorreo.enviarCorreoHtml(new String[]{usuario.getEmail()},
                "Registro ", cuerpoCorreo, Utils.obtienePropiedad("mail.user"),
                null);
        return true;
    }
    
        //REGISTRO
        public static Mail enviarCorreUsuarioNuevoAplicacionMovil(String idioma, Usuario usuario) {
        String hello = "Hola";
        String subject = "Gracias por registrarse en AlodigaWallet";
        String text1 = "Gracias por registrarse en AlodigaWallet, ahora podr&aacute; disfrutar de todos los productos de su Billetera M&oacute;vil";
        String text2 = "Datos de su registro: ";
        String name = "Nombre: ";
        String lastName= "Apellido: ";
        String Email= "Email: ";
        String Credencial= "Contraseña: ";
        String numberMovil= "N&uacute;mero Celular: ";
        String date = "Fecha: ";
        String account_number = "N&uacute;mero de cuenta: ";
        String moreInfo = "Para mayor informaci&oacute;n visite";
        String mailInvite = "Le invitamos a seguir disfrutando los beneficios y de los atractivos productos y servicios que le ofrece Alodiga.";
        String thanks = "Gracias por preferirnos, Alodiga Mejora tu vida";
        String messageFooter1 = "Este mensaje ha sido enviado desde una cuenta de correo electr&oacute;nico exclusivamente de notificaciones que no admite mensajes. No responda esta comunicaci&oacute;n.";
        String allRights = "Todos los derechos reservados";
            if (idioma.equalsIgnoreCase("EN")) {
            hello = "Hello";
            subject = "Thank you for registering in AlodigaWallet";
            text1 = "Thank you for registering with AlodigaWallet, now you can enjoy all the products of your Mobile Wallet";
            text2 = "Registration data: ";
            name = "Name: ";
            lastName = "Last Name: ";
            Email = "Email: ";
            Credencial="Password: ";
            numberMovil = "Movil Number: ";
            date = "Date:";
            account_number = "Account Number: ";
            moreInfo = "Form more info visit";
            mailInvite = "We invite you to continue enjoying the benefits and attractive products and services offered by Alodiga.";
            thanks = "Thank you for choosing Alodiga, Alodiga Mejora tu vida";
            messageFooter1 = "This message was sent from an email distributor solely for notification messages that are not supported. Do not respond to this communication";
            allRights = "All rights reserved";

        }            
          
        String style1 = "style='font:13px/0.6em Arial,Helvetica,sans-serif,lighter; color: #666; font-size:13px;'";
        String style2 = "style='background-color: #07b49c;color:#ffff;font:12px/1.8em Arial,Helvetica,sans-serif,lighter;font-weight:bold;padding-left:10px'";
        String body = "";
        body = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
        body += "<html xmlns='http://www.w3.org/1999/xhtml'>";
        body += "<head>";
        body += "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><style type='text/css'>.Estilo11 {font:13px/0.6em Arial,Helvetica,sans-serif,lighter; color: #333333; font-size:13px; font-weight:bold;}</style><style type='text/css'>.Estilo12 {font:13px/0.6em Arial,Helvetica,sans-serif,lighter; color: #666; font-size:13px;}</style><style type='text/css'>.EstiloColumn {background-color: #555555;color:#FFBF00;font:12px/1.8em Arial,Helvetica,sans-serif,lighter;font-weight:bold;padding-left:10px}</style>" + "<div align='center'>"
                + "<table width='756' height='600' border='0'>"
                + "<tr><th width='750' height='595'><p>"
                + "<img src='http://sales.alodiga.com/images/img-alodiga-logo.png' align='left' width='114' height='90' longdesc='Logo alodiga' />"
                + "</p><p>&nbsp;</p>" + "<p>&nbsp;</p>"
                + "<table  width='730' border='0' >"
                + "<tr><th width='728' height='20' align='right' bgcolor='#4c8e41' style='color:#FFFF;font:16px/1.8em Arial,Helvetica,sans-serif,lighter;'>" + text2 + "</th></tr>"
                + "<tr><th width='728' height='5' bgcolor='#232323'></th></tr>"
                + "</table>"
                + "<table width='728' border='0'>"
                + "<tr><th width='728'>"
                + "<p align='left' class='Estilo11'><br/><br/>&iexcl;"
                + hello + " " + usuario.getNombre() + ""
                + "!<br/><br/>"
                + text1 + "<br><br></p>"
                + "</th>"
                + "</tr>"
                + "<tr>"
                + "<th><p align='left' style='font: 16px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; font-weight:bold; display: table;  margin: 0; padding:0;' >"
                + text2
                + "</p></th></tr>"
                + "<tr height='3px'><th width='728' bgcolor='#232323'></th></tr>"
                + "<tr>"
                + "<th>"
                + "<div><table width='728' border='0' cellpadding='2' cellspancing='0' style='border:inherit'>"
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + name + "" + usuario.getNombre() +" </div></td>"
                + "</tr>"
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + lastName + "" + usuario.getApellido()+" </div></td>"
                + "</tr>"
                
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + Email + "" + usuario.getEmail()+" </div></td>"
                + "</tr>"
                

                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + numberMovil + "" + usuario.getMovil()+" </div></td>"
                + "</tr>"
                
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + account_number + "" +  usuario.getCuenta().getNumeroCuenta() + "</div></td>"
                
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + date + new Timestamp(new java.util.Date().getTime()) + "</div></td>"
                
                + "<tr height='3px'><th width='728' bgcolor='#232323'></th></tr>"
                + "<tr height='40px'>"
                + "<th height='40px'><div class='Estilo11' align='left'>"
                + moreInfo
                + "<span style='font-size: 13px'> "
                + "<a href='https://www.alodiga.com/'>  www.alodiga.com</a></span></div>"
                + "</th>"
                + "</tr>"
                + "<tr>"
                + "<th height='31' bordercolor='#999999'><div align='center'><p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;' >"
                + thanks
                + "</p>"
                + " <p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>"
                + messageFooter1
                + "</p>"
//                + " <p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>"
//                + messageFooter1
//                + "</p>"
                + "</div>"
                + "</th>"
                + "</tr>"
                + " </table>"
                + "<div align='center'>"
                + "<p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>&copy; Copyright 2013 - Alodiga, C.A. " + allRights + "<br> </div></th></tr></table></div></body></html>";

        Mail mail = new Mail(subject,body);
        mail.setSubject(subject);
        mail.setFrom(ALODIGA_WALLET_ADDRESS);
        mail.setBody(body);
        ArrayList<String> recipients = new ArrayList<String>();
        recipients.add(usuario.getEmail());
        mail.setTo(recipients);
        return mail;
    }
        
        
        //RECUPERAR CONTRASEÑA
        public static Mail enviarCorreRecuperarContraseñaAplicacionMovil(String idioma, Usuario usuario) {
        String hello = "Hola";
        String subject = "Restablece tu Contraseña de tu cuenta Alodiga Wallet";
        String text1 = "Su contraseña se ha cambiado satisfactoriamente";
        String text2 = "Recuperaci&oacute;n de Contraseña: ";
        String name = "Nombre: ";
        String lastName= "Apellido: ";
        String Email= "Email: ";
        String Credencial= "Contraseña: ";
        String date = "Fecha: ";
        String moreInfo = "Para mayor informaci&oacute;n visite";
        String mailInvite = "Le invitamos a seguir disfrutando los beneficios y de los atractivos productos y servicios que le ofrece Alodiga.";
        String thanks = "Gracias por preferirnos, Alodiga Mejora tu vida";
        String messageFooter1 = "Este mensaje ha sido enviado desde una cuenta de correo electr&oacute;nico exclusivamente de notificaciones que no admite mensajes. No responda esta comunicaci&oacute;n.";
        String allRights = "Todos los derechos reservados";
            if (idioma.equalsIgnoreCase("EN")) {
            hello = "Hello";
            subject = "Reset your Alodiga Wallet account password";
            text1 = "Your password has been changed successfully";
            text2 = "Recover password: ";
            name = "Name: ";
            lastName = "Last Name: ";
            Email = "Email: ";
            Credencial="Password: ";
            date = "Date:";
            moreInfo = "Form more info visit";
            mailInvite = "We invite you to continue enjoying the benefits and attractive products and services offered by Alodiga.";
            thanks = "Thank you for choosing Alodiga, Alodiga Mejora tu vida";
            messageFooter1 = "This message was sent from an email distributor solely for notification messages that are not supported. Do not respond to this communication";
            allRights = "All rights reserved";

        }            
          
        String style1 = "style='font:13px/0.6em Arial,Helvetica,sans-serif,lighter; color: #666; font-size:13px;'";
        String style2 = "style='background-color: #07b49c;color:#ffff;font:12px/1.8em Arial,Helvetica,sans-serif,lighter;font-weight:bold;padding-left:10px'";
        String body = "";
        body = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
        body += "<html xmlns='http://www.w3.org/1999/xhtml'>";
        body += "<head>";
        body += "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><style type='text/css'>.Estilo11 {font:13px/0.6em Arial,Helvetica,sans-serif,lighter; color: #333333; font-size:13px; font-weight:bold;}</style><style type='text/css'>.Estilo12 {font:13px/0.6em Arial,Helvetica,sans-serif,lighter; color: #666; font-size:13px;}</style><style type='text/css'>.EstiloColumn {background-color: #555555;color:#FFBF00;font:12px/1.8em Arial,Helvetica,sans-serif,lighter;font-weight:bold;padding-left:10px}</style>" + "<div align='center'>"
                + "<table width='756' height='600' border='0'>"
                + "<tr><th width='750' height='595'><p>"
                + "<img src='http://sales.alodiga.com/images/img-alodiga-logo.png' align='left' width='114' height='90' longdesc='Logo alodiga' />"
                + "</p><p>&nbsp;</p>" + "<p>&nbsp;</p>"
                + "<table  width='730' border='0' >"
                + "<tr><th width='728' height='20' align='right' bgcolor='#4c8e41' style='color:#FFFF;font:16px/1.8em Arial,Helvetica,sans-serif,lighter;'>" + text2 + "</th></tr>"
                + "<tr><th width='728' height='5' bgcolor='#232323'></th></tr>"
                + "</table>"
                + "<table width='728' border='0'>"
                + "<tr><th width='728'>"
                + "<p align='left' class='Estilo11'><br/><br/>&iexcl;"
                + hello + " " + usuario.getNombre() + ""
                + "!<br/><br/>"
                + text1 + "<br><br></p>"
                + "</th>"
                + "</tr>"
                + "<tr>"
                + "<th><p align='left' style='font: 16px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; font-weight:bold; display: table;  margin: 0; padding:0;' >"
                + text2
                + "</p></th></tr>"
                + "<tr height='3px'><th width='728' bgcolor='#232323'></th></tr>"
                + "<tr>"
                + "<th>"
                + "<div><table width='728' border='0' cellpadding='2' cellspancing='0' style='border:inherit'>"
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + name + "" + usuario.getNombre() +" </div></td>"
                + "</tr>"
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + lastName + "" + usuario.getApellido()+" </div></td>"
                + "</tr>"
                
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + Email + "" + usuario.getEmail()+" </div></td>"
                + "</tr>"
                      
                + "<tr height='30px'><td " + style2 + " width='305'><div align='left'>"
                + date + new Timestamp(new java.util.Date().getTime()) + "</div></td>"
                
                + "<tr height='3px'><th width='728' bgcolor='#232323'></th></tr>"
                + "<tr height='40px'>"
                + "<th height='40px'><div class='Estilo11' align='left'>"
                + moreInfo
                + "<span style='font-size: 13px'> "
                + "<a href='https://www.alodiga.com/'>  www.alodiga.com</a></span></div>"
                + "</th>"
                + "</tr>"
                + "<tr>"
                + "<th height='31' bordercolor='#999999'><div align='center'><p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;' >"
                + thanks
                + "</p>"
                + " <p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>"
                + messageFooter1
                + "</p>"
//                + " <p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>"
//                + messageFooter1
//                + "</p>"
                + "</div>"
                + "</th>"
                + "</tr>"
                + " </table>"
                + "<div align='center'>"
                + "<p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>&copy; Copyright 2013 - Alodiga, C.A. " + allRights + "<br> </div></th></tr></table></div></body></html>";

        Mail mail = new Mail(subject,body);
        mail.setSubject(subject);
        mail.setFrom(ALODIGA_WALLET_ADDRESS);
        mail.setBody(body);
        ArrayList<String> recipients = new ArrayList<String>();
        recipients.add(usuario.getEmail());
        mail.setTo(recipients);
        return mail;
    }
        
       
    
    public static void enviarCorreoRecuperarCredencialRRP(String idioma,
            String email) {
        String mensaje = null;
        String subject = null;
        if (idioma.equalsIgnoreCase("ES")) {
            mensaje = obtienePropiedad("MENSAJE_RECUPERAR_CREDENCIALRRP_ES");
            subject = "Cambiar credencial";
        } else if (idioma.equalsIgnoreCase("EN")) {
            mensaje = obtienePropiedad("MENSAJE_RECUPERAR_CREDENCIALRRP_EN");
            subject = "Password reset";
        }

        String correoEncriptado = Encryptor.encrypt(
                obtienePropiedad("ENC_KEY"),
                obtienePropiedad("ENC_INIT_VALUE"), email);

        mensaje = String.format(mensaje, correoEncriptado, correoEncriptado);

        EnvioCorreo.enviarCorreoHtml(new String[]{email}, subject, mensaje,
                Utils.obtienePropiedad("mail.user"), null);
    }
    

    /**
     * Realiza una codificación Hash MD5 al texto pasado por parámetro.
     * @param text	Texto a codificar.
     * @return java.lang.String
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String MD5(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }
    
      /**
     * Codifica los bytes en formato Hexadecimal.
     * @param data	Datos a codificar.
     * @return java.lang.String
     */
    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String sendSmsSimbox(String text, String phoneNumber) {
        HttpsURLConnection connection = null;
        InputStream is = null;
        try {
            Authenticator.setDefault(new MyAuthenticator(Constante.USER_SIMBOX_1, Constante.PASSWORD_SIMBOX_1));
            java.net.URL url = new java.net.URL(Constante.URL_SIMBOX);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(Constante.TYPE_REQUEST_SIMBOX);
            connection.setRequestProperty(Constante.REQUEST_PROPERTIE_CONTENT_TYPE, Constante.REQUEST_PROPERTIE_CONTENT_TYPE_VALUE);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //   REQUEST_PROPERTIE_CONTENT_TYPE_VALUE
            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(getRequestJsonDinstar(text, phoneNumber).toString());
            wr.close();
            //Get Response  
            try {
                is = connection.getInputStream();
            } catch (IOException ioe) {
                if (connection instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) connection;
                    int statusCode = httpConn.getResponseCode();
                    System.out.println(httpConn.getResponseCode());
                    System.out.println(statusCode);
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    
    private static StringBuilder getRequestJsonDinstar(String text,String phoneNumber) {
            StringBuilder builderParam = new StringBuilder();
            builderParam.append("{\"text\":\"");
            builderParam.append(text);
            builderParam.append("\",\"param\":[{\"number\":\"");
            builderParam.append(phoneNumber);
            builderParam.append("\"}]}");
        return builderParam;
    }
    
    
        
    public static Boolean EsNumeroDeTelefono(String value) {
        try {    
            return (value.matches("[+]?\\d*") && value.equals("")==false);
        } catch (NumberFormatException e) {
                
                return false;
        }
   
    }


            
            
            
    
    
    
    
    
}
