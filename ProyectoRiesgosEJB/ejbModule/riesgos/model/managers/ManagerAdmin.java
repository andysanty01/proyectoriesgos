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
import riesgos.model.auditoria.ManagerAuditoria;
import riesgos.model.entities.AreaRiesgo;
import riesgos.model.entities.NivelRiesgo;
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
		public void insertarTipoRiesgo(TipoRiesgo nuevoTipo) throws Exception {
			mDAO.insertar(nuevoTipo);
			mAuditoria.mostrarLog(TipoRiesgo.class, "insertarTipoRiesgo","Tipo Riesgo: " + nuevoTipo.getTipoRiesgoNombre() + " agregada con exito");
			
		}

		// Actualizar
		public void actualizarTipoRiesgo(TipoRiesgo edicionTipo) throws Exception {
			TipoRiesgo tipo = (TipoRiesgo) mDAO.findById(TipoRiesgo.class, edicionTipo.getTipoRiesgoId());

			tipo.setTipoRiesgoNombre(edicionTipo.getTipoRiesgoNombre());
			tipo.setTipoRiesgoDescripcion(edicionTipo.getTipoRiesgoDescripcion());
			mDAO.actualizar(tipo);
			mAuditoria.mostrarLog(TipoRiesgo.class, "actualizarTipoRiesgo", "TipoRiesgo: " + edicionTipo.getTipoRiesgoNombre() + " actualizado con �xito");
			
		}

		// Eliminar
		public void eliminarTipoRiesgo(int idTipoRiesgo) throws Exception {
			TipoRiesgo tipo = (TipoRiesgo) mDAO.findById(TipoRiesgo.class, idTipoRiesgo);
			if (tipo.getRiesgos().size() > 0)
				throw new Exception("No se puede elimininar la Tipo Riesgo porque tiene Riesgos registrados.");
			mDAO.eliminar(TipoRiesgo.class, tipo.getTipoRiesgoId());
			mAuditoria.mostrarLog(TipoRiesgo.class, "eliminarTipoRiesgo", "TipoRiesgo: " + idTipoRiesgo +"se elimin� el Tipo Riesgo ");
		}
		
		
		
		
		//----------------------------------------------NIVEL RIESGO------------
		
		
		// Inicializar
			public NivelRiesgo inicializarNivelRiesgo() {
				NivelRiesgo nivel = new NivelRiesgo();
				nivel.setNivelRiesgoNombre("");
				return nivel;
			}

			// Listar
			public List<NivelRiesgo> findAllNivelRiesgos() {
				return mDAO.findAll(NivelRiesgo.class);
			}

			// Insertar
			public void insertarNivelRiesgo(NivelRiesgo nuevoNivel) throws Exception {
				mDAO.insertar(nuevoNivel);
				mAuditoria.mostrarLog(NivelRiesgo.class, "insertarNivelRiesgo", "Nivel Riesgo: " + nuevoNivel.getNivelRiesgoNombre() + " agregada con exito");
			}

			// Actualizar
			public void actualizarNivelRiesgo(NivelRiesgo edicionNivel) throws Exception {
				NivelRiesgo nivel = (NivelRiesgo) mDAO.findById(NivelRiesgo.class, edicionNivel.getNivelRiesgoId());

				nivel.setNivelRiesgoNombre(edicionNivel.getNivelRiesgoNombre());
				mDAO.actualizar(nivel);
				mAuditoria.mostrarLog(NivelRiesgo.class, "actualizarNivelRiesgo","NivelRiesgo: " + edicionNivel.getNivelRiesgoNombre() + " actualizado con �xito");
			}

			// Eliminar
			public void eliminarNivelRiesgo(int idNivelRiesgo) throws Exception {
				NivelRiesgo nivel = (NivelRiesgo) mDAO.findById(NivelRiesgo.class, idNivelRiesgo);
				if (nivel.getRiesgos().size() > 0)
					throw new Exception("No se puede elimininar la Nivel Riesgo porque tiene Riesgos registrados.");
				mDAO.eliminar(NivelRiesgo.class, nivel.getNivelRiesgoId());
				mAuditoria.mostrarLog(NivelRiesgo.class, "eliminarNivelRiesgo", "NivelRiesgo: " + idNivelRiesgo +"se elimin� el Nivel Riesgo ");
			}
	
			
			
			//----------------------------------------------AREA RIESGO------------
			
			
			// Inicializar
				public AreaRiesgo inicializarAreaRiesgo() {
					AreaRiesgo area = new AreaRiesgo();
					area.setAreaRiesgoNombre("");
					area.setAreaRiesgoDireccion("");
					area.setAreaRiesgoTelefono("");
					return area;
				}

				// Listar
				public List<AreaRiesgo> findAllAreaRiesgos() {
					return mDAO.findAll(AreaRiesgo.class);
				}

				// Insertar
				public void insertarAreaRiesgo(AreaRiesgo nuevoArea) throws Exception {
					mDAO.insertar(nuevoArea);
					mAuditoria.mostrarLog(AreaRiesgo.class, "insertarAreaRiesgo", "AreaRiesgo: " + nuevoArea.getAreaRiesgoNombre() + " agregada con exito");
				}

				// Actualizar
				public void actualizarAreaRiesgo(AreaRiesgo edicionArea) throws Exception {
					AreaRiesgo area = (AreaRiesgo) mDAO.findById(AreaRiesgo.class, edicionArea.getAreaRiesgoId());

					area.setAreaRiesgoNombre(edicionArea.getAreaRiesgoNombre());
					area.setAreaRiesgoDireccion(edicionArea.getAreaRiesgoDireccion());
					area.setAreaRiesgoTelefono(edicionArea.getAreaRiesgoTelefono());
					
					mDAO.actualizar(area);
					mAuditoria.mostrarLog(AreaRiesgo.class, "actualizarAreaRiesgo","AreaRiesgo: " + edicionArea.getAreaRiesgoNombre() + " actualizado con �xito");
				}

				// Eliminar
				public void eliminarAreaRiesgo(int idAreaRiesgo) throws Exception {
					AreaRiesgo area = (AreaRiesgo) mDAO.findById(AreaRiesgo.class, idAreaRiesgo);
					if (area.getRiesgos().size() > 0)
						throw new Exception("No se puede elimininar la Area Riesgo porque tiene Riesgos registrados.");
					mDAO.eliminar(AreaRiesgo.class, area.getAreaRiesgoId());
					mAuditoria.mostrarLog(AreaRiesgo.class, "eliminarAreaRiesgo", "AreaRiesgo: " + idAreaRiesgo +"se elimino el Area Riesgo ");
				}
	
	
	
}
