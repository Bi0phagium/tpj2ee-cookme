package step4.processing;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator(value = "validators.age")
public class AgeValidator implements Validator { 
    public AgeValidator() {
        
    }
 
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
    	
        if ((int)value > 100) {
            FacesMessage msg = new FacesMessage("age validation failed.",
                    "age validation failed please follow the contraint > 100");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}