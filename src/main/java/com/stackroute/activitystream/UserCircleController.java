package com.stackroute.activitystream;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.dao.UserCircleDAO;
import com.stackroute.activitystream.model.User;


@RestController
public class UserCircleController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserCircleDAO userCircleDAO;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String startup()
	{
		return "Server Running Fine";
	}
	
	@RequestMapping(value="/addusertocircle/{circle_Id}/{email_Id}",method=RequestMethod.GET)
	public ResponseEntity<?> addUserToCircle(@PathVariable int circle_Id, @PathVariable String email_Id)
	{
		try {
			//User currentUser = (User) session.getAttribute("currentUser");
			userCircleDAO.addUserToCircle(circle_Id,email_Id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/removeuserfromcircle/{circle_Id}/{email_Id}",method=RequestMethod.GET)
	public ResponseEntity<?> unSubscribeCircle(@PathVariable int circle_Id,@PathVariable String email_Id)
	{
		try {
			//User currentUser = (User) session.getAttribute("currentUser");
			userCircleDAO.removeUserFromCircle(circle_Id, email_Id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
