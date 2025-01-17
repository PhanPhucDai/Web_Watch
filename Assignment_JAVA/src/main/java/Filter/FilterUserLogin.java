package Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;


@WebFilter({ "/User/TrangYeuThich","/User/dislikeVideo/*"
			,"/User/likeVideoInTrangYeuThichPage/*" 
			,"/User/TrangChu", "/User/send/mail"
			, "/User/likeVideo/*", "/User/deleteVideo/*"
			, "/User/search","/Video/deitail/*","/Admin/QuanTriVideo","/Admin/QuanTriVideo/GetVideoByID/*","/Admin/doThing","/Admin/reset" })
public class FilterUserLogin implements HttpFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		User user=(User)req.getSession().getAttribute("user");
		if(user==null) {
			req.getRequestDispatcher("/view/Login.jsp").forward(req, resp);
		}
	 		chain.doFilter(req, resp);
	}

}
