package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_trainusers")
public class trainusers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int age;
	private String workclass;
	private String education;
	private String marital_status;
	private String occupation;
	private String race;
	private String sex;
	private String native_country;
	private String label;

	public trainusers() {
	}

	public trainusers(int id, int age, String workclass, String education, String marital_status, String occupation,
			String race, String sex, String native_country, String label) {
		this.id = id;
		this.age = age;
		this.workclass = workclass;
		this.education = education;
		this.marital_status = marital_status;
		this.occupation = occupation;
		this.race = race;
		this.sex = sex;
		this.native_country = native_country;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getWorkclass() {
		return workclass;
	}

	public void setWorkclass(String workclass) {
		this.workclass = workclass;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNative_country() {
		return native_country;
	}

	public void setNative_country(String native_country) {
		this.native_country = native_country;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
