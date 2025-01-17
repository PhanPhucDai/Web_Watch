package Servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Bean.User;
import Dao.UserDao;

 
@WebServlet({"/Admin/QuanTriNguoiDung","/Admin/QuanTriNguoiDung/user/*"})
public class ServletQuanTriNguoiDung extends HttpServlet {
 
		UserDao userDao=new UserDao();
	 
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("Check run ");
			System.out.println(req.getServletPath());
			if(req.getServletPath().contains("/Admin/QuanTriNguoiDung/user")) {
				String idUser=req.getPathInfo().substring(1);
				System.out.println("id"+idUser);

				User user=userDao.getItemById(idUser);
				System.out.println("user "+user.getEmail());
				System.out.println("user "+user.getFullname());
				req.setAttribute("nguoiDung", user);
			}
			
			req.setAttribute("items", userDao.getAllItems());
			req.getRequestDispatcher("/view/QuanTriNguoiDung.jsp").forward(req, resp);
		}
		
		 
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		     
		}

	 
}
