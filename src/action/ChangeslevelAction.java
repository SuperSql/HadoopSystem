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

public class ChangeslevelAction extends ActionSupport {
	private int changedslevel;
	private String changeslevelfile_name;
	private String username;
	private int prenode_id;
	private int index;

	public int getChangedslevel() {
		return changedslevel;
	}

	public void setChangedslevel(int changedslevel) {
		this.changedslevel = changedslevel;
	}

	public String getChangeslevelfile_name() {
		return changeslevelfile_name;
	}

	public void setChangeslevelfile_name(String changeslevelfile_name) {
		this.changeslevelfile_name = changeslevelfile_name;
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

	public ChangeslevelAction() {
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
					&& listcurrent.get(i).getFile_name().equals(changeslevelfile_name)) {
				file_id = listcurrent.get(i).getFile_id();
				System.out.println(file_id + "==============================================");
				break;
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFile_id() == prenode_id) {
				if (changedslevel > list.get(i).getSecurity_level()) {
					changedslevel = list.get(i).getSecurity_level();
					System.out.println("prenode slevel " + list.get(i).getSecurity_level());
				}
				break;
			}
		}
		if (filesdao.judgeLabel(username)) {
			filesdao.changeslevel(list, changedslevel, file_id);
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
