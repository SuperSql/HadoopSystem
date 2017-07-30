package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.filesDao;
import domain.files;

public class RenameAction extends ActionSupport {
	private String oldfile_name;
	private String newfile_name;
	private String currentpath;
	private String username;
	private int prenode_id;
	private int index;

	public String getOldfile_name() {
		return oldfile_name;
	}

	public void setOldfile_name(String oldfile_name) {
		this.oldfile_name = oldfile_name;
	}

	public String getNewfile_name() {
		return newfile_name;
	}

	public void setNewfile_name(String newfile_name) {
		this.newfile_name = newfile_name;
	}

	public String getCurrentpath() {
		return currentpath;
	}

	public void setCurrentpath(String currentpath) {
		this.currentpath = currentpath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPrenode_id() {
		return prenode_id;
	}

	public void setPrenode_id(int prenode_id) {
		this.prenode_id = prenode_id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public RenameAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() throws Exception {
		System.out.println(oldfile_name);
		System.out.println(newfile_name);
		System.out.println(currentpath);
		System.out.println(username);
		System.out.println(prenode_id);
		System.out.println(index);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		filesDao filesdao = ctx.getBean("filesDao", filesDao.class);
		int file_id = 0;
		List<files> list = filesdao.listFiles();
		List<files> listpersonal = filesdao.listFilesPersonal(list, username);
		List<files> listcurrent = filesdao.showDirectoryByID(listpersonal, prenode_id);
		for (int i = 0; i < listcurrent.size(); i++) {
			if (listcurrent.get(i).getPrenode_id() == prenode_id
					&& listcurrent.get(i).getFile_name().equals(oldfile_name)) {
				file_id = listcurrent.get(i).getFile_id();
				System.out.println(file_id + "==============================================");
			}
		}
		if (filesdao.judgeLabel(username)) {
			filesdao.rename(file_id, currentpath, oldfile_name, newfile_name);
		} else {
		}
		list = filesdao.listFiles();
		listpersonal = filesdao.listFilesPersonal(list, username);
		listcurrent = filesdao.showDirectoryByID(listpersonal, prenode_id);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		request.setAttribute("username", username);
		request.setAttribute("list", list);
		request.setAttribute("listpersonal", listpersonal);
		request.setAttribute("listcurrent", listcurrent);
		request.setAttribute("index", index);
		request.setAttribute("idindex", prenode_id);
		return "showfiles";
	}
}
