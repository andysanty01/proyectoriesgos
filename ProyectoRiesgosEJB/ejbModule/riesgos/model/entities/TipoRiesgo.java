package riesgos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_riesgo database table.
 * 
 */
@Entity
@Table(name="tipo_riesgo")
@NamedQuery(name="TipoRiesgo.findAll", query="SELECT t FROM TipoRiesgo t")
public class TipoRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipo_riesgo_id", unique=true, nullable=false)
	private Integer tipoRiesgoId;

	@Column(name="tipo_riesgo_descripcion", nullable=false, length=50)
	private String tipoRiesgoDescripcion;

	@Column(name="tipo_riesgo_nombre", nullable=false, length=50)
	private String tipoRiesgoNombre;

	//bi-directional many-to-one association to Riesgo
	@OneToMany(mappedBy="tipoRiesgo")
	private List<Riesgo> riesgos;

	public TipoRiesgo() {
	}

	public Integer getTipoRiesgoId() {
		return this.tipoRiesgoId;
	}

	public void setTipoRiesgoId(Integer tipoRiesgoId) {
		this.tipoRiesgoId = tipoRiesgoId;
	}

	public String getTipoRiesgoDescripcion() {
		return this.tipoRiesgoDescripcion;
	}

	public void setTipoRiesgoDescripcion(String tipoRiesgoDescripcion) {
		this.tipoRiesgoDescripcion = tipoRiesgoDescripcion;
	}

	public String getTipoRiesgoNombre() {
		return this.tipoRiesgoNombre;
	}

	public void setTipoRiesgoNombre(String tipoRiesgoNombre) {
		this.tipoRiesgoNombre = tipoRiesgoNombre;
	}

	public List<Riesgo> getRiesgos() {
		return this.riesgos;
	}

	public void setRiesgos(List<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	public Riesgo addRiesgo(Riesgo riesgo) {
		getRiesgos().add(riesgo);
		riesgo.setTipoRiesgo(this);

		return riesgo;
	}

	public Riesgo removeRiesgo(Riesgo riesgo) {
		getRiesgos().remove(riesgo);
		riesgo.setTipoRiesgo(null);

		return riesgo;
	}

}