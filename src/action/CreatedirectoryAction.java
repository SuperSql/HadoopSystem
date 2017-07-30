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

public class CreatedirectoryAction extends ActionSupport {
	private String file_name;
	private int slevel;
	private String currentpath;
	private String username;
	private int prenode_id;
	private String prenode_name;
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getSlevel() {
		return slevel;
	}

	public void setSlevel(int slevel) {
		this.slevel = slevel;
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

	public String getPrenode_name() {
		return prenode_name;
	}

	public void setPrenode_name(String prenode_name) {
		this.prenode_name = prenode_name;
	}

	public CreatedirectoryAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		filesDao filesdao = ctx.getBean("filesDao", filesDao.class);
		List<files> list = filesdao.listFiles();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFile_id() == prenode_id) {
				if (slevel > list.get(i).getSecurity_level()) {
					slevel = list.get(i).getSecurity_level();
				}
				break;
			}
		}
	

		if (filesdao.judgeLabel(username)) {
			filesdao.createDirectory(currentpath + "/" + file_name, file_name, username, slevel, prenode_name, prenode_id);
			System.out.println("createdirectory action");
		} else {

		}
		list = filesdao.listFiles();
		List<files> listpersonal = filesdao.listFilesPersonal(list, username);
		List<files> listcurrent = filesdao.showDirectoryByID(listpersonal, prenode_id);
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
