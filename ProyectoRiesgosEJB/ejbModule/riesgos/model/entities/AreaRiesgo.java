package riesgos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the area_riesgo database table.
 * 
 */
@Entity
@Table(name="area_riesgo")
@NamedQuery(name="AreaRiesgo.findAll", query="SELECT a FROM AreaRiesgo a")
public class AreaRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="area_riesgo_id", unique=true, nullable=false)
	private Integer areaRiesgoId;

	@Column(name="area_riesgo_direccion", length=100)
	private String areaRiesgoDireccion;

	@Column(name="area_riesgo_nombre", nullable=false, length=50)
	private String areaRiesgoNombre;

	@Column(name="area_riesgo_telefono", nullable=false, length=10)
	private String areaRiesgoTelefono;

	//bi-directional many-to-one association to Riesgo
	@OneToMany(mappedBy="areaRiesgo")
	private List<Riesgo> riesgos;

	public AreaRiesgo() {
	}

	public Integer getAreaRiesgoId() {
		return this.areaRiesgoId;
	}

	public void setAreaRiesgoId(Integer areaRiesgoId) {
		this.areaRiesgoId = areaRiesgoId;
	}

	public String getAreaRiesgoDireccion() {
		return this.areaRiesgoDireccion;
	}

	public void setAreaRiesgoDireccion(String areaRiesgoDireccion) {
		this.areaRiesgoDireccion = areaRiesgoDireccion;
	}

	public String getAreaRiesgoNombre() {
		return this.areaRiesgoNombre;
	}

	public void setAreaRiesgoNombre(String areaRiesgoNombre) {
		this.areaRiesgoNombre = areaRiesgoNombre;
	}

	public String getAreaRiesgoTelefono() {
		return this.areaRiesgoTelefono;
	}

	public void setAreaRiesgoTelefono(String areaRiesgoTelefono) {
		this.areaRiesgoTelefono = areaRiesgoTelefono;
	}

	public List<Riesgo> getRiesgos() {
		return this.riesgos;
	}

	public void setRiesgos(List<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	public Riesgo addRiesgo(Riesgo riesgo) {
		getRiesgos().add(riesgo);
		riesgo.setAreaRiesgo(this);

		return riesgo;
	}

	public Riesgo removeRiesgo(Riesgo riesgo) {
		getRiesgos().remove(riesgo);
		riesgo.setAreaRiesgo(null);

		return riesgo;
	}

}