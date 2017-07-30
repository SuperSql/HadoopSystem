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

public class ShowfilesAction extends ActionSupport {
	private int file_id;
	private String username;
	private int index;
	// private List<files> listpersonal;
	//
	// public List<files> getListpersonal() {
	// return listpersonal;
	// }
	//
	// public void setListpersonal(List<files> listpersonal) {
	// this.listpersonal = listpersonal;
	// }

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ShowfilesAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() throws Exception {
		// filesDao filesdao = new filesDao();
		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpSession session=ServletActionContext.getRequest().getSession();
		// List<files> listpersonal=(List<files>)
		// request.getAttribute("lisepersonal");
		// List<files> listcurrent = filesdao.showDirectoryByID(listpersonal,
		// file_id);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		filesDao filesdao = ctx.getBean("filesDao", filesDao.class);
		List<files> list = filesdao.listFiles();
		List<files> listpersonal = filesdao.listFilesPersonal(list, username);
		List<files> listcurrent = filesdao.showDirectoryByID(listpersonal, file_id);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("index", index);
		request.setAttribute("idindex", file_id);
		System.out.println("action index=" + index);
		// int index = 0;
		// int[] idindex = new int[500];
		// index = (int) request.getAttribute("index");
		// System.out.println(index);
		// idindex = (int[]) request.getAttribute("idindex");
		request.setAttribute("username", username);
		request.setAttribute("list", list);
		request.setAttribute("listpersonal", listpersonal);
		request.setAttribute("listcurrent", listcurrent);
		return "showfiles";
	}
}
