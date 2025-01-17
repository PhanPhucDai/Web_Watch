package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Favorite;
import Bean.User;
import Bean.Videos;
import Dao.FavoriteDao;
import Dao.VideoDao;

@WebServlet({ "/User/TrangYeuThich","/User/dislikeVideo/*","/User/likeVideoInTrangYeuThichPage/*" })
public class ServletTrangYeuThich extends HttpServlet{
	FavoriteDao favoriteDao =new FavoriteDao();
	VideoDao videoDao=new VideoDao();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO:  Lấy ra tất cả danh sách yêu thích của bạn
		HttpSession httpSession=req.getSession();
		User user =(User) httpSession.getAttribute("user");
		//Lấy danh sách yêu thích
		List<Favorite> favorites= favoriteDao.getItemByUserId(user);
 		List<Videos> videos=new ArrayList<Videos>();
 		//Kiểm tra danh sách yêu thích 
		if(favorites == null) {
			req.setAttribute("itemVideos", listVideo());
			req.setAttribute("items", videos);
			req.setAttribute("noList", "Bạn chưa có danh sách yêu thích nào");
			req.getRequestDispatcher("/view/trangYeuThich.jsp").forward(req, resp);
			return; 
		}
		for (Favorite favorite2 : favorites) {
			Videos video=  videoDao.getItemById(favorite2.getVideo().getId());
			videos.add(video);
		}
		if(req.getServletPath().contains("/User/dislikeVideo")) {
			String id = req.getPathInfo();
			HttpSession session = req.getSession();
			User user1 = (User) session.getAttribute("user");
			try {
				favoriteDao.deleteByIdUsedAndIdVideo(user.getId(),  id.substring(1));
				req.setAttribute("successDelete", "Xoá thành công");
			}catch(Exception ex) {
				ex.printStackTrace();
				req.setAttribute("error", "Không thể xóa được");
			}
			resp.sendRedirect("/Assignment_JAVA/User/TrangYeuThich");
			return ;
		}else if(req.getServletPath().contains("/User/likeVideoInTrangYeuThichPage")) {
			String videoLiked = req.getPathInfo();
			Videos video = videoDao.getItemById(videoLiked.substring(1));
			List<Favorite> favorite = favoriteDao.getItemByUserId(user);
			String idVideo=video.getId();
			boolean checkExist = false;
			for (Favorite favorite1 : favorites) {
				if (favorite1.getVideo().getId().trim().equals(idVideo.trim())) {
					checkExist=true;
				}
			}
			if(checkExist==true) {
				req.setAttribute("idVideo", idVideo );
				req.setAttribute("errorExistVideo", "Video đã được lưu vào danh sách yếu thích trước đó");
			}else {
				Date currentDate = new Date(0);
				Favorite favorite2 = new Favorite(video, user, currentDate);
				favoriteDao.addItems(favorite2);
				req.setAttribute("successDelete", "Đã lưu vào danh sách yếu thích");
				} 
			
			resp.sendRedirect("/Assignment_JAVA/User/TrangYeuThich");
			return ;
		}
		req.setAttribute("itemVideos", listVideo());
		req.setAttribute("items", videos);
		req.getRequestDispatcher("/view/trangYeuThich.jsp").forward(req, resp);
	}
	
	public    List<Videos> listVideo() {
		List<Videos> listVideo = videoDao.getAllItems();
		
		return listVideo;
	}
	
}
