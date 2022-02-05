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

import riesgos.model.entities.OrigenRiesgo;
import riesgos.model.entities.SegUsuario;
import riesgos.model.managers.ManagerAdmin;
import riesgos.model.entities.TipoRiesgo;
import riesgos.model.seguridades.ManagerSeguridades;



@Named
@SessionScoped
public class BeanAdmin implements Serializable {

	@EJB
	private ManagerSeguridades mSeg;
	@EJB
	private ManagerAdmin mAdmin;
	private List<OrigenRiesgo> listaOrigenRiesgos;
	private List<SegUsuario> listaUsuarios;

	// Variables Origen
	private OrigenRiesgo nuevoOrigenRiesgo;
	private OrigenRiesgo edicionOrigenRiesgo;
	private List<TipoRiesgo> listaTipoRiesgo;
	
	private List<SegUsuario> listaUsuarios;

	// Variables Tipo Riesgo
	private TipoRiesgo nuevaTipoRiesgo;
	private TipoRiesgo edicionTipoRiesgo;

	// Variables Nivel Riesgo
	private List<NivelR> listaNivelRiesgo;
	private NivelRiesgo nuevoNivelRiesgo;
	private NivelRiesgo edicionNivelRiesgo;

	// Variables Area Riesgo
	private List<AreaRiesgo> listaAreaRiesgo;
	private AreaRiesgo nuevoAreaRiesgo;
	private AreaRiesgo edicionAreaRiesgo;

	
	public BeanAdmin() {
	}

	@PostConstruct
	public void inicializar() {
		listaOrigenRiesgos = mAdmin.findAllOrigenRiesgos();
		nuevoOrigenRiesgo = mAdmin.inicializarOrigenRiesgo();
		
	
		listaTipoRiesgo = mAdmin.findAllTipoRiesgos();
		nuevaTipoRiesgo = mAdmin.inicializarTipo();
		
		// NIvel Riesgo
		listaNivelRiesgo = mAdmin.findAllNivelRiesgos();
		nuevoNivelRiesgo = mAdmin.inicializarNivelRiesgo();
		
		//
		listaAreaRiesgo = mAdmin.findAllAreaRiesgos();
		nuevoAreaRiesgo = mAdmin.inicializarAreaRiesgo();

	}

	// -----------------------------------TIPO RIESGO-------------------------------------------------
	// -----------------------------------ORIGEN-------------------------------------------------
	// Insertar
	public void actionListenerInsertarOrigenRiesgo() {
		try {
			mAdmin.insertarOrigenRiesgo(nuevoOrigenRiesgo);
			JSFUtil.crearMensajeINFO("OrigenRiesgo creado");
			listaOrigenRiesgos = mAdmin.findAllOrigenRiesgos();
			nuevoOrigenRiesgo = mAdmin.inicializarOrigenRiesgo();
			
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
	public String actionSeleccionarEdicionOrigenRiesgo(OrigenRiesgo origenRiesgo) {
		edicionOrigenRiesgo = origenRiesgo;
		return "origen_edicion";
	}

	// Actualizar
	public void actionListenerActualizarOrigenRiesgo() {
		try {
			mAdmin.actualizarOrigenRiesgo(edicionOrigenRiesgo);
			listaOrigenRiesgos = mAdmin.findAllOrigenRiesgos();
			JSFUtil.crearMensajeINFO("OrigenRiesgo actualizado.");
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
	public void actionListenerEliminarOrigenRiesgo(int idOrigenRiesgo) {
		try {
			mAdmin.eliminarOrigenRiesgo(idOrigenRiesgo);
			listaOrigenRiesgos = mAdmin.findAllOrigenRiesgos();
			JSFUtil.crearMensajeINFO("OrigenRiesgo eliminado.");
			
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
	
	
	
	//---------------------------------------AREA RISGO --------------------------------
	//Insertar
	public void actionListenerInsertarAreaRiesgo() {
		try {
			mAdmin.insertarAreaRiesgo(nuevoAreaRiesgo);
			JSFUtil.crearMensajeINFO("Area Riesgo creado");
			listaAreaRiesgo = mAdmin.findAllAreaRiesgos();
			nuevoAreaRiesgo = mAdmin.inicializarAreaRiesgo();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Actualizar
		public void actionListenerActualizarAreaRiesgo() {
			try {
				mAdmin.actualizarAreaRiesgo(edicionAreaRiesgo);
				listaAreaRiesgo = mAdmin.findAllAreaRiesgos();
				JSFUtil.crearMensajeINFO("Area Riesgo actualizado.");
			} catch (Exception e) {
				JSFUtil.crearMensajeERROR(e.getMessage());
				e.printStackTrace();
			}
		}
	
		//Eliminar
		
		public void actionListenerEliminarAreaRiesgo(int idAreaRiesgo) {
			try {
				mAdmin.eliminarAreaRiesgo(idAreaRiesgo);
				listaAreaRiesgo = mAdmin.findAllAreaRiesgos();
				JSFUtil.crearMensajeINFO("Area Riesgo eliminado.");
			} catch (Exception e) {
				JSFUtil.crearMensajeERROR(e.getMessage());
				e.printStackTrace();
			}
		}
	
		
		//---------------------------------------NIVEL RISGO --------------------------------
		//Insertar
		public void actionListenerInsertarnNivelRiesgo() {
			try {
				mAdmin.insertarNivelRiesgo(nuevoNivelRiesgo);
				JSFUtil.crearMensajeINFO("Nivel Riesgo creado");
				listaNivelRiesgo = mAdmin.findAllNivelRiesgos();
				nuevoNivelRiesgo = mAdmin.inicializarNivelRiesgo();
			} catch (Exception e) {
				JSFUtil.crearMensajeERROR(e.getMessage());
				e.printStackTrace();
			}
		}
		
		// Actualizar
			public void actionListenerActualizarNivelRiesgo() {
				try {
					mAdmin.actualizarNivelRiesgo(edicionNivelRiesgo);
					listaNivelRiesgo = mAdmin.findAllNivelRiesgos();
					JSFUtil.crearMensajeINFO("Nivel Riesgo actualizado.");
				} catch (Exception e) {
					JSFUtil.crearMensajeERROR(e.getMessage());
					e.printStackTrace();
				}
			}
		
			//Eliminar
			
			public void actionListenerEliminarNivelRiesgo(int idNivelRiesgo) {
				try {
					mAdmin.eliminarNivelRiesgo(idNivelRiesgo);
					listaNivelRiesgo = mAdmin.findAllNivelRiesgos();
					JSFUtil.crearMensajeINFO("NIvel Riesgo eliminado.");
				} catch (Exception e) {
					JSFUtil.crearMensajeERROR(e.getMessage());
					e.printStackTrace();
				}
			}
	
	
	
	
	
}
