package entities.validator;

import java.util.regex.*;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidatorWithRegex implements Validator{
	  private Pattern pattern;
	  private Matcher matcher;

	  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	  public EmailValidatorWithRegex(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	  }

	  /**
	   * Validate hex with regular expression
	   * @param hex hex for validation
	   * @return true valid hex, false invalid hex
	   */
	  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		  
		  String hex = (String) value;
		  matcher = pattern.matcher(hex);
		  if(matcher.matches() == false){
			  
			  	FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary("Email is not valid.");
				message.setDetail("Email is not valid.");
				context.addMessage("userForm:Email", message);
				throw new ValidatorException(message);
		  }

	  }
}
