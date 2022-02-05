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

import invernadero.controller.JSFUtil;
import invernadero.controller.seguridades.BeanSegLogin;
import invernadero.model.compras.managers.ManagerCompras;
import invernadero.model.core.entities.Ciudad;
import invernadero.model.core.entities.Cliente;
import invernadero.model.core.entities.ComprasCab;
import invernadero.model.core.entities.ComprasDet;
import invernadero.model.core.entities.Producto;
import invernadero.model.core.entities.ProformasCab;
import invernadero.model.core.entities.Proveedor;
import invernadero.model.core.entities.SegUsuario;
import invernadero.model.seguridades.managers.ManagerSeguridades;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named
@SessionScoped
public class BeanAdmin implements Serializable {

	@EJB
	private ManagerSeguridades mSeg;
	@EJB
	private ManagerCompras mCompras;
	private List<Ciudad> listaCiudades;
	private List<Proveedor> listaProveedores;
	private List<Producto> listaProductos;
	private List<ComprasCab> listaComprasCab;
	private List<ComprasDet> listaComprasDet;
	private List<SegUsuario> listaUsuarios;

	// Variables Ciudad
	private Ciudad nuevaCiudad;
	private Ciudad edicionCiudad;

	// Variables Proveedor
	private Proveedor nuevoProveedor;
	private Proveedor edicionProveedor;
	private int ciudadSeleccionado;

	// Variables Producto
	private Producto nuevoProducto;
	private Producto edicionProducto;

	// Variables ComprasCab

	private ComprasCab edicionComprasCab;

	// Variables ComprasDet
	private ComprasCab compraCabSeleccionada;
	private int productoSeleccionado;

	// ComprasDetalle
	private ComprasDet nuevoDetalle;
	private List<ComprasDet> listaDetalle;
	private int productoIngreso;
	private int cantidadIngreso;
	private double precioIngreso;
	private double totalDetalle;
	// ComprasCabecera
	private ComprasCab nuevaCompra;
	private int proveedorIngreso;
	private Date fechaMinima;
	private boolean ivaIngreso;
	private Date fechaIngreso;

	private int idSegUsuarioSeleccionado;
	@Inject
	private BeanSegLogin beanSegLogin;

	public BeanAdmin() {
	}

	@PostConstruct
	public void inicializar() {
		listaCiudades = mCompras.findAllCiudades();
		listaProveedores = mCompras.findAllProveedores();
		listaProductos = mCompras.findAllProductos();
		listaComprasCab = mCompras.findAllComprasCab();

		nuevaCiudad = mCompras.inicializarCiudad();
		nuevoProveedor = mCompras.inicializarProveedor();
		nuevoProducto = mCompras.inicializarProducto();
	}

	// -----------------------------------PRODUCTOS-------------------------------------------------
	// Insertar
	public void actionListenerInsertarProducto() {
		try {
			mCompras.insertarProducto(beanSegLogin.getLoginDTO(), nuevoProducto);
			JSFUtil.crearMensajeINFO("Producto creado");
			listaProductos = mCompras.findAllProductos();
			nuevoProducto = mCompras.inicializarProducto();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Cargar pagina de Actualizar
	public String actionSeleccionarEdicionProducto(Producto producto) {
		edicionProducto = producto;
		return "producto_edicion";
	}

	// Actualizar
	public void actionListenerActualizarProducto() {
		try {
			mCompras.actualizarProducto(beanSegLogin.getLoginDTO(), edicionProducto);
			listaProductos = mCompras.findAllProductos();
			JSFUtil.crearMensajeINFO("Producto actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Eliminar
	public void actionListenerEliminarProducto(int idProducto) {
		try {
			mCompras.eliminarProducto(beanSegLogin.getLoginDTO(), idProducto);
			listaProductos = mCompras.findAllProductos();
			JSFUtil.crearMensajeINFO("Producto eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
}
