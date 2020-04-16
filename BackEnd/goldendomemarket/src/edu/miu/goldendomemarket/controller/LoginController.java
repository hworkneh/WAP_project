package edu.miu.goldendomemarket.controller;
/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.miu.goldendomemarket.domain.Account;
import edu.miu.goldendomemarket.service.serviceImpl.AccountServiceImpl;
import edu.miu.goldendomemarket.util.Database;
import edu.miu.goldendomemarket.util.PasswordHashing;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/api/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		PrintWriter out = response.getWriter();
		out.print("Logged Out");
		out.flush();
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountServiceImpl accService=new AccountServiceImpl((Database) getServletContext().getAttribute("db"));
		response.setContentType("text/html");
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null) {
			sb.append(s);
		}
		Account acc = (Account) gson.fromJson(sb.toString(), Account.class);
		System.out.println(acc.toString());
		Account result;
		String pass=acc.getPassword();
		acc.setPassword(PasswordHashing.hashpassword(pass));
		if(accService.findUser(acc.getEmail(), acc.getPassword())!=null) {
			result=accService.findUser(acc.getEmail(), acc.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("user", result.getEmail());
			session.setAttribute("user_type", result.getAccountType());
			session.setAttribute("user_id", result.getAccountId());
		}else {
			result=null;
		}
		PrintWriter out = response.getWriter();
		String json = new Gson().toJson(result);
		out.print(json);
		out.flush();
	}

}
