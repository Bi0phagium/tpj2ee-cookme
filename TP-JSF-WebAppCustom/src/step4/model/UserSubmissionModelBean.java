package step4.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="user")
@RequestScoped //Dur�e de vue uniquement lors d'une requ�te
//m�me propri�t�s que UserModelBean mais port�e diff�rente
public class UserSubmissionModelBean extends UserModelBean{
	public UserSubmissionModelBean() {
	}
}
