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
public class BeanArea implements Serializable {

	@EJB
	private ManagerSeguridades mSeg;
	@EJB
	private ManagerArea mArea;
	

	
	// Variables Usuario
	private Riesgo edicionRiesgo;
	private List<Riesgo> listaRiesgo;
	
	
	
	public BeanArea() {
		
	}

	@PostConstruct
	public void inicializar() {
		listaRiesgo = mArea.findAllRiesgos();
		edicionRiesgo = mArea.inicializarRiesgo();
		
		

	}

	
		
		//---------------------------------------USUARIO --------------------------------

		
		// Actualizar
			public void actionListenerActualizarRiesgo() {
				try {
					mArea.actualizarRiesgo(edicionRiesgo);
					listaRiesgo = mArea.findAllRiesgos();
					JSFUtil.crearMensajeINFO("Riesgo actualizado.");
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
