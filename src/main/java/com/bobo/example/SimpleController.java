package com.bobo.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/form")
public class SimpleController {
	
	@Autowired
	private SimpleFormValidator validator;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getForm(Model uiModel) {
		uiModel.addAttribute("sexValues", SexEnum.values());
		uiModel.addAttribute("simpleForm", new SimpleForm());
		return "form";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postForm(SimpleForm form, BindingResult br, Model uiModel) {
		
		validator.validate(form, br);
		
		uiModel.addAttribute("sexValues", SexEnum.values());
		if (br.hasErrors())
			return "form";
		
		return "form";
	}
	
	@RequestMapping(value = "/spring", method = RequestMethod.GET)
	public String getFormSpring(Model uiModel) {
		uiModel.addAttribute("sexValues", SexEnum.values());
		uiModel.addAttribute("form", new SimpleForm());
		return "formSpring";
	}
	
	@RequestMapping(value = "/spring", method = RequestMethod.POST)
	public String postFormSpring(@ModelAttribute("form") SimpleForm form, BindingResult br, Model uiModel) {
		
		validator.validate(form, br);
		
		uiModel.addAttribute("sexValues", SexEnum.values());
		
		if (br.hasErrors())
			return "formSpring";
		
		return "formSpring";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     dateFormat.setLenient(false);
     webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
}
