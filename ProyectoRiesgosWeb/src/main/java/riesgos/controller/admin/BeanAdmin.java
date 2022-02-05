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

import invernadero.model.seguridades.managers.ManagerSeguridades;
import riesgos.model.entities.OrigenRiesgo;
import riesgos.model.entities.SegUsuario;
import riesgos.model.managers.ManagerAdmin;

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

	public BeanAdmin() {
	}

	@PostConstruct
	public void inicializar() {
		listaOrigenRiesgos = mAdmin.findAllOrigenRiesgos();

		nuevoOrigenRiesgo = mAdmin.inicializarOrigenRiesgo();
	}

	// -----------------------------------ORIGEN-------------------------------------------------
	// Insertar
	public void actionListenerInsertarOrigenRiesgo() {
		try {
			mAdmin.insertarOrigenRiesgo(nuevoOrigenRiesgo);
			JSFUtil.crearMensajeINFO("OrigenRiesgo creado");
			listaOrigenRiesgos = mAdmin.findAllOrigenRiesgos();
			nuevoOrigenRiesgo = mAdmin.inicializarOrigenRiesgo();
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
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
}
