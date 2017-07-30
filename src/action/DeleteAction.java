package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.filesDao;
import domain.files;

public class DeleteAction extends ActionSupport {
	private String username;
	private String deletefile_name;
	private int prenode_id;
	private int index;
	private String deletePath;

	public String getDeletefile_name() {
		return deletefile_name;
	}

	public void setDeletefile_name(String deletefile_name) {
		this.deletefile_name = deletefile_name;
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

	public String getDeletePath() {
		return deletePath;
	}

	public void setDeletePath(String deletePath) {
		this.deletePath = deletePath;
	}

	public DeleteAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		filesDao filesdao = ctx.getBean("filesDao", filesDao.class);
		int file_id = 0;
		List<files> list = filesdao.listFiles();
		List<files> listpersonal = filesdao.listFilesPersonal(list, username);
		List<files> listcurrent = filesdao.showDirectoryByID(listpersonal, prenode_id);
		for (int i = 0; i < listcurrent.size(); i++) {
			if (listcurrent.get(i).getPrenode_id() == prenode_id
					&& listcurrent.get(i).getFile_name().equals(deletefile_name)) {
				file_id = listcurrent.get(i).getFile_id();
				System.out.println(file_id + "==============================================");
			}
		}
		if (filesdao.judgeLabel(username)) {
			filesdao.delete(list, file_id, deletePath, deletefile_name);
		} else {
			
		}


		list = filesdao.listFiles();
		listpersonal = filesdao.listFilesPersonal(list, username);
		listcurrent = filesdao.showDirectoryByID(listpersonal, prenode_id);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("index", index);
		request.setAttribute("idindex", prenode_id);
		System.out.println("action index=" + index);

		request.setAttribute("username", username);
		request.setAttribute("list", list);
		request.setAttribute("listpersonal", listpersonal);
		request.setAttribute("listcurrent", listcurrent);
		return "showfiles";
	}
}
