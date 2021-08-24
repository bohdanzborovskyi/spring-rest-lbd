package com.zbodya.Service;


import org.springframework.stereotype.Service;

@Service
public class AuthorizationService 
{
	
	public boolean isAuthorized(String path, String role) 
	{
		if((path.startsWith("/api/teachers") && role.equals("TEACHER_ROLE"))
				|| (path.startsWith("/api/students") && (role.equals("TEACHER_ROLE")||role.equals("STUDENT_ROLE"))))
		{
			System.out.println("Authorize: " + true);
			return true;
		}
		else
		{
			System.out.println("Authorize: " + false);
			return false;
		}
	}

}
