package com.cts.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.bean.EmployeeDetails;
import com.cts.dbimpl.EmployeeServiceImplDb;
import com.cts.service.EmployeeDetailService;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int salary=Integer.parseInt(request.getParameter("salary"));
		int deptId=Integer.parseInt(request.getParameter("deptId"));
		
		EmployeeDetailService employeedetailservice=new EmployeeServiceImplDb();
		EmployeeDetails employee1;
		employee1=new EmployeeDetails(id,name,salary,deptId);
		employeedetailservice.add(employee1);
		RequestDispatcher requestDispatcher;
		
		if(employeedetailservice.add(employee1))
		{
			requestDispatcher=request.getRequestDispatcher("success.html");
			requestDispatcher.forward(request, response);
		}
		else
		{
			requestDispatcher=request.getRequestDispatcher("Employee.html");
			PrintWriter printwriter=response.getWriter();
			printwriter.println("invalid input");
			requestDispatcher.include(request, response);
		}
		
		doGet(request, response);
	}

}
