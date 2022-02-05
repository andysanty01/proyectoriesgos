package riesgos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the riesgo database table.
 * 
 */
@Entity
@Table(name="riesgo")
@NamedQuery(name="Riesgo.findAll", query="SELECT r FROM Riesgo r")
public class Riesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="riesgo_id", unique=true, nullable=false)
	private Integer riesgoId;

	@Column(name="riesgo_descripcion", nullable=false, length=50)
	private String riesgoDescripcion;

	@Column(name="riesgo_estado", nullable=false, length=50)
	private String riesgoEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="riesgo_fecha_notificacion", nullable=false)
	private Date riesgoFechaNotificacion;

	@Column(name="riesgo_identificador", length=15)
	private String riesgoIdentificador;

	//bi-directional many-to-one association to AreaRiesgo
	@ManyToOne
	@JoinColumn(name="area_riesgo_id")
	private AreaRiesgo areaRiesgo;

	//bi-directional many-to-one association to LocalizacionRiesgo
	@ManyToOne
	@JoinColumn(name="localizacion_riesgo_id", nullable=false)
	private LocalizacionRiesgo localizacionRiesgo;

	//bi-directional many-to-one association to NivelRiesgo
	@ManyToOne
	@JoinColumn(name="nivel_riesgo_id")
	private NivelRiesgo nivelRiesgo;

	//bi-directional many-to-one association to OrigenRiesgo
	@ManyToOne
	@JoinColumn(name="origen_riesgo_id")
	private OrigenRiesgo origenRiesgo;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_seg_usuario", nullable=false)
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to TipoRiesgo
	@ManyToOne
	@JoinColumn(name="tipo_riesgo_id")
	private TipoRiesgo tipoRiesgo;

	public Riesgo() {
	}

	public Integer getRiesgoId() {
		return this.riesgoId;
	}

	public void setRiesgoId(Integer riesgoId) {
		this.riesgoId = riesgoId;
	}

	public String getRiesgoDescripcion() {
		return this.riesgoDescripcion;
	}

	public void setRiesgoDescripcion(String riesgoDescripcion) {
		this.riesgoDescripcion = riesgoDescripcion;
	}

	public String getRiesgoEstado() {
		return this.riesgoEstado;
	}

	public void setRiesgoEstado(String riesgoEstado) {
		this.riesgoEstado = riesgoEstado;
	}

	public Date getRiesgoFechaNotificacion() {
		return this.riesgoFechaNotificacion;
	}

	public void setRiesgoFechaNotificacion(Date riesgoFechaNotificacion) {
		this.riesgoFechaNotificacion = riesgoFechaNotificacion;
	}

	public String getRiesgoIdentificador() {
		return this.riesgoIdentificador;
	}

	public void setRiesgoIdentificador(String riesgoIdentificador) {
		this.riesgoIdentificador = riesgoIdentificador;
	}

	public AreaRiesgo getAreaRiesgo() {
		return this.areaRiesgo;
	}

	public void setAreaRiesgo(AreaRiesgo areaRiesgo) {
		this.areaRiesgo = areaRiesgo;
	}

	public LocalizacionRiesgo getLocalizacionRiesgo() {
		return this.localizacionRiesgo;
	}

	public void setLocalizacionRiesgo(LocalizacionRiesgo localizacionRiesgo) {
		this.localizacionRiesgo = localizacionRiesgo;
	}

	public NivelRiesgo getNivelRiesgo() {
		return this.nivelRiesgo;
	}

	public void setNivelRiesgo(NivelRiesgo nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}

	public OrigenRiesgo getOrigenRiesgo() {
		return this.origenRiesgo;
	}

	public void setOrigenRiesgo(OrigenRiesgo origenRiesgo) {
		this.origenRiesgo = origenRiesgo;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public TipoRiesgo getTipoRiesgo() {
		return this.tipoRiesgo;
	}

	public void setTipoRiesgo(TipoRiesgo tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

}