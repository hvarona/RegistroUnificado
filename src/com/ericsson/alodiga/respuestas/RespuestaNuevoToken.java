package com.ericsson.alodiga.respuestas;

import com.ericsson.alodiga.model.Tarjeta;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RespuestaNuevoToken")
@XmlAccessorType(XmlAccessType.FIELD)
public class RespuestaNuevoToken extends Respuesta implements Serializable {

	private static final long serialVersionUID = 3824171095981479016L;

	@XmlElement(name = "token")
	public String token;

	public RespuestaNuevoToken() {
		super();
	}

	public RespuestaNuevoToken(CodigoRespuesta codigo) {
		super(new Date(), codigo.getCodigo(), codigo.name());
	}

	public RespuestaNuevoToken(CodigoRespuesta codigo, String mensajeRespuesta) {
		super(new Date(), codigo.getCodigo(), mensajeRespuesta);
	}

	public RespuestaNuevoToken(CodigoRespuesta codigo, String mensajeRespuesta,
			String token) {
		super(new Date(), codigo.getCodigo(), mensajeRespuesta);
		this.token = token;
	}
        

        
}
