package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.filesDao;
import domain.files;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		filesDao filesdao = ctx.getBean("filesDao", filesDao.class);
		if (filesdao.login(username, password)) {
			List<files> list = filesdao.listFiles();
			List<files> listpersonal = filesdao.listFilesPersonal(list, username);
			List<files> listcurrent = filesdao.showUserDirectory(listpersonal, username);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("username", username);
			request.setAttribute("list", list);
			request.setAttribute("listpersonal", listpersonal);
			request.setAttribute("listcurrent", listcurrent);
			return "showfiles";
		} else {
			return "login_fail";
		}

	}
}
