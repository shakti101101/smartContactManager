package com.smart.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.service.Emailservice;

import jakarta.servlet.http.HttpSession;

@Controller
public class ForgetController {
	
	@Autowired
	private Emailservice emailservice;
	
	
	@Autowired
	private UserRepository repository;

	//email id form open handler
	@GetMapping("/forget")
	public String openEmailform()
	{
		
		return "forget_email_form";
		
	}
	
	@PostMapping("/sent-otp")
	public String sentOTP(@RequestParam ("email") String email,HttpSession session)
	{
		System.out.println("EMAIL >>>>"+email);
		//generating otp of 4 digit
		
		Random random=new Random(1000);
		int otp = random.nextInt(999999);
		System.out.println("OTP >>>>"+otp);
	
		//write code for send to mail.......
		
		String subject ="OTP from SCM";
		String message=""
				+"<div style='border:1px solid #e2e2e2; padding:20px'>"
				+"<h1>"
				+"OTP is "
				+"<b>"+otp
				+"</n>"
				+"</h1>"
				+"</div>"
				
				;
		String to=email;
		
		boolean flag = this.emailservice.sendEmail(subject, message, to);
		
		if(flag)
		{
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			
			return "verify_otp";
		}else {
			
			session.setAttribute("message", "Check your Email id !!");
			return "forget_email_form";
		}
		
		
	}
	//verify-otp
	@PostMapping("/verify-otp")
	public String verifyotp(@RequestParam("otp")int otp,HttpSession session)
	{
		int myotp=(int)session.getAttribute("myotp");
		String email=(String) session.getAttribute("email");
		
		if(myotp==otp)
		{
			//password change form
			User user=this.repository.getUserByUserName(email);
            if(user==null)
            {
            	//sent error message
        		session.setAttribute("message", "User does not exit with this email !!");
    			return "forget_email_form";	
    	
            }else {
            	//sent change password form
            }
			
			return "password_change_form";
		}else {
			session.setAttribute("message", "You have entered wrong otp !!");
			return "verify_otp";	
		}
		
	}
}
