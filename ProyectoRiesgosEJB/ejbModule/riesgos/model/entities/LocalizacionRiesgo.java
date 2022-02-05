package riesgos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the localizacion_riesgo database table.
 * 
 */
@Entity
@Table(name="localizacion_riesgo")
@NamedQuery(name="LocalizacionRiesgo.findAll", query="SELECT l FROM LocalizacionRiesgo l")
public class LocalizacionRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="localizacion_riesgo_id", unique=true, nullable=false)
	private Integer localizacionRiesgoId;

	@Column(name="localizacion_riesgo_canton", nullable=false, length=50)
	private String localizacionRiesgoCanton;

	@Column(name="localizacion_riesgo_coordenadas", nullable=false, length=50)
	private String localizacionRiesgoCoordenadas;

	@Column(name="localizacion_riesgo_pais", nullable=false, length=50)
	private String localizacionRiesgoPais;

	@Column(name="localizacion_riesgo_parroquia", nullable=false, length=50)
	private String localizacionRiesgoParroquia;

	@Column(name="localizacion_riesgo_provincia", nullable=false, length=50)
	private String localizacionRiesgoProvincia;

	@Column(name="localizacion_riesgo_zona", nullable=false, length=50)
	private String localizacionRiesgoZona;

	//bi-directional many-to-one association to Riesgo
	@OneToMany(mappedBy="localizacionRiesgo")
	private List<Riesgo> riesgos;

	public LocalizacionRiesgo() {
	}

	public Integer getLocalizacionRiesgoId() {
		return this.localizacionRiesgoId;
	}

	public void setLocalizacionRiesgoId(Integer localizacionRiesgoId) {
		this.localizacionRiesgoId = localizacionRiesgoId;
	}

	public String getLocalizacionRiesgoCanton() {
		return this.localizacionRiesgoCanton;
	}

	public void setLocalizacionRiesgoCanton(String localizacionRiesgoCanton) {
		this.localizacionRiesgoCanton = localizacionRiesgoCanton;
	}

	public String getLocalizacionRiesgoCoordenadas() {
		return this.localizacionRiesgoCoordenadas;
	}

	public void setLocalizacionRiesgoCoordenadas(String localizacionRiesgoCoordenadas) {
		this.localizacionRiesgoCoordenadas = localizacionRiesgoCoordenadas;
	}

	public String getLocalizacionRiesgoPais() {
		return this.localizacionRiesgoPais;
	}

	public void setLocalizacionRiesgoPais(String localizacionRiesgoPais) {
		this.localizacionRiesgoPais = localizacionRiesgoPais;
	}

	public String getLocalizacionRiesgoParroquia() {
		return this.localizacionRiesgoParroquia;
	}

	public void setLocalizacionRiesgoParroquia(String localizacionRiesgoParroquia) {
		this.localizacionRiesgoParroquia = localizacionRiesgoParroquia;
	}

	public String getLocalizacionRiesgoProvincia() {
		return this.localizacionRiesgoProvincia;
	}

	public void setLocalizacionRiesgoProvincia(String localizacionRiesgoProvincia) {
		this.localizacionRiesgoProvincia = localizacionRiesgoProvincia;
	}

	public String getLocalizacionRiesgoZona() {
		return this.localizacionRiesgoZona;
	}

	public void setLocalizacionRiesgoZona(String localizacionRiesgoZona) {
		this.localizacionRiesgoZona = localizacionRiesgoZona;
	}

	public List<Riesgo> getRiesgos() {
		return this.riesgos;
	}

	public void setRiesgos(List<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	public Riesgo addRiesgo(Riesgo riesgo) {
		getRiesgos().add(riesgo);
		riesgo.setLocalizacionRiesgo(this);

		return riesgo;
	}

	public Riesgo removeRiesgo(Riesgo riesgo) {
		getRiesgos().remove(riesgo);
		riesgo.setLocalizacionRiesgo(null);

		return riesgo;
	}

}