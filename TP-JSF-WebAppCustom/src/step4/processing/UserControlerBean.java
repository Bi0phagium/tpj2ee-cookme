package step4.processing;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import step4.model.LoginBean;
import step4.model.UserModelBean;
import step4.dao.fabric.DaoFabric;
import step4.dao.instance.UserDao;
import step4.model.UserSubmissionModelBean;

@ManagedBean
@ApplicationScoped
// Utilisation de application scope afin d'offrir un point d'entrée unique à
// l'ensemble des clients
public class UserControlerBean {
	private UserDao userDao;
	private static final String USERNAME_PATTERN = "^[_A-Za-z0-9-@]+";
	private static final String USERMAIL_PATTERN = "[a-zA-Z0-9-._]+@[a-zA-Z0-9-._].[a-z]+";
	private static final String LOGIN_PATTERN = "[a-zA-Z0-9-._]";
	private static final int AGE = 100;
	
	private Pattern pattern;
	private Matcher matcher;

	public UserControlerBean() {
		this.userDao = DaoFabric.getInstance().createUserDao();
	}

	public String checkUser(LoginBean loginBean) {
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),
				loginBean.getPwd());
		if (user != null) {

			// récupère l'espace de mémoire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();

			// place l'utilisateur dans l'espace de mémoire de JSF
			sessionMap.put("loggedUser", user);

			// redirect the current page
			return "userdisplay.xhtml";
		} else {

			// redirect the current page
			return "userLogin.xhtml";
		}
	}

	public void checkAndAddUser(UserSubmissionModelBean userSubmitted) {

		// Vérifier les propriétés de l'utilisateur
		boolean validation = true;

		pattern = Pattern.compile(USERNAME_PATTERN);
		matcher = pattern.matcher(userSubmitted.getLastname());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("lastname validation failed.",
					"lastname Validation failed please follow the contraint"
							+ USERNAME_PATTERN);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			validation = false;
			throw new ValidatorException(msg);
		}
		
		matcher = pattern.matcher(userSubmitted.getSurname());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("surname validation failed.",
					"surname Validation failed please follow the contraint"
							+ USERNAME_PATTERN);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			validation = false;
			throw new ValidatorException(msg);
		}
		
		if (userSubmitted.getAge()>AGE && userSubmitted.getAge()<0) {
			FacesMessage msg = new FacesMessage("age validation failed.",
					"age Validation failed please follow the contraint"
							+ "age > " + AGE);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			validation = false;
			throw new ValidatorException(msg);
		}

		pattern = Pattern.compile(USERMAIL_PATTERN);
		matcher = pattern.matcher(userSubmitted.getMail());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("mail validation failed.",
					"mail Validation failed please follow the contraint"
							+ USERMAIL_PATTERN);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			validation = false;
			throw new ValidatorException(msg);
		}
		
		pattern = Pattern.compile(LOGIN_PATTERN);
		matcher = pattern.matcher(userSubmitted.getLogin());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("login validation failed.",
					"login Validation failed please follow the contraint"
							+ LOGIN_PATTERN);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			validation = false;
			throw new ValidatorException(msg);
		}
		
		if(!userSubmitted.getPwd().equals(userSubmitted.getpwdConfirmation()))
		{
			FacesMessage msg = new FacesMessage("password validation failed.",
					"password Validation failed please follow the contraint"
							+ " the passwords have to be the same");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			validation = false;
			throw new ValidatorException(msg);
		}
		
		// ajout de l'utilisateur à la base de données
		if(validation)
			this.userDao.addUser(userSubmitted);
	}

}
