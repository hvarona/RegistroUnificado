/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericsson.alodiga.utils;

import com.alodiga.wallet.ws.Transaction;
import com.ericsson.alodiga.model.Usuario;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;




/**
 *
 * @author usuario
 */
public class SendMailTherad extends Thread {

      
    
    
    private String idioma;
    private Usuario usuario;
    private Transaction transaction;
    private Float oldAmount;
    private Float currentAmount;
    private Integer sendType;
    
    
    
    
     /**
     * Método que devuelve el número de ítems (números aleatorios) existentes en la serie
     * @return El número de ítems (números aleatorios) de que consta la serie
     * @param 
     */
    public SendMailTherad(String idioma, Usuario usuario,Integer sendType ) {
    
      this.idioma = idioma;
      this.usuario = usuario;      
      this.sendType = sendType;
              
        
    }

 

    public void run() {
      Mail mail = null; 
        switch (sendType) {
            
            
            case Constante.SEND_TYPE_EMAIL_REGISTER:
            // code block
                mail = Utils.enviarCorreUsuarioNuevoAplicacionMovil("ES", usuario);
                break;  
                
                case Constante.SEND_RECUPERAR_CONTRASEÑA:
            // code block
                mail = Utils.enviarCorreRecuperarContraseñaAplicacionMovil("ES", usuario);
                break;  
            
        }
        
        // Hace el envio
        try {
            //Esto es para hacer el envio con Servidor smtp de alodiga
//              EnvioCorreo.enviarCorreoHtml(new String[]{ mail.getTo().get(0)},
//                mail.getSubject(), mail.getBody(), Utils.obtienePropiedad("mail.user"), null);
            
            //Esto es para hacer el envio con Servidor smtp de amazon
            AmazonSESSendMail.SendMail(mail.getSubject(), mail.getBody(), mail.getTo().get(0));
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SendMailTherad.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

}
