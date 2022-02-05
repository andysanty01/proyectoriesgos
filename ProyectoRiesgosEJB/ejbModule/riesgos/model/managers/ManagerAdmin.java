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

import invernadero.model.core.entities.Ciudad;
import invernadero.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerClientes
 */
@Stateless
@LocalBean
public class ManagerAdmin {
	@EJB
	private ManagerDAO mDAO;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerAdmin() {
	}

	// ---------------------------------------------ORIGEN------------------------------------------------
	// Inicializar
	public Ciudad inicializarCiudad() {
		Ciudad ciudad = new Ciudad();
		ciudad.setCiuNombre("");
		return ciudad;
	}

	// Listar
	public List<Ciudad> findAllCiudades() {
		return mDAO.findAll(Ciudad.class);
	}

	// Insertar
	public void insertarCiudad(LoginDTO loginDTO, Ciudad nuevaCiudad) throws Exception {
		mDAO.insertar(nuevaCiudad);
		mAuditoria.mostrarLog(loginDTO, getClass(), "insertarCiudad",
				"Ciudad: " + nuevaCiudad.getCiuNombre() + " agregada con éxito");
	}

	// Actualizar
	public void actualizarCiudad(LoginDTO loginDTO, Ciudad edicionCiudad) throws Exception {
		Ciudad ciudad = (Ciudad) mDAO.findById(Ciudad.class, edicionCiudad.getCiuId());

		ciudad.setCiuNombre(edicionCiudad.getCiuNombre());
		mDAO.actualizar(ciudad);
		mAuditoria.mostrarLog(loginDTO, getClass(), "actualizarCiudad",
				"se actualizó la ciudad " + edicionCiudad.getCiuNombre());
	}

	// Eliminar
	public void eliminarCiudad(LoginDTO loginDTO, int idCiudad) throws Exception {
		Ciudad ciudad = (Ciudad) mDAO.findById(Ciudad.class, idCiudad);
		if (ciudad.getProveedors().size() > 0)
			throw new Exception("No se puede elimininar la ciudad porque tiene proveedores registrados.");
		mDAO.eliminar(Ciudad.class, ciudad.getCiuId());
		mAuditoria.mostrarLog(loginDTO, getClass(), "eliminarCiudad", "se eliminó la ciudad " + ciudad.getCiuId());
	}
	
	
	//AQUI CODIGO BRUS
	
	
	
	
	
}
