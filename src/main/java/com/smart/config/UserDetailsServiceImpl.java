//package com.smart.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.smart.dao.UserRepository;
//import com.smart.entities.User;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	@Autowired
//	private  UserRepository repository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		 System.out.println("username >>>>>>>>>>>>"+username);
//		User user =repository.getUserByUserName(username);
//		//System.out.println(user.toString());
//		if(user==null)
//		{
//			throw new UsernameNotFoundException("count not found user ");
//		}
//		
//		CustomUserDetail customUserDetail= new CustomUserDetail(user);
//		
//		return customUserDetail;
//	}
//
//}
