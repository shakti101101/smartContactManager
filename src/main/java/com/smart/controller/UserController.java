package com.smart.controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserRepository repository;

	@Autowired
	private ContactRepository contactRepository;
	
	@RequestMapping("/index")
	public String dashbord(Model model,Principal principal,HttpSession session)
	{
		String uesrname=principal.getName();
		System.out.println(uesrname);
		session.setAttribute("message", "Get session value");
		return "normal/dash_bord";
	}

	//open add form handler
	
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model)
	{
		model.addAttribute("title","Add-contact");
		model.addAttribute("contact", new Contact());
		return "normal/addContact";
	}
	
	@PostMapping("/process-contact")
	public String processcontact(@ModelAttribute ("contact")Contact contact ,Model model,@RequestParam("profileImage") MultipartFile file,HttpSession httpSession)
	{
		try {
			//file processing uploading file ...
			if(file.isEmpty())
			{
				System.out.println("File is empty ");
				contact.setImage("Contacts_logo.png");
			}else {
				//file the file to folder update the name to  contact
			contact.setImage(file.getOriginalFilename());
			File savefile=new ClassPathResource("static/image").getFile();
			 Path path =Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(),path ,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("image is uploaded");
			}
			System.out.println("DATA ---"+contact);
			if(contact==null)
			{
			System.out.println("it is empty !!!");	
			
			}else {
			
			repository.save(contact);
			}
			System.out.println("Added to data base");
			httpSession.setAttribute("message", new Message("successfilly added data","success"));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
			httpSession.setAttribute("message", new Message("something is wrong !!","danger"));
		}
		
		return "normal/addContact";
	}
	
	//show all contact 
	//use pagination 
    //per page =5[n]
	//current page=0 [page]
	@GetMapping("/show-contact/{page}")
	public String showcontact(@PathVariable("page") Integer page,Model  model)
	{
		model.addAttribute("title","show-contact");
		
		Pageable pageable = PageRequest.of(page, 5);
		Page<Contact> contact =contactRepository.findAll(pageable);
		
		model.addAttribute("contact", contact);
		model.addAttribute("currntpage", page);
		model.addAttribute("totalpage", contact.getTotalPages());
		
		
		
		return "normal/show-contact";
	}
	
	//showing particular contact details ....
	@RequestMapping("/{cid}/contact")
	public String showContacDetails(@PathVariable("cid") Integer cid)
	{
		System.out.println("CID get >>>>>>>"+cid);
		return "normal/contact_detail";
	}
	
}
