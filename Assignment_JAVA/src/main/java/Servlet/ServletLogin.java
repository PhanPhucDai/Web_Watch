package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.User;
import Dao.UserDao;
import untils.XMAIL;


@WebServlet({"/Login","/Login/index","/Login/confirmGmail","/Login/sendCodeForgetLogin","/Login/register","/LogOut"})
public class ServletLogin extends HttpServlet{
	UserDao dao=new UserDao();
	int code;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if(path.contains("/Login/index")) {
			String nameAccount=req.getParameter("taiKhoan");
			String passwordAccount=req.getParameter("matKhau");
			User user= dao.getUserByAccount(nameAccount, passwordAccount);
			if(user == null) {
				req.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác vui lòng thử lại");
				req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
				return;
			}else {
				req.getSession().setAttribute("user", user);
			}
		resp.sendRedirect("/Assignment_JAVA/User/TrangChu");
		}else if(path.contains("/Login/confirmGmail")) {
			XMAIL xmail=new XMAIL();
			String gmail = req.getParameter("Gmail");
 			String checkGmail=null;
			List<User> listUser=dao.getAllItems();
			User user=new User();
			for (User userList : listUser) {
				if(gmail.equals(userList.getEmail())) {
					checkGmail= "true";
					user=userList;
					 break;
				}
			}
			if(checkGmail == "true") {
				String code= String.valueOf(codeCheckMail());
				user.setPassword( code);
				dao.update(user);
				xmail.sendMail(gmail,code);
				req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
				return;
			}else {
				checkGmail="false";
				 req.setAttribute("resultCheckEmail", checkGmail);
				 req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
				 return;
			}
		 
		}else if(path.contains("/Login/register")) {
			   String tenDangNhap =req.getParameter("tenDangNhap");
			   String hoVaTen =req.getParameter("hoVaTen");
			   String email =req.getParameter("email");
			   String taoMatKhau =req.getParameter("matKhau");
			   String xacNhanMatKhau =req.getParameter("xacNhanMatKhau");
			   if(tenDangNhap == null || hoVaTen == null  || email == null  || taoMatKhau == null  || xacNhanMatKhau == null ) {
				   req.setAttribute("error","Vui lòng nhập đầy đủ thông tin" );
				   req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
				return;
			   }
			   
			   if(taoMatKhau.trim().equals( xacNhanMatKhau.trim()) ==  false ) {
				   req.setAttribute("error","Xác nhận mật khẩu không chính xác" );
				   req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
				return;
			   }
			   
			   List<User> listUser=dao.getAllItems();
			   for (User user : listUser) {
				if(user.getId().trim().equals(tenDangNhap.trim()) == true) {
					req.setAttribute("error","tên đăng nhập đã tồn tại" );
					req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
					return;
				}
			}
	 

			 User user=new User(tenDangNhap,taoMatKhau,hoVaTen,email,false);   
			 dao.addItems(user);
			 req.setAttribute("error","Tạo thành công" );
			req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
		}else if(path.contains("/LogOut")) {
			 HttpSession httpSession= req.getSession();
			 httpSession.removeAttribute("user");
			 httpSession.invalidate();
			req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
		}
		   
		
	}
	
	public int codeCheckMail() {
		code = (int) (Math.random() * 90000) + 10000;
		return code;
	}

}
