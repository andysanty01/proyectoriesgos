package riesgos.model.managers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import riesgos.model.entities.TipoRiesgo;
import riesgos.model.auditoria.managers.ManagerAuditoria;
import riesgos.model.entities.AreaRiesgo;
import riesgos.model.entities.LocalizacionRiesgo;
import riesgos.model.entities.NivelRiesgo;
import riesgos.model.entities.OrigenRiesgo;
import riesgos.model.entities.SegUsuario;

/**
 * Session Bean implementation class ManagerClientes
 */
@Stateless
@LocalBean
public class ManagerUsuario {
	@EJB
	private ManagerDAO mDAO;
	
	@EJB
	private ManagerAuditoria mAuditoria;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerUsuario() {
	}

	// ---------------------------------------------USUARIO------------------------------------------------
	// Inicializar
	public SegUsuario inicializarUsuario() {
		SegUsuario usuario = new SegUsuario();
		usuario.setCodigo("");
		usuario.setApellidos("");
		usuario.setNombres("");
		usuario.setCorreo("");
		usuario.setTelefono("");
		usuario.setClave("");
		usuario.setActivo(true);
		return usuario;
	}

	// Listar
	public List<SegUsuario> findAllSegUsuarios() {
		return mDAO.findAll(SegUsuario.class);
	}

	// Insertar
	public void insertarUsuario(SegUsuario nuevoUsuario) throws Exception {
		mDAO.insertar(nuevoUsuario);
		mAuditoria.mostrarLog(SegUsuario.class,"insertarUsuario", "Usuario: " + nuevoUsuario.getApellidos() + " agregada con �xito");
	}

	// Actualizar
	public void actualizarUsuario(SegUsuario edicionUsuario) throws Exception {
		SegUsuario usuario = (SegUsuario) mDAO.findById(SegUsuario.class, edicionUsuario.getIdSegUsuario());
		
		usuario.setCodigo(edicionUsuario.getCodigo());
		usuario.setApellidos(edicionUsuario.getApellidos());
		usuario.setNombres(edicionUsuario.getNombres());
		usuario.setCorreo(edicionUsuario.getCorreo());
		usuario.setTelefono(edicionUsuario.getTelefono());
		usuario.setClave(edicionUsuario.getClave());
		usuario.setActivo(edicionUsuario.getActivo());
		
		mDAO.actualizar(usuario);
		mAuditoria.mostrarLog(SegUsuario.class,"actualizarUsuario", "Usuario: " + edicionUsuario.getApellidos() + " actualizado con �xito");
	}

	// Eliminar
	public void eliminarUsuario(int idUsuario) throws Exception {
		SegUsuario usuario = (SegUsuario) mDAO.findById(SegUsuario.class, idUsuario);
		if (usuario.getRiesgos().size() > 0)
			throw new Exception("No se puede elimininar la Usuario porque tiene riesgos registrados.");
		mDAO.eliminar(SegUsuario.class, usuario.getIdSegUsuario());
		mAuditoria.mostrarLog(SegUsuario.class,"eliminarUsuario", "Usuario: " + idUsuario + " eliminado con exito");
	}
	
	
	
	
	
	
	
	// ---------------------------------------------LOCALIZACION------------------------------------------------
		// Inicializar
		public LocalizacionRiesgo inicializarlocalizacion() {
			LocalizacionRiesgo local = new LocalizacionRiesgo();
			local.setLocalizacionRiesgoPais("");
			local.setLocalizacionRiesgoProvincia("");
			local.setLocalizacionRiesgoCanton("");
			local.setLocalizacionRiesgoParroquia("");
			local.setLocalizacionRiesgoZona("");
			local.setLocalizacionRiesgoCoordenadas("");

			return local;
		}

		// Listar
		public List<LocalizacionRiesgo> findAllLocalizacionRiesgos() {
			return mDAO.findAll(LocalizacionRiesgo.class);
		}

		// Insertar
		public void insertarlocalizacion(LocalizacionRiesgo nuevolocalizacion) throws Exception {
			mDAO.insertar(nuevolocalizacion);
			mAuditoria.mostrarLog(LocalizacionRiesgo.class,"insertarLocalizacion", "Localizacion: " + nuevolocalizacion.getLocalizacionRiesgoProvincia() + " agregada con �xito");
		}

		// Actualizar
		public void actualizarlocalizacion(LocalizacionRiesgo edicionlocalizacion) throws Exception {
			LocalizacionRiesgo local = (LocalizacionRiesgo) mDAO.findById(LocalizacionRiesgo.class, edicionlocalizacion.getLocalizacionRiesgoId());
			
			local.setLocalizacionRiesgoPais(edicionlocalizacion.getLocalizacionRiesgoPais());
			local.setLocalizacionRiesgoProvincia(edicionlocalizacion.getLocalizacionRiesgoProvincia());
			local.setLocalizacionRiesgoCanton(edicionlocalizacion.getLocalizacionRiesgoCanton());
			local.setLocalizacionRiesgoParroquia(edicionlocalizacion.getLocalizacionRiesgoParroquia());
			local.setLocalizacionRiesgoZona(edicionlocalizacion.getLocalizacionRiesgoZona());
			local.setLocalizacionRiesgoCoordenadas(edicionlocalizacion.getLocalizacionRiesgoCoordenadas());
			
			mDAO.actualizar(local);
			mAuditoria.mostrarLog(LocalizacionRiesgo.class,"actualizarLocalizacion ", "Localizacion Riesgo : " + edicionlocalizacion.getLocalizacionRiesgoProvincia() + " actualizado con �xito");
		}

		// Eliminar
		public void eliminarlocalizacion(int idLocalizacion) throws Exception {
			LocalizacionRiesgo local = (LocalizacionRiesgo) mDAO.findById(LocalizacionRiesgo.class, idLocalizacion);
			if (local.getRiesgos().size() > 0)
				throw new Exception("No se puede elimininar la localizacion porque tiene riesgos registrados.");
			mDAO.eliminar(LocalizacionRiesgo.class, local.getLocalizacionRiesgoId());
			mAuditoria.mostrarLog(LocalizacionRiesgo.class,"eliminarLocalizacion", "localizacion: " + idLocalizacion + " eliminado con exito");
		}
		
	
	
	
	
	
	
}
