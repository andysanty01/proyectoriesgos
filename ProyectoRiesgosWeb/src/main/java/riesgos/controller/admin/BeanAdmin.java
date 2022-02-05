package riesgos.controller.admin;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import riesgos.model.entities.SegUsuario;
import riesgos.model.entities.TipoRiesgo;
import riesgos.model.managers.ManagerAdmin;
import riesgos.model.seguridades.ManagerSeguridades;



@Named
@SessionScoped
public class BeanAdmin implements Serializable {

	@EJB
	private ManagerSeguridades mSeg;
	@EJB
	private ManagerAdmin mAdmin;
	private List<TipoRiesgo> listaTipoRiesgo;
	
	private List<SegUsuario> listaUsuarios;

	// Variables Tipo Riesgo
	private TipoRiesgo nuevaTipoRiesgo;
	private TipoRiesgo edicionTipoRiesgo;



	
	public BeanAdmin() {
	}

	@PostConstruct
	public void inicializar() {
		listaTipoRiesgo = mAdmin.findAllTipoRiesgos();


		nuevaTipoRiesgo = mAdmin.inicializarTipo();

	}

	// -----------------------------------TIPO RIESGO-------------------------------------------------
	// Insertar
	public void actionListenerInsertarTipoRiesgo() {
		try {
			mAdmin.insertarTipoRiesgo(nuevaTipoRiesgo);
			JSFUtil.crearMensajeINFO("Tipo Riesgo creado");
			listaTipoRiesgo = mAdmin.findAllTipoRiesgos();
			nuevaTipoRiesgo = mAdmin.inicializarTipo();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Cargar pagina de Actualizar
	public String actionSeleccionarEdicionTipoRiesgo(TipoRiesgo tipo) {
		edicionTipoRiesgo = tipo;
		return "tipo_edicion";
	}

	// Actualizar
	public void actionListenerActualizarTipoRiesgo() {
		try {
			mAdmin.actualizarTipoRiesgo(edicionTipoRiesgo);
			listaTipoRiesgo = mAdmin.findAllTipoRiesgos();
			JSFUtil.crearMensajeINFO("Tipo Riesgo actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Eliminar
	public void actionListenerEliminarTipoRiesgo(int idTipoRiesgo) {
		try {
			mAdmin.eliminarTipoRiesgo(idTipoRiesgo);
			listaTipoRiesgo = mAdmin.findAllTipoRiesgos();
			JSFUtil.crearMensajeINFO("TipoRiesgo eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
}
