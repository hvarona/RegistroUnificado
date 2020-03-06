package com.ericsson.alodiga.respuestas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ericsson.alodiga.model.Ocupacion;
import com.ericsson.alodiga.model.PreguntaSecreta;

@XmlRootElement(name = "RespuestaListadoOcupaciones")
@XmlAccessorType(XmlAccessType.FIELD)
public class RespuestaListadoPreguntaSecretas extends Respuesta implements
		Serializable {

	private static final long serialVersionUID = 6163664204952591466L;
	public List<PreguntaSecreta> datosRespuesta = null;

	public RespuestaListadoPreguntaSecretas() {
		super();
	}

	public RespuestaListadoPreguntaSecretas(CodigoRespuesta codigo) {
		super(new Date(), codigo.getCodigo(), codigo.name());
		this.datosRespuesta = null;
	}

	public RespuestaListadoPreguntaSecretas(CodigoRespuesta codigo,
			String mensajeRespuesta) {
		super(new Date(), codigo.getCodigo(), mensajeRespuesta);
		this.datosRespuesta = null;
	}

	public RespuestaListadoPreguntaSecretas(CodigoRespuesta codigo,
			String mensajeRespuesta, List<PreguntaSecreta> lista) {
		super(new Date(), codigo.getCodigo(), mensajeRespuesta);
		this.datosRespuesta = lista;
	}

}
