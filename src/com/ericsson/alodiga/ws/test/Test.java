/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericsson.alodiga.ws.test;

import com.ericsson.alodiga.model.Cuenta;
import com.ericsson.alodiga.model.Usuario;
import com.ericsson.alodiga.utils.AmazonSESSendMail;
import com.ericsson.alodiga.utils.Mail;
import com.ericsson.alodiga.utils.SendMailTherad;
import com.ericsson.alodiga.utils.Utils;

/**
 *
 * @author ltoro
 */
public class Test {
    
    
    public static void main(String[] args){      
        Usuario usuario = new Usuario();
        usuario.setNombre("Kerwin");
        usuario.setApellido("Gomez");
        usuario.setCredencial("231231231");
        usuario.setEmail("moisegrat12@gmail.com");
        usuario.setMovil("584241934005");
        Cuenta cunCuenta = new Cuenta();
        cunCuenta.setNumeroCuenta("01050614154515461528");
        usuario.setCuenta(cunCuenta);
        Mail mail = Utils.enviarCorreUsuarioNuevoAplicacionMovil("ES", usuario);
        System.out.println("body: " + mail.getBody());
        
        SendMailTherad sendMailTherad = new SendMailTherad("ES", usuario, Integer.valueOf("1"));
        sendMailTherad.run();
        
        try {
                    //Envio de Correo Electronico
//                    EnvioCorreo.enviarCorreoHtml(new String[]{mail.getTo().get(0)},
//                mail.getSubject(),  mail.getBody(), Utils.obtienePropiedad("mail.user"), null);
                    
            AmazonSESSendMail.SendMail(mail.getSubject(), mail.getBody(), mail.getTo().get(0));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    
}
