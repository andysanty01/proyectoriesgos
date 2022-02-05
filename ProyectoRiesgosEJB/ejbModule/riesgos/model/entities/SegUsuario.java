package riesgos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_usuario database table.
 * 
 */
@Entity
@Table(name="seg_usuario")
@NamedQuery(name="SegUsuario.findAll", query="SELECT s FROM SegUsuario s")
public class SegUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_seg_usuario", unique=true, nullable=false)
	private Integer idSegUsuario;

	@Column(nullable=false)
	private Boolean activo;

	@Column(nullable=false, length=50)
	private String apellidos;

	@Column(nullable=false, length=50)
	private String clave;

	@Column(nullable=false, length=10)
	private String codigo;

	@Column(nullable=false, length=50)
	private String correo;

	@Column(nullable=false, length=50)
	private String nombres;

	@Column(nullable=false, length=10)
	private String telefono;

	//bi-directional many-to-one association to Riesgo
	@OneToMany(mappedBy="segUsuario")
	private List<Riesgo> riesgos;

	//bi-directional many-to-one association to SegAsignacion
	@OneToMany(mappedBy="segUsuario")
	private List<SegAsignacion> segAsignacions;

	public SegUsuario() {
	}

	public Integer getIdSegUsuario() {
		return this.idSegUsuario;
	}

	public void setIdSegUsuario(Integer idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Riesgo> getRiesgos() {
		return this.riesgos;
	}

	public void setRiesgos(List<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	public Riesgo addRiesgo(Riesgo riesgo) {
		getRiesgos().add(riesgo);
		riesgo.setSegUsuario(this);

		return riesgo;
	}

	public Riesgo removeRiesgo(Riesgo riesgo) {
		getRiesgos().remove(riesgo);
		riesgo.setSegUsuario(null);

		return riesgo;
	}

	public List<SegAsignacion> getSegAsignacions() {
		return this.segAsignacions;
	}

	public void setSegAsignacions(List<SegAsignacion> segAsignacions) {
		this.segAsignacions = segAsignacions;
	}

	public SegAsignacion addSegAsignacion(SegAsignacion segAsignacion) {
		getSegAsignacions().add(segAsignacion);
		segAsignacion.setSegUsuario(this);

		return segAsignacion;
	}

	public SegAsignacion removeSegAsignacion(SegAsignacion segAsignacion) {
		getSegAsignacions().remove(segAsignacion);
		segAsignacion.setSegUsuario(null);

		return segAsignacion;
	}

}