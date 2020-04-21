package com.ericsson.alodiga.utils;


import com.alodiga.massiva.sms.SendSmsMassiva;
import com.alodiga.twilio.sms.services.TwilioSmsSenderProxy;
import com.ericsson.alodiga.model.Usuario;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SendSmsThread extends Thread {

    private static final long serialVersionUID = 1L;
    private String movil;
    private String codigo;
    private Long languageId;
    private Integer sendSmsType;
    private Usuario usuario;

    public SendSmsThread(String movil, String codigo, Integer sendSmsType) {
        this.codigo = codigo;
        this.movil = movil;
        this.sendSmsType = sendSmsType;
    }

    public SendSmsThread(String movil, Usuario usuario, Integer sendSmsType) {
        this.movil = movil;
        this.usuario = usuario;
        this.sendSmsType = sendSmsType;
    }

    public SendSmsThread(String movil, String codigo_, Long languageId) {
        this.codigo = codigo_;
        this.movil = movil;
        this.languageId = languageId;
    }

    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String message = null;

        switch (sendSmsType) {
            case Constante.SEND_TYPE_SMS_REGISTER:
                // code block
                message = getLangujeByPhoneNumber(usuario.getMovil()).equals(Constante.SPANISH_LANGUAGE) ? "Billetera Alodiga, Usted se ha registrado satisfactoriamente: " + sdf.format(timestamp) + " " + usuario.getEmail() : "Alodiga Wallet, You have successfully registered: " + sdf.format(timestamp) + " " + usuario.getEmail();
                break;
            case Constante.SEND_TYPE_SMS_CODIGO_MOVIL:
                // code block
                message = getLangujeByPhoneNumber(movil).equals(Constante.SPANISH_LANGUAGE) ? "Billetera Alodiga, Su codigo de seguridad para el registro es: " + codigo : "Alodiga Wallet, Your security code is: " + codigo;
                break;
            case Constante.SEND_TYPE_SMS_RECUPERAR_CONTRASEÑA:
                // code block
                message = getLangujeByPhoneNumber(movil).equals(Constante.SPANISH_LANGUAGE) ? "Billetera Alodiga, Su codigo de seguridad para la nueva contraseña es: " + codigo : "Alodiga Wallet, Your security code for the new password is: " + codigo;
                break;
        }
        try {
            //String message = getLangujeByPhoneNumber(movil).equals(Constante.SPANISH_LANGUAGE) ? "Billetera Alodiga, Su codigo de seguridad para el registro es: " + codigo : "Alodiga Wallet, Your security code is: " + codigo ;
            //Solo aplica para dos o tres pasises si se desea hacer dinamicamente se debe agregar un plan de numeraciòn
            String countryCode = movil.substring(0, 2);
            if (movil.substring(0, 1).equals("1")) {
                //lo envia por USA
                TwilioSmsSenderProxy proxy = new TwilioSmsSenderProxy();
                proxy.sendTwilioSMS(movil, message);
            } else if (movil.substring(0, 2).equals("58")) {
                //Venezuela  integras con simbox
                //APIOperations aPIOperations = new APIOperations();
                SendSmsMassiva sendSmsMassiva = new SendSmsMassiva();
                try {
                    //aPIOperations.sendSmsSimbox(Constante.USER_API, Constante.PASSWORD_API, message, movil);
                    sendSmsMassiva.sendSmsMassiva(message, movil);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (movil.substring(0, 2).equals("57")) {
                //TODO:
                //Colombia
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    private Long getLangujeByPhoneNumber(String phone) {
        if (phone.substring(0, 1).equals("1")) {
            return Constante.ENGLISH_LANGUAGE;
        } else {
            return Constante.SPANISH_LANGUAGE;
        }
    }

}
