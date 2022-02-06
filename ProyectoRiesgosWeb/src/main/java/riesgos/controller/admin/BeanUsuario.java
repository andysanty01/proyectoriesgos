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
public class BeanUsuario implements Serializable {

	@EJB
	private ManagerSeguridades mSeg;
	@EJB
	private ManagerUsuario mUsuario;
	

	
	// Variables Usuario
	private SegUsuario nuevoUsuario;
	private SegUsuario edicionUsuario;
	private List<SegUsuario> listaUsuarios;
	
	
	// Variables Localiacion
	private LocalizacionRiesgo nuevaLocalizacion;
	private LocalizacionRiesgo edicionLocalizacion;
	private List<LocalizacionRiesgo> listaLocalizacion;
	
	

	// Variables Riesgo
	private Riesgo nuevoRiesgo;
	private Riesgo edicionRiesgo;
	private List<Riesgo> listaRiesgo;
	private int usuarioSelecionado ;
	private int tipoSelecionado ;
	private int localizacionSelecionado ;
	
	
	
	public BeanUsuario() {
		
	}

	@PostConstruct
	public void inicializar() {
		listaUsuarios = mUsuario.findAllSegUsuarios();
		nuevoUsuario = mUsuario.inicializarUsuario();
		
		
		listaLocalizacion = mUsuario.findAllLocalizacionRiesgos();
		nuevaLocalizacion = mUsuario.inicializarlocalizacion();
		
		
		
		
		listaRiesgo = mUsuario.findAllRiesgos();
		nuevoRiesgo = mUsuario.inicializarRiesgo();

	}

	
		
		//---------------------------------------USUARIO --------------------------------
		//Insertar
		public void actionListenerInsertarUsuario() {
			try {
				mUsuario.insertarUsuario(nuevoUsuario);
				JSFUtil.crearMensajeINFO("Usuario creado");
				listaUsuarios = mUsuario.findAllSegUsuarios();
				nuevoUsuario = mUsuario.inicializarUsuario();
			} catch (Exception e) {
				JSFUtil.crearMensajeERROR(e.getMessage());
				e.printStackTrace();
			}
		}
		
		// Actualizar
			public void actionListenerActualizarUsuario() {
				try {
					mUsuario.actualizarUsuario(edicionUsuario);
					listaUsuarios = mUsuario.findAllSegUsuarios();
					JSFUtil.crearMensajeINFO("Usuario actualizado.");
				} catch (Exception e) {
					JSFUtil.crearMensajeERROR(e.getMessage());
					e.printStackTrace();
				}
			}
		
			//Eliminar
			
			public void actionListenerEliminarUsuario(int idUsuario) {
				try {
					mUsuario.eliminarUsuario(idNivelRiesgo);
					listaUsuarios = mUsuario.findAllSegUsuarios();
					JSFUtil.crearMensajeINFO("Usuario eliminado.");
				} catch (Exception e) {
					JSFUtil.crearMensajeERROR(e.getMessage());
					e.printStackTrace();
				}
			}
			
			// Cargar pagina de Actualizar Usuario
			public String actionSeleccionarEdicionUsuario(SegUsuario usuario) {
				edicionUsuario = usuario;
				return "usuario_edicion";
			}
	
	
			// Cargar pagina de Actualizar Tipo Riesgo
			public String actionSeleccionarEdicionTipoRiesgo(TipoRiesgo tipo) {
				edicionTipo = tipo;
				return "tipoRiesgo_edicion";
			}
	
		
			// Cargar pagina de Actualizar NIvel Riesgo
			public String actionSeleccionarEdicionNivelRiesgo(NivelRiesgo nivel) {
				edicionNivel = nivel;
				return "nivelRiesgo_edicion";
			}
	
			
			// Cargar pagina de Actualizar Area Riesgo
			public String actionSeleccionarEdicionAreaRiesgo(AreaRiesgo area) {
				edicionArea = area;
				return "areaRiesgo_edicion";
			}
			
			
			
			
			
			
			
			
			
			
			
			//---------------------------------------LOCALIZACION --------------------------------
			//Insertar
			public void actionListenerInsertarLocalizacion() {
				try {
					mUsuario.insertarlocalizacion(nuevaLocalizacion);
					JSFUtil.crearMensajeINFO("Localizacion Riesgo creado");
					listaLocalizacion = mUsuario.findAllLocalizacionRiesgos();
					nuevaLocalizacion = mUsuario.inicializarlocalizacion();
				} catch (Exception e) {
					JSFUtil.crearMensajeERROR(e.getMessage());
					e.printStackTrace();
				}
			}
			
			// Actualizar
				public void actionListenerActualizarLocaliacion() {
					try {
						mUsuario.actualizarlocalizacion(edicionLocalizacion);
						listaLocalizacion = mUsuario.findAllLocalizacionRiesgos();
						JSFUtil.crearMensajeINFO("Localizacion Riesgo actualizado.");
					} catch (Exception e) {
						JSFUtil.crearMensajeERROR(e.getMessage());
						e.printStackTrace();
					}
				}
			
				//Eliminar
				
				public void actionListenerEliminarLocalizacion(int idLocalizacion) {
					try {
						mUsuario.eliminarlocalizacion(idLocalizacion);
						listaLocalizacion = mUsuario.findAllLocalizacionRiesgos();
						JSFUtil.crearMensajeINFO("Localizacion Riesgo eliminado.");
					} catch (Exception e) {
						JSFUtil.crearMensajeERROR(e.getMessage());
						e.printStackTrace();
					}
				}
				
				// Cargar pagina de Actualizar Usuario
				public String actionSeleccionarEdicionLocalizacion(LocalizacionRiesgo local) {
					edicionLocalizacion = local;
					return "localizacion_edicion";
				}
				
				
				
				
				
				
				
				//---------------------------------------RIESGO --------------------------------
				//Insertar
				public void actionListenerInsertarRiesgo() {
					try {
						mUsuario.insertarRiesgo(nuevoRiesgo,usuarioSelecionado,tipoSelecionado,localizacionSelecionado);
						JSFUtil.crearMensajeINFO("Riesgo  creado");
						listaRiesgo = mUsuario.findAllRiesgos();
						nuevoRiesgo = mUsuario.inicializarRiesgo();
					} catch (Exception e) {
						JSFUtil.crearMensajeERROR(e.getMessage());
						e.printStackTrace();
					}
				}
				
				// Actualizar
					public void actionListenerActualizarRiesgo() {
						try {
							mUsuario.actualizarRiesgo(edicionRiesgo,usuarioSelecionado,tipoSelecionado,localizacionSelecionado);
							listaRiesgo = mUsuario.findAllRiesgos();
							JSFUtil.crearMensajeINFO(" Riesgo actualizado.");
						} catch (Exception e) {
							JSFUtil.crearMensajeERROR(e.getMessage());
							e.printStackTrace();
						}
					}
				
					//Eliminar
					
					public void actionListenerEliminarRiesgo(int idRiesgo) {
						try {
							mUsuario.eliminarRiesgo(idRiesgo);
							listaRiesgo = mUsuario.findAllRiesgos();
							JSFUtil.crearMensajeINFO(" Riesgo eliminado.");
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
