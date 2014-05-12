package step4.processing;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import step4.dao.fabric.DaoFabric;
import step4.dao.instance.UserDao;
 
@FacesValidator(value = "validators.pwd")
public class PwdValidator implements Validator {
    private static final String USERNAME_PATTERN = "^[_A-Za-z0-9-@]+";
    private Pattern pattern;
    private Matcher matcher;
 
    public PwdValidator() {
    	pattern = Pattern.compile(USERNAME_PATTERN);
    }
 
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Email validation failed.",
                    "Email validation failed please follow the contraint"
                            + USERNAME_PATTERN);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}