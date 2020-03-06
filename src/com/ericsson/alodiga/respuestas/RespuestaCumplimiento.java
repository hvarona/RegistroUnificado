package com.ericsson.alodiga.respuestas;

import com.alodiga.wallet.ws.Cumplimient;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ericsson.alodiga.model.Usuario;

@XmlRootElement(name = "RespuestaUsuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class RespuestaCumplimiento extends Respuesta implements Serializable {

	private static final long serialVersionUID = -4678921246061451710L;
	public Cumplimient cumplimient;
	
	public RespuestaCumplimiento() {
		super();
	}
	
	public RespuestaCumplimiento (CodigoRespuesta codigo) {
		super(new Date(), codigo.getCodigo(), codigo.name());
		this.cumplimient = null;
	}
	
	public RespuestaCumplimiento(CodigoRespuesta codigo, String mensajeRespuesta) {
		super(new Date(), codigo.getCodigo(), mensajeRespuesta);
		this.cumplimient = null;
	}

	public RespuestaCumplimiento(CodigoRespuesta codigo, String mensajeRespuesta, Cumplimient cumplimient) {
		super(new Date(), codigo.getCodigo(), mensajeRespuesta);
		this.cumplimient = cumplimient;
	}
	
	

}
