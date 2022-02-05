package riesgos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel_riesgo database table.
 * 
 */
@Entity
@Table(name="nivel_riesgo")
@NamedQuery(name="NivelRiesgo.findAll", query="SELECT n FROM NivelRiesgo n")
public class NivelRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nivel_riesgo_id", unique=true, nullable=false)
	private Integer nivelRiesgoId;

	@Column(name="nivel_riesgo_nombre", nullable=false, length=50)
	private String nivelRiesgoNombre;

	//bi-directional many-to-one association to Riesgo
	@OneToMany(mappedBy="nivelRiesgo")
	private List<Riesgo> riesgos;

	public NivelRiesgo() {
	}

	public Integer getNivelRiesgoId() {
		return this.nivelRiesgoId;
	}

	public void setNivelRiesgoId(Integer nivelRiesgoId) {
		this.nivelRiesgoId = nivelRiesgoId;
	}

	public String getNivelRiesgoNombre() {
		return this.nivelRiesgoNombre;
	}

	public void setNivelRiesgoNombre(String nivelRiesgoNombre) {
		this.nivelRiesgoNombre = nivelRiesgoNombre;
	}

	public List<Riesgo> getRiesgos() {
		return this.riesgos;
	}

	public void setRiesgos(List<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	public Riesgo addRiesgo(Riesgo riesgo) {
		getRiesgos().add(riesgo);
		riesgo.setNivelRiesgo(this);

		return riesgo;
	}

	public Riesgo removeRiesgo(Riesgo riesgo) {
		getRiesgos().remove(riesgo);
		riesgo.setNivelRiesgo(null);

		return riesgo;
	}

}