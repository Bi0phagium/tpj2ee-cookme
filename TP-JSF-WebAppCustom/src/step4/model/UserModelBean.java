package step4.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
//contrainte BEAN implements Serializable
public class UserModelBean implements Serializable{
	private String lastname;
	private String surname;
	private int age;
	private String mail;
	private String login;
	private String pwd;
	private String pwdConfirmation;
	
	//Contrainte BEAN constructeur sans paramètre
	public UserModelBean() {
	}
	
	public UserModelBean(String lastname,String surname,int age,String mail,String login,String pwd) {
		this.lastname = lastname;
		this.surname = surname;
		this.age = age;
		this.mail = mail;
		this.login = login;
		this.pwd = pwd;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getpwdConfirmation() {
		return pwdConfirmation;
	}

	public void setpwdConfirmation(String pwdConfirmation) {
		this.pwdConfirmation = pwdConfirmation;
	}
	
	
	@Override
	public String toString() {
		return "[SURNAME]:"+this.getSurname()+",[LASTNAME]:"+this.getLastname()+",[AGE]:"+this.getAge()+",[MAIL]:"+this.getMail()+",[LOGIN]:"+this.getLogin()+",[PWD]:"+this.getPwd();
	}
	

}
