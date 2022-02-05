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
import riesgos.model.entities.OrigenRiesgo;

/**
 * Session Bean implementation class ManagerClientes
 */
@Stateless
@LocalBean
public class ManagerAdmin {
	@EJB
	private ManagerDAO mDAO;
	
	@EJB
	private ManagerAuditoria mAuditoria;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerAdmin() {
	}

	// ---------------------------------------------ORIGEN------------------------------------------------
	// Inicializar
	public OrigenRiesgo inicializarOrigenRiesgo() {
		OrigenRiesgo origen = new OrigenRiesgo();
		origen.setOrigenRiesgoNombre("");
		origen.setOrigenRiesgoDescripcion("");
		return origen;
	}

	// Listar
	public List<OrigenRiesgo> findAllOrigenRiesgos() {
		return mDAO.findAll(OrigenRiesgo.class);
	}

	// Insertar
	public void insertarOrigenRiesgo(OrigenRiesgo nuevaOrigenRiesgo) throws Exception {
		mDAO.insertar(nuevaOrigenRiesgo);
		mAuditoria.mostrarLog(OrigenRiesgo.class,"insertarOrigenRiesgo", "OrigenRiesgo: " + nuevaOrigenRiesgo.getOrigenRiesgoNombre() + " agregada con �xito");
	}

	// Actualizar
	public void actualizarOrigenRiesgo(OrigenRiesgo edicionOrigenRiesgo) throws Exception {
		OrigenRiesgo origen = (OrigenRiesgo) mDAO.findById(OrigenRiesgo.class, edicionOrigenRiesgo.getOrigenRiesgoId());

		origen.setOrigenRiesgoNombre(edicionOrigenRiesgo.getOrigenRiesgoNombre());
		mDAO.actualizar(origen);
		mAuditoria.mostrarLog(OrigenRiesgo.class,"actualizarOrigenRiesgo", "OrigenRiesgo: " + edicionOrigenRiesgo.getOrigenRiesgoNombre() + " actualizado con �xito");
	}

	// Eliminar
	public void eliminarOrigenRiesgo(int idOrigenRiesgo) throws Exception {
		OrigenRiesgo origen = (OrigenRiesgo) mDAO.findById(OrigenRiesgo.class, idOrigenRiesgo);
		if (origen.getRiesgos().size() > 0)
			throw new Exception("No se puede elimininar la origen porque tiene proveedores registrados.");
		mDAO.eliminar(OrigenRiesgo.class, origen.getOrigenRiesgoId());
		mAuditoria.mostrarLog(OrigenRiesgo.class,"eliminarOrigenRiesgo", "OrigenRiesgo: " + idOrigenRiesgo + " eliminado con �xito");
	}
	
	
	//AQUI CODIGO BRUS
	
	//----------------------------------------------TIPO RIESGO------------
	
	
	// Inicializar
		public TipoRiesgo inicializarTipo() {
			TipoRiesgo tipo = new TipoRiesgo();
			tipo.setTipoRiesgoNombre("");
			tipo.setTipoRiesgoDescripcion("");
			return tipo;
		}

		// Listar
		public List<TipoRiesgo> findAllTipoRiesgos() {
			return mDAO.findAll(TipoRiesgo.class);
		}

		// Insertar
		public void insertarCiudad(LoginDTO loginDTO, TipoRiesgo nuevoTipo) throws Exception {
			mDAO.insertar(nuevoTipo);
			mAuditoria.mostrarLog(loginDTO, getClass(), "insertarTipoRiesgo",
					"Tipo Riesgo: " + nuevoTipo.getTipoRiesgoNombre() + " agregada con �xito");
		}

		// Actualizar
		public void actualizarCiudad(LoginDTO loginDTO, TipoRiesgo edicionTipo) throws Exception {
			TipoRiesgo tipo = (TipoRiesgo) mDAO.findById(TipoRiesgo.class, edicionTipo.getTipoRiesgoId());

			tipo.setTipoRiesgoNombre(edicionTipo.getTipoRiesgoNombre());
			tipo.setTipoRiesgoDescripcion(edicionTipo.getTipoRiesgoDescripcion());
			mDAO.actualizar(tipo);
			mAuditoria.mostrarLog(loginDTO, getClass(), "actualizarTipoRiesgo",
					"se actualiz� el Tipo Riesgo " + edicionTipo.getTipoRiesgoNombre());
		}

		// Eliminar
		public void eliminarCiudad(LoginDTO loginDTO, int idTipoRiesgo) throws Exception {
			TipoRiesgo tipo = (TipoRiesgo) mDAO.findById(TipoRiesgo.class, idTipoRiesgo);
			if (tipo.getRiesgos().size() > 0)
				throw new Exception("No se puede elimininar la Tipo Riesgo porque tiene Riesgos registrados.");
			mDAO.eliminar(TipoRiesgo.class, tipo.getTipoRiesgoId());
			mAuditoria.mostrarLog(loginDTO, getClass(), "eliminarTipoRiesgo", "se elimin� el Tipo Riesgo " + tipo.getTipoRiesgoId());
		}
	
	
	
	
}
