package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_files")
public class files {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String file_name;
	private String file_owner;
	private int security_level;
	private String file_type;
	private int file_id;
	private String prenode_name;
	private int prenode_id;

	public files() {
		// TODO Auto-generated constructor stub
	}

	public files(int id, String file_name, String file_owner, int security_level, String file_type, int file_id,
			String prenode_name, int prenode_id) {
		this.id = id;
		this.file_name = file_name;
		this.file_owner = file_owner;
		this.security_level = security_level;
		this.file_type = file_type;
		this.file_id = file_id;
		this.prenode_name = prenode_name;
		this.prenode_id = prenode_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_owner() {
		return file_owner;
	}

	public void setFile_owner(String file_owner) {
		this.file_owner = file_owner;
	}

	public int getSecurity_level() {
		return security_level;
	}

	public void setSecurity_level(int security_level) {
		this.security_level = security_level;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getPrenode_name() {
		return prenode_name;
	}

	public void setPrenode_name(String prenode_name) {
		this.prenode_name = prenode_name;
	}

	public int getPrenode_id() {
		return prenode_id;
	}

	public void setPrenode_id(int prenode_id) {
		this.prenode_id = prenode_id;
	}

}
