package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;
import Bean.Videos;
import Dao.VideoDao;

@WebServlet({"/Video/deitail/*"})
public class ServletVideo extends HttpServlet{
	VideoDao videoDao=new VideoDao();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getPathInfo();
		req.setAttribute("video", videoDao.getItemById(path.substring(1)));
		req.setAttribute("itemVideos", listVideo());
		req.getRequestDispatcher("/view/trangChiTiet.jsp").forward(req, resp);
		}
	
	
	 
	public    List<Videos> listVideo() {
		List<Videos> listVideo = videoDao.getAllItems();
		
		return listVideo;
	}
}
