/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericsson.alodiga.respuestas;

/**
 *
 * @author ltoro
 */
public class RespuestaListadoTarjeta {
    private Long id;
    private Float numeroTarjeta;

    public RespuestaListadoTarjeta(Long id, Float numeroTarjeta) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Float numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    

   
    

}
