package com.smart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository repository;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Samrt Contact Manager");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Samrt Contact Manager");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Signup - Samrt Contact Manager");
		model.addAttribute("user", new User());

		return "signup";
	}

	@RequestMapping("/signin")
	public String signin(Model model) {
		System.out.println("login custom");
		model.addAttribute("title", "signin - Samrt Contact Manager");
		return "login";
	}

	// handler register
	@PostMapping("/do_register")
	public String do_register(@Valid @ModelAttribute ("user") User user,@RequestParam(value="agreement" , defaultValue = "false") 
	boolean agreement,Model model,HttpSession session, BindingResult result1)
	{
		try {
			
			if(!agreement) {
				System.out.println("you have not agree the term and condition !!!");
				throw new Exception("you have not agree the term and condition !!!");
			}
			
			if(result1.hasErrors())
			{
				System.out.println("Error"+ result1.toString());
				return "signup";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabed(true);
			user.setImgUrl("defult_img");
			//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); 
			System.out.println("agreement----"+agreement);
			System.out.println("user----------"+user);
			model.addAttribute("user", user);
			
			User result = this.repository.save(user);
			
			model.addAttribute("user", new User());
			 session.setAttribute("message",new Message(" Successfully Registered !!","alert-success"));
	         

		} catch (Exception e) {
          e.printStackTrace();
          model.addAttribute("user", user);
          session.setAttribute("message",new Message(" somehing went wong !!"+e.getMessage(),"alert-danger"));
          
		}
		return "signup";
	}

}
