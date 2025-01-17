package Servlet;
 
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.Path;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Bean.Videos;
import DTO.VideoMaxFavoriteDTO;
import Dao.VideoDao;



@WebServlet({"/Admin/QuanTriVideo","/Admin/QuanTriVideo/GetVideoByID/*","/Admin/doThing","/Admin/reset"})
@MultipartConfig(
		 maxFileSize = 52428800,   // 50MB
		 maxRequestSize = 104857600, // 100MB
	     fileSizeThreshold = 0
	)
public class ServletQuanTriVideo extends HttpServlet{
	
	VideoDao videoDao=new VideoDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	 if(req.getServletPath().contains("/Admin/QuanTriVideo/GetVideoByID")) {
			String idVideo= req.getPathInfo().substring(1);		
			Videos videos=videoDao.getItemById(idVideo);
			req.setAttribute("videos", videos);
			 
		} 
	 	req.setAttribute("videosMaxFavourite", getVideosMaxFavourite());
		req.setAttribute("videoMaxView", getVideosMaxView());
		req.getRequestDispatcher("/view/QuanTriVideo.jsp").forward(req, resp);
	}
	
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String action = req.getParameter("action");
	    System.out.println("Đây là doPost - action: " + action);
	  
	    if ("add".equals(action)) {
	    	  req.setCharacterEncoding("UTF-8");
	        String id = "vd" + UUID.randomUUID().toString();
	        String title = req.getParameter("tenTieuPham");
	        String activeParam = req.getParameter("active");
	        boolean active = "true".equalsIgnoreCase(activeParam);
	        String moTa = req.getParameter("moTa");

	        String videoFileName = null;
	        String posterFileName = null;

	        // Tạo thư mục nếu chưa tồn tại
	        String basePath = getServletContext().getRealPath("/") + "source";
	        File dir = new File(basePath);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	        // Xử lý tải poster
	        Part posterPart = req.getPart("poster");
	        String posterFullPath = null;
	        if (posterPart != null && posterPart.getSize() > 0) {
	            posterFileName = getFileName(posterPart).replaceAll("\\s+", "_");
	            try {
	                posterFullPath = basePath + File.separator + posterFileName; // Đường dẫn đầy đủ
	                posterPart.write(posterFullPath); // Lưu tệp poster
	            } catch (IOException e) {
	                throw new ServletException("Lỗi khi lưu tệp poster: " + posterFileName, e);
	            }
	        }

	        // Xử lý tải video
	        Part videoPart = req.getPart("linkTieuPham");
	        String videoFullPath = null;
	        if (videoPart != null && videoPart.getSize() > 0) {
	            videoFileName = getFileName(videoPart).replaceAll("\\s+", "_");
	            try {
	                videoFullPath = basePath + File.separator + videoFileName; // Đường dẫn đầy đủ
	                videoPart.write(videoFullPath); // Lưu tệp video
	            } catch (IOException e) {
	                throw new ServletException("Lỗi khi lưu tệp video: " + videoFileName, e);
	            }
	        }

	        // Lưu video vào cơ sở dữ liệu
	        try {
	            // Lưu đối tượng Videos với đường dẫn đầy đủ của poster và video
	            String pathTuongDoi="source/"+posterFileName;
	         // Đường dẫn tương đối tới thư mục 'source' và videoFileName
	            String pathTuongDoi2 = "<img style=\"width: 100px; height: 100px;\" src=\"source/" + videoFileName + "\" />";
	            Videos videos = new Videos(id, title, pathTuongDoi, moTa, 0, active, pathTuongDoi2);
	            videoDao.addItems(videos);
	        } catch (Exception e) {
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "Lỗi khi lưu video vào cơ sở dữ liệu: " + e.getMessage());
	            req.getRequestDispatcher("/view/error.jsp").forward(req, resp);
	            return;
	        }

	        // Chuyển hướng
	        resp.sendRedirect(req.getContextPath() + "/Admin/QuanTriVideo");
	    } else if ("edit".equals(action)) {
	    	  req.setCharacterEncoding("UTF-8");
	    	  	String id = req.getParameter("id");
	    	  	Videos videos = videoDao.getItemById(id);
		        String title = req.getParameter("tenTieuPham");
		        String activeParam = req.getParameter("active");
		        boolean active = (activeParam != null);
		        String moTa = req.getParameter("moTa");
		        Videos videos2=new Videos(id, title, videos.getPoster(), moTa, videos.getViews(),  active, videos.getDetailVideo());
		        videoDao.updateItems(videos2);
		     	Videos videos1 = new Videos();
	  	        req.setAttribute("videos", videos1);
	  	        req.setAttribute("videosMaxFavourite", getVideosMaxFavourite());
	  	        req.setAttribute("videoMaxView", getVideosMaxView());
	  	        req.getRequestDispatcher("/view/QuanTriVideo.jsp").forward(req, resp);
	  	        return;
	    } else if ("delete".equals(action)) {
	    	
	    	 	String id = req.getParameter("id");
	    	 	videoDao.deleteById(id);
	    	 	Videos videos = new Videos();
	  	        req.setAttribute("videos", videos);
	  	        req.setAttribute("videosMaxFavourite", getVideosMaxFavourite());
	  	        req.setAttribute("videoMaxView", getVideosMaxView());
	  	        req.getRequestDispatcher("/view/QuanTriVideo.jsp").forward(req, resp);
	  	        return;
	      } else if ("reset".equals(action)) {
	        Videos videos = new Videos();
	        req.setAttribute("videos", videos);
	    	req.setAttribute("videosMaxFavourite", getVideosMaxFavourite());
	        req.setAttribute("videoMaxView", getVideosMaxView());
	        req.getRequestDispatcher("/view/QuanTriVideo.jsp").forward(req, resp);
	        return;
	    }
	}

	    
 

	public List<Videos> getVideosMaxView(){
		List<Videos> videos= videoDao.getItemMaxView();
		return videos;
	}
	

	public List<VideoMaxFavoriteDTO> getVideosMaxFavourite(){
		List<VideoMaxFavoriteDTO> VideoMaxFavoriteDTO= videoDao.getItemMaxFavourite();
		return VideoMaxFavoriteDTO;
	}
	
	private String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }
}
