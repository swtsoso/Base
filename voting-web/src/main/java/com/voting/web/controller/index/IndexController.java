package com.voting.web.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页控制器
 * @version 1.0
 */
@Controller
public class IndexController {
	
	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public ModelAndView toIndex(ModelAndView view) {
		view.setViewName("redirect:/index");
		return view;
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "/index";
	}

}
