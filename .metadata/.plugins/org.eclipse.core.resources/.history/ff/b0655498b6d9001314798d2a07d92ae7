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
public class LoginControlerBean {
	private UserDao userDao;
	private static final String LOGIN_PATTERN = "[a-zA-Z0-9-._]";

	private Pattern pattern;
	private Matcher matcher;

	public LoginControlerBean() {
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

	public boolean checkLogin(UserSubmissionModelBean userSubmitted) {

		// Vérifier les propriétés de l'utilisateur
		boolean validation = true;

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

		// ajout de l'utilisateur à la base de données
		return validation;
	}

}
