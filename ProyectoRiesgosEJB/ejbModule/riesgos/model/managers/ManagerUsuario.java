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

	// ---------------------------------------------ORIGEN------------------------------------------------
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
	
	
	
	
	
	
}
