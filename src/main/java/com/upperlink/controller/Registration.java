package com.upperlink.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upperlink.model.Developer;
import com.upperlink.model.Skills;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Operation operation = new Operation();
			
			String firstName = request.getParameter("firsName");
			String lastName = request.getParameter("lastName");
			String[] skills = request.getParameterValues("skills");
			Skills selectedSkill = null;
			
			Developer developer = operation.getDeveloper(operation.addDeveloper(firstName, lastName));
			
			for (int i = 0; i < skills.length; i++) {
				selectedSkill = operation.getSkill(operation.getEnum(skills[i]));
				if(selectedSkill == null){
					operation.addSkill(operation.getEnum(skills[i]));
					selectedSkill = operation.getSkill(operation.getEnum(skills[i]));
				}
				
				operation.registerDeveloper(developer, selectedSkill);
			}
			
			response.getWriter().append("Successfully Registered");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
