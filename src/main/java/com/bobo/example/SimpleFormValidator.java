package com.bobo.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SimpleFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return SimpleForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		SimpleForm form = (SimpleForm) target;
		
		if (StringUtils.isEmpty(form.getName()))
			errors.rejectValue("name", "form.name.none");
		
		if (form.getBirthDate() == null)
			errors.rejectValue("birthDate", "form.birthDate.none");
		
		if (form.getSex() == null)
			errors.rejectValue("sex", "form.sex.none");
	}


}
