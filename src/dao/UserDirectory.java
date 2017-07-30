package dao;

import java.util.List;

import domain.files;

public class UserDirectory {
	private List<files> list;
	private String prenode_name;
	private int prenode_id;

	public UserDirectory() {
		// TODO Auto-generated constructor stub
	}

	public UserDirectory(List<files> list, String prenode_name, int prenode_id) {
		this.list = list;
		this.prenode_name = prenode_name;
		this.prenode_id = prenode_id;
	}

	public List<files> getList() {
		return list;
	}

	public void setList(List<files> list) {
		this.list = list;
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
