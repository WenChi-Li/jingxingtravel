package tw.jingxing.rentcar.controller;
//package tw.leonchen.controller2;
//
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	private UserProfilesService uService;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		UserProfiles user = uService.findByName(username);		
//		return new User(user.getName(), user.getPassword(), Collections.EMPTY_LIST);
//	}
//
//}
