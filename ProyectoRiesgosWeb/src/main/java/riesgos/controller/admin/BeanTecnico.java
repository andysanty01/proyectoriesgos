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
public class BeanTecnico implements Serializable {

	@EJB
	private ManagerTecnico mTecnico;

	// Variables Riesgo	
	private Riesgo edicionRiesgo;
	private List<Riesgo> listaRiesgo;
	private int origenSeleccionado;
	private int nivelSelecionado;
	private int areaSelecionado;
	private String codOrigen;
	public BeanTecnico() {

	}

	@PostConstruct
	public void inicializar() {
		listaRiesgo = mTecnico.findAllRiesgos();
		codOrigen = mTecnico.codigoOrigen(Riesgo.class, "riesgo_id");
	}

	// Actualizar
	public void actionListenerActualizarRiesgo() {
		try {
			mTecnico.actualizarRiesgo(edicionRiesgo, origenSeleccionado, nivelSelecionado, areaSelecionado);
			listaRiesgo = mTecnico.findAllRiesgos();
			codOrigen = mTecnico.codigoOrigen(Riesgo.class, "riesgo_id");
			JSFUtil.crearMensajeINFO(" Riesgo actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	// Cargar pagina de Actualizar Usuario
	public String actionSeleccionarEdicionRiesgo(Riesgo riesgo) {
		edicionRiesgo = riesgo;
		return "riesgo_edicion";
	}

}
