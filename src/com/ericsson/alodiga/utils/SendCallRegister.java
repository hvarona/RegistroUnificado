package com.ericsson.alodiga.utils;


import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendCallRegister extends Thread {
    private static final long serialVersionUID = 1L;
    private String movil;
    private String codigo;
    private Long languageId;

    public SendCallRegister(String movil, String codigo_)  {
       this.codigo = codigo_;
       this.movil = movil;
    }
    
     public SendCallRegister(String movil, String codigo_,Long languageId)  {
       this.codigo = codigo_;
       this.movil = movil;
       this.languageId = languageId;
    }
    

    public void run() {
        try {
//            String message = getLangujeByPhoneNumber(movil).equals(Constante.SPANISH_LANGUAGE) ? "Billetera Alodiga, Su codigo de seguridad para el registro es: " + "codigo" : "Alodiga Wallet, Your security code is: " + "codigo" ;
//            TwilioSmsSenderProxy proxy = new TwilioSmsSenderProxy();
//            proxy.sendTwilioSMS(movil, message);
            Utils.getWsaPort().realizarLlamadaRegistro(
                   Utils.obtienePropiedad("usuarioWSA"),
                   Utils.obtienePropiedad("passwordWSA"), "1", movil, codigo);
        } catch (RemoteException ex) {
           ex.printStackTrace();
        }
    }
    
    
//    
//    private Long getLangujeByPhoneNumber(String phone){
//            if(phone.substring(0, 1).equals("1")){
//                return Constante.ENGLISH_LANGUAGE;
//            }else{
//                return Constante.SPANISH_LANGUAGE; 
//            }
//    }
    
    
}
