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
import riesgos.model.entities.Riesgo;
import riesgos.model.entities.SegUsuario;

/**
 * Session Bean implementation class ManagerClientes
 */
@Stateless
@LocalBean
public class ManagerArea {
	@EJB
	private ManagerDAO mDAO;
	
	@EJB
	private ManagerAuditoria mAuditoria;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerArea() {
	}
		
			
		// ---------------------------------------------RIESGO------------------------------------------------
				// Inicializar
		
				public Riesgo inicializarRiesgo() {
					Riesgo riesgo = new Riesgo();
					riesgo.setRiesgoEstado("Pendiente");
					return riesgo;
				}

				// Listar
				public List<Riesgo> findAllRiesgos() {
					return mDAO.findAll(Riesgo.class);
				}


				// Actualizar
				public void actualizarRiesgo(Riesgo edicionRiesgo) throws Exception {
					Riesgo riesgo = (Riesgo) mDAO.findById(Riesgo.class, edicionRiesgo.getRiesgoId());

					riesgo.setRiesgoEstado(edicionRiesgo.getRiesgoEstado());
					mDAO.actualizar(riesgo);
					mAuditoria.mostrarLog(Riesgo.class,"actualizarRiesgo ", " Riesgo : " + edicionRiesgo.getRiesgoId() + " actualizado con �xito");
				}

	
	
	
	
	
	
}
