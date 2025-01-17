package Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.User;

@WebFilter({"/Admin/QuanTriVideo","/Admin/QuanTriVideo/GetVideoByID/*","/Admin/doThing","/Admin/reset",
	"/Admin/QuanTriNguoiDung","/Admin/QuanTriNguoiDung/user/*" })
public class FillterAdmin implements HttpFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
			HttpSession getSession=req.getSession();
			User user=(User)getSession.getAttribute("user");
			if(!user.isAdmin() || user == null) {
				req.getRequestDispatcher("/User/TrangChu").forward(req, resp);
			}else {
				chain.doFilter(req, resp);
			}
	}

}
