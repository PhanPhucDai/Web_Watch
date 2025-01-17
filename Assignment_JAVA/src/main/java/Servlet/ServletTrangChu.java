package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import Dao.UserDao;
import Dao.VideoDao;
import untils.XMAIL;

@WebServlet({ "/User/TrangChu", "/User/send/mail", "/User/likeVideo/*", "/User/deleteVideo/*", "/User/search" })
public class ServletTrangChu extends HttpServlet {
	UserDao Userdao = new UserDao();
	VideoDao VideoDao = new VideoDao();
	XMAIL xmail = new XMAIL();
	FavoriteDao daoFavorite = new FavoriteDao();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO:load tất cả các video 
		req.setAttribute("itemVideos", listVideo());
		if (req.getServletPath().contains("/User/send/mail")) {
			String email = req.getParameter("email");
			String videoId = req.getParameter("videoId");
			String contend = req.getParameter("textSendVideo");
			boolean existEmail = false;
			List<User> users = Userdao.getAllItems();
			for (User user : users) {
				if (user.getEmail().equalsIgnoreCase(email)) {
					sendVideo(email, videoId, contend);
					existEmail = true;
					
				}
			}
				
			if (existEmail == false) {
				req.setAttribute("error", "email không tồn tại trong hệ thông");
				System.out.println("không tồn tại");
				req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
				return;
			}
		} else if (req.getServletPath().contains("/User/likeVideo")) {
			String videoLiked = req.getPathInfo();
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			Videos video = VideoDao.getItemById(videoLiked.substring(1));
			List<Favorite> favorites = daoFavorite.getItemByUserId(user);
			String idVideo=video.getId();
			boolean checkExist = false;
			for (Favorite favorite : favorites) {
				if (favorite.getVideo().getId().trim().equals(idVideo.trim())) {
					checkExist=true;
				}
			}
			if(checkExist==true) {
				req.setAttribute("idVideo", idVideo );
				req.setAttribute("errorExistVideo", "Video đã được lưu vào danh sách yếu thích trước đó");
			}else {
				Date currentDate = new Date(0);
				Favorite favorite2 = new Favorite(video, user, currentDate);
				daoFavorite.addItems(favorite2);
				req.setAttribute("successDelete", "Đã lưu vào danh sách yếu thích");
				} 
		}else if (req.getServletPath().contains("/User/deleteVideo")) {
			String id = req.getPathInfo();
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			try {
				daoFavorite.deleteByIdUsedAndIdVideo(user.getId(),  id.substring(1));
				req.setAttribute("successDelete", "Xoá thành công");
			}catch(Exception ex) {
				ex.printStackTrace();
				req.setAttribute("error", "Không thể xóa được");
			}
		}else if (req.getServletPath().contains("/User/search")) {
			String keySearch = req.getParameter("keySearch");
			List<Videos>  videos=VideoDao.getItemBySearchKey(keySearch);
			req.setAttribute("itemVideos", videos);
		}
		req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
	}

	public List<Videos> listVideo() {
		List<Videos> listVideo = VideoDao.getAllItems();
		
		return listVideo;
	}

	public void sendVideo(String mail, String link, String contend) {
		xmail.sendMailShareVideo(mail, link, contend);
	}

}
