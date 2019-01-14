package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav;
		
		//request.getSession().setAttribute("user",request.getSession().getAttribute("user"));
		if(request.getSession().getAttribute("operator")!=null)
			mav = new ModelAndView("operatorHome");
		else mav = new ModelAndView("home");
		return mav;
	}
}
