package riesgos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the origen_riesgo database table.
 * 
 */
@Entity
@Table(name="origen_riesgo")
@NamedQuery(name="OrigenRiesgo.findAll", query="SELECT o FROM OrigenRiesgo o")
public class OrigenRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="origen_riesgo_id", unique=true, nullable=false)
	private Integer origenRiesgoId;

	@Column(name="origen_riesgo_descripcion", nullable=false, length=50)
	private String origenRiesgoDescripcion;

	@Column(name="origen_riesgo_nombre", nullable=false, length=50)
	private String origenRiesgoNombre;

	//bi-directional many-to-one association to Riesgo
	@OneToMany(mappedBy="origenRiesgo")
	private List<Riesgo> riesgos;

	public OrigenRiesgo() {
	}

	public Integer getOrigenRiesgoId() {
		return this.origenRiesgoId;
	}

	public void setOrigenRiesgoId(Integer origenRiesgoId) {
		this.origenRiesgoId = origenRiesgoId;
	}

	public String getOrigenRiesgoDescripcion() {
		return this.origenRiesgoDescripcion;
	}

	public void setOrigenRiesgoDescripcion(String origenRiesgoDescripcion) {
		this.origenRiesgoDescripcion = origenRiesgoDescripcion;
	}

	public String getOrigenRiesgoNombre() {
		return this.origenRiesgoNombre;
	}

	public void setOrigenRiesgoNombre(String origenRiesgoNombre) {
		this.origenRiesgoNombre = origenRiesgoNombre;
	}

	public List<Riesgo> getRiesgos() {
		return this.riesgos;
	}

	public void setRiesgos(List<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	public Riesgo addRiesgo(Riesgo riesgo) {
		getRiesgos().add(riesgo);
		riesgo.setOrigenRiesgo(this);

		return riesgo;
	}

	public Riesgo removeRiesgo(Riesgo riesgo) {
		getRiesgos().remove(riesgo);
		riesgo.setOrigenRiesgo(null);

		return riesgo;
	}

}