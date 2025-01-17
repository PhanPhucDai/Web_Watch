package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.User;
import Dao.UserDao;

@WebServlet({ "/QuanTriCaNhan", "/QuanTriCaNhan/email/*", "/QuanTriCaNhan/username/*", "/QuanTriCaNhan/pass" })
public class QuanTriCaNhan extends HttpServlet {
	UserDao userDao = new UserDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession getSession = req.getSession();
		User user = (User) getSession.getAttribute("user");

		if (req.getServletPath().contains("/QuanTriCaNhan/email")) {
			try {
				String newMail = req.getParameter("mail");
				user.setEmail(newMail);
				System.out.println("Thay đổi gmail");
				userDao.update(user);
				req.setAttribute("success", "Đổi gmail thành công");
			} catch (Exception e) {
				req.setAttribute("error", "Đổi gmail không thành công");
				e.printStackTrace();
			}

		} else if (req.getServletPath().contains("/QuanTriCaNhan/username")) {
			try {
				String newUsername = req.getParameter("username");
				user.setFullname(newUsername);
				userDao.update(user);
				req.setAttribute("success", "Đổi tên thành công");
			} catch (Exception e) {
				req.setAttribute("error", "Đổi tên không thành công");
				e.printStackTrace();
			}
		}
		req.setAttribute("nguoiDung", user);
		req.getRequestDispatcher("/view/QuanTriCaNhan.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession getSession = req.getSession();
		User user = (User) getSession.getAttribute("user");
		if (req.getServletPath().contains("/QuanTriCaNhan/pass")) {
			String oldPass = req.getParameter("oldPass");
			String newPass = req.getParameter("newPass");
			String newPassAngain = req.getParameter("newPassAngain");
			if(!newPass.trim().equals(newPassAngain.trim())) {
				req.setAttribute("error", "Đổi nhập lại mật khẩu không chính xác");
				req.setAttribute("nguoiDung", user);
				req.getRequestDispatcher("/view/QuanTriCaNhan.jsp").forward(req, resp);
				return;
			}else if(!oldPass.trim().equals(user.getPassword().trim())) {
				req.setAttribute("error", "Đổi nhập lại mật khẩu cũ không chính xác");
				req.setAttribute("nguoiDung", user);
				req.getRequestDispatcher("/view/QuanTriCaNhan.jsp").forward(req, resp);
				return;
			}
			try {
				user.setPassword(newPass);
				userDao.update(user);
				req.setAttribute("success", "Đổi mật khẩu thành công");
			} catch (Exception e) {
				req.setAttribute("error", "Đổi mật khẩu không thành công");
				e.printStackTrace();
			}
		}
		req.setAttribute("nguoiDung", user);
		req.getRequestDispatcher("/view/QuanTriCaNhan.jsp").forward(req, resp);
	}

}
