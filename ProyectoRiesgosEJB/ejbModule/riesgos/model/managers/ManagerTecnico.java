package riesgos.model.managers;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import riesgos.model.auditoria.ManagerAuditoria;
import riesgos.model.entities.AreaRiesgo;
import riesgos.model.entities.LocalizacionRiesgo;
import riesgos.model.entities.NivelRiesgo;
import riesgos.model.entities.OrigenRiesgo;
import riesgos.model.entities.Riesgo;
import riesgos.model.entities.SegUsuario;
import riesgos.model.entities.TipoRiesgo;

/**
 * Session Bean implementation class ManagerTecnico
 */
@Stateless
@LocalBean
public class ManagerTecnico {

	@EJB
	private ManagerDAO mDAO;

	@EJB
	private ManagerAuditoria mAuditoria;

	@PersistenceContext
	private EntityManager em;

	public ManagerTecnico() {
		// TODO Auto-generated constructor stub
	}
	
	
	//======================================================================//
	//Listar RiesgoDetalle
    public List<RiesgoDetalle> findAreasByRiesgo(int idRiesgo){
    	return mDAO.findWhere(RiesgoDetalle.class, "o.Riesgo.idRiesgo="+idRiesgo, "o.fechaInicio");
    }
    public RiesgoDetalle inicializarRiesgoDetalle(Riesgo riesgo) {
    	RiesgoDetalle riesdet=new RiesgoDetalle();    	
    	riesgo.setRiesgoEstado("Asignado"); 
    	mDAO.actualizar(riesgo);
    	riesdet.setAreaRiesgo(new AreaRiesgo());    	
    	riesdet.setRiesgo(riesgo);
    	return riesdet;
    }
   
    public void insertarRiesgoDetalle(RiesgoDetalle nuevaArea,int area) throws Exception {    	
    	area.setRiesgo(area);
    	nuevaArea.setRiesgo(usuario);
    	nuevaArea.setAvance(0);
    	mDAO.insertar(nuevaArea);
    } 
	
	//======================================================================//

	// Listar Riesgos
	public List<Riesgo> findAllRiesgos() {
		return mDAO.findAll(Riesgo.class);
	}

	// Actualizar
	public void actualizarRiesgo(Riesgo edicionRiesgo, int idOrigen, int idNivel, int idArea) throws Exception {
		Riesgo riesgo = (Riesgo) mDAO.findById(Riesgo.class, edicionRiesgo.getRiesgoId());
		OrigenRiesgo origen = (OrigenRiesgo) mDAO.findById(TipoRiesgo.class, idOrigen);
		NivelRiesgo nivel = (NivelRiesgo) mDAO.findById(LocalizacionRiesgo.class, idNivel);
		AreaRiesgo area = (AreaRiesgo) mDAO.findById(LocalizacionRiesgo.class, idArea);

		riesgo.setRiesgoIdentificador(identificadorRiesgo(edicionRiesgo));
		riesgo.setRiesgoDescripcion(edicionRiesgo.getRiesgoDescripcion());
		riesgo.setRiesgoFechaNotificacion(edicionRiesgo.getRiesgoFechaNotificacion());
		riesgo.setOrigenRiesgo(origen);
		riesgo.setNivelRiesgo(nivel);
		riesgo.setAreaRiesgo(area);
		riesgo.setRiesgoEstado("Asignado");

		mDAO.actualizar(riesgo);
		mAuditoria.mostrarLog(Riesgo.class, "actualizarRiesgo ",
				"Riesgo : " + edicionRiesgo.getRiesgoDescripcion() + " actualizado con exito");
	}

	/// codigo origen	  
	
	public String identificadorRiesgo(Riesgo riesgoedicion) {
		String identificador="";
		String idRiesgo = riesgoedicion.getRiesgoId().toString();		
		try {
		
			if (riesgoedicion.getOrigenRiesgo().getOrigenRiesgoNombre() == "Natural") {
				identificador = "SGRE-N-" + idRiesgo;				
			} else if (riesgoedicion.getOrigenRiesgo().getOrigenRiesgoNombre() =="Antrópico") {
					identificador = "SGRE-A-" + idRiesgo;
				} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return identificador;
	}

}
