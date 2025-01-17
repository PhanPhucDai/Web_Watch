<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <title>Tiểu phẩm</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
       
      

        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
		<base href="${pageContext.servletContext.contextPath}/">
    </head>
    
    <style>
    	ul.navbar li {
		    margin: 15px; /* Tùy chỉnh khoảng cách giữa các mục */
		    height: 250px; /* Chiều cao cố định */
		}
		
		ul.navbar img {
		    object-fit: cover;
		    width: 100%;
		    height: 150px;
		}
		
		ul.navbar .text-center {
		    color: white;
		}
    	
    </style>
    <body style="background-color: rgba(29, 31, 34, 0.99);">
  
        <div class="container">

            <header>
                <nav class="navbar navbar-expand-sm">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="lab3Index.html">
                            <img src="images/logo-energy-pilates.png" alt
                                style="width: 150px;">
                        </a>
                        <button class="navbar-toggler "
                            style="background-color: rgb(207, 212, 212);"
                            data-bs-toggle="collapse"
                            type="button" data-bs-target="#collapsibleNavbar">
                            <span class="navbar-toggler-icon  "></span>
                        </button>
                        <div class="collapse navbar-collapse"
                            id="collapsibleNavbar">
                            <ul class="navbar-nav me-auto">
                                <li class="nav-item">
                                    <a class="nav-link text-white  text-white" href="/Assignment_JAVA/User/TrangChu"><i class="fa-solid fa-film bi bi-file-earmark-text "></i>Trang chủ</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white  text-white" href="/Assignment_JAVA/User/TrangYeuThich"><i class="fa-solid fa-film bi bi-file-earmark-text "></i>Trang yêu thích</a>
                                </li>
 								 <c:if test="${sessionScope.user.admin == true}">
									<li class="nav-item"><a
								class="nav-link text-white  text-white"
								href="/Assignment_JAVA/User/TrangYeuThich"><i class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí Video</a></li>
							 
						 
									<li class="nav-item"><a
								class="nav-link text-white  text-white"
								href="/Assignment_JAVA/Admin/QuanTriNguoiDung"><i class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí người dùng</a></li>
							<li class="nav-item"><a
								class="nav-link text-white  text-white"
								href="/Assignment_JAVA/Admin/QuanTriCaNhan"><i class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí cá nhân</a></li>
							</c:if>
							<li class="nav-item"><a
								class="nav-link text-white  text-white"
								href="/Assignment_JAVA/LogOut"><i
									class="fa-solid fa-film bi bi-file-earmark-text "></i>Đăng xuất</a></li>
                            </ul>
                           <c:choose>
							<c:when test="${empty sessionScope.user}">
								<div>
									<ul class="navbar-nav d-flex">
										<li class="nav-item"><a class="nav-link text-white">Đăng
												kí</a></li>
										<li class="nav-item"><a class="nav-link text-white">
										</a></li>
									</ul>
								</div>
							</c:when>
							<c:when test="${not empty sessionScope.user}">
								<div>
									<ul class="navbar-nav d-flex">
										<li class="nav-item"><a class="nav-link text-white">Xin
												Chào: </a></li>
										<li class="nav-item"><a class="nav-link text-white">${sessionScope.user.fullname}</a></li>
									</ul>
								</div>
							</c:when>
						</c:choose>
                        </div>
                       <form action="/Assignment_JAVA/User/search" class="d-flex" role="search" style="height: 40px;">
						<input style="height: 40px;" class="form-control " type="search" placeholder="Tìm kiếm" aria-label="Search" name="keySearch">
						<button class="btn btn-outline-success" type="submit" style="height: 40px; width:140px">Tìm kiếm</button>
					</form>
                    </div>
                </nav>
            </header>
             
            <section class="border-2">
            <div class="border-2">${video.detailVideo}</div>
            <div>
            	${video.title}
            </div>
            <div class="accordion" id="accordionExample">
			  <div class="accordion-item">
			    <h2 class="accordion-header">
			      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
			       Bình luận
			      </button>
			    </h2>
			    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
			      <div class="accordion-body">
			     	 ${video.description}
 			      </div>
			    </div>
			  </div>
			  </div>
            </section>
              
            <div class="  position-relative  text-white" style="right:12px;">
                Những tiểu phẩm khác
            </div>
         <div class="row g-0">
    <ul class="navbar list-unstyled d-flex flex-wrap justify-content-start">
        <c:forEach var="item" items="${itemVideos}">
            <li class="col-xxl-2 col-lg-3 col-md-4 col-sm-6 p-2 d-flex flex-column align-items-center justify-content-center" 
                style="background-color: rgb(43, 44, 44); border-radius: 10px;">

                <!-- Liên kết ảnh -->
                <a href="/Assignment_JAVA/Video/deitail/${item.id}">
                    <img src="${item.poster}" alt="Ảnh" class="img-fluid"
                        style="object-fit: cover; width: 100%; height: 150px; border-radius: 10px;">
                </a>

                <!-- Nội dung nút và tiêu đề -->
                <div class="mt-2 text-center">
                    <div class="row">
                        <div class="col-4">
                            <a href="/Assignment_JAVA/User/likeVideo/${item.id}" 
                               class="btn btn-outline-secondary text-white" type="button">Like</a>
                        </div>
                        <div class="col-8">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                Chia Sẻ
                            </button>
                        </div>
                    </div>
                    <span class="text-white mt-2">${item.title}</span>
                </div>

                <!-- Form chia sẻ -->
                <form action="/Assignment_JAVA/User/send/mail" method="get">
                    <div class="modal" id="exampleModal" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Chia sẻ</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <span>Nhập Email</span>
                                    <input class="form-control mt-1" type="email" name="email" required>
                                    <span>Phần nội dung</span>
                                    <textarea class="form-control mt-1" name="textSendVideo" required></textarea>
                                    <input type="hidden" name="videoId" value="${item.id}">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="submit" class="btn btn-primary">Gửi</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </li>
        </c:forEach>
    </ul>
</div>

                <div class="d-flex justify-content-center">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#"
                                    aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link"
                                    href="#">1</a></li>
                            <li class="page-item"><a class="page-link"
                                    href="#">2</a></li>
                            <li class="page-item"><a class="page-link"
                                    href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <footer class="py-5">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <a class="nav-link active text-white"
                                aria-current="page" href="#">Về chúng tôi</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">Gmail:
                                dai582005@gmail.com</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">Địa chỉ:
                                40/8/1 Tân thới hòa - Tân phú</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled text-white"
                                aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                </footer>
         
            </div>
            <script src="js/jquery-1.11.0.min.js"></script>
            <scriptz
                src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
            <script
                src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
                crossorigin="anonymous"></script>
            <script src="js/plugins.js"></script>
            <script src="js/script.js"></script>
            <script>
      
    </script>
        </body>
    </html>