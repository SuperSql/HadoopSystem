package action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.filesDao;

public class RegisterAction extends ActionSupport {
	private String account;
	private String password;
	private String repassword;
	private String email;
	private String age;
	private String workclass;
	private String education;
	private String marital_status;
	private String occupation;
	private String race;
	private String sex;
	private String native_country;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		filesDao filesdao = ctx.getBean("filesDao", filesDao.class);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (account == null || password == null) {
			request.setAttribute("register_message", "帐号和密码不能为空！");
			return "fail";
		} else if (!password.equals(repassword)) {
			request.setAttribute("register_message", "两次输入的密码不一致！");
			return "fail";
		} else if (!isEmail(email)) {
			request.setAttribute("register_message", "电子邮箱格式错误！");
			return "fail";
		} else if (!filesdao.registerAccount(account, password, email, age, workclass, education, marital_status,
				occupation, race, sex, native_country)) {
			request.setAttribute("register_message",account+ "    该帐号已被注册！");
			return "fail";
		}
		request.setAttribute("register_message", account+" 用户");
		return "success";
	}

	public boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
