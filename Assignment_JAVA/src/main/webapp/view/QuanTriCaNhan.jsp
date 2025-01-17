<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<title>Quản trị người dùng</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="author" content>
<meta name="keywords" content>
<meta name="description" content>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/vendor.css">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap"
	rel="stylesheet">
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body style="background-color: rgba(29, 31, 34, 0.99);">
	<c:if test="${not empty error}">
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>Lỗi! </strong> ${error}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>
	<c:if test="${not empty success}">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">

			<strong>Success! </strong> ${success}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>
	<div class="container">
		<header>
			<nav class="navbar navbar-expand-sm">
				<div class="container-fluid">

					<button class="navbar-toggler "
						style="background-color: rgb(207, 212, 212);"
						data-bs-toggle="collapse" type="button"
						data-bs-target="#collapsibleNavbar">
						<span class="navbar-toggler-icon  "></span>
					</button>
					<div class="collapse navbar-collapse" id="collapsibleNavbar">
						<ul class="navbar-nav me-auto">
							<li class="nav-item"><a
								class="nav-link text-white  text-white"
								href="/Assignment_JAVA/User/TrangChu"><i
									class="fa-solid fa-film bi bi-file-earmark-text "></i>Trang chủ</a></li>
							<li class="nav-item"><a
								class="nav-link text-white  text-white"
								href="/Assignment_JAVA/User/TrangYeuThich"><i
									class="fa-solid fa-film bi bi-file-earmark-text "></i>Trang yêu
									thích</a></li>
							<c:if test="${sessionScope.user.admin == true}">
								<li class="nav-item"><a
									class="nav-link text-white  text-white"
									href="/Assignment_JAVA/Admin/QuanTriVideo"><i
										class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí
										Video</a></li>
								<li class="nav-item"><a
									class="nav-link text-white  text-white"
									href="/Assignment_JAVA/User/TrangYeuThich"><i
										class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí
										người dùng</a></li>
								<li class="nav-item"><a
									class="nav-link text-white  text-white"
									href="/Assignment_JAVA/QuanTriCaNhan"><i
										class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí
										cá nhân</a></li>
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
										<li class="nav-item"><a class="nav-link text-white">${sessionScope.user.fullname}</a>
										</li>
									</ul>
								</div>
							</c:when>
						</c:choose>
					</div>
					<form action="/Assignment_JAVA/User/search" class="d-flex"
						role="search" style="height: 40px;">
						<input style="height: 40px;" class="form-control " type="search"
							placeholder="Tìm kiếm" aria-label="Search" name="keySearch">
						<button class="btn btn-outline-success" type="submit"
							style="height: 40px; width: 140px">Tìm kiếm</button>
					</form>
				</div>
			</nav>
		</header>

		<section class="h-25">
			<div id="carouselExampleControlsNoTouching" class="carousel slide"
				data-bs-touch="false">
				<div class="carousel-inner "
					style="border-radius: 20px; overflow: hidden;">
					<div class="carousel-item active">
						<img src="images/casino.png" class="d-block w-100 h-100" alt="Ảnh"
							style="object-fit: cover;">
					</div>
					<div class="carousel-item">
						<img src="images/Zombie.png" class="d-block w-100 h-100" alt="Ảnh"
							style="object-fit: cover;">
					</div>
					<div class="carousel-item">
						<img src="images/fight_night.png" class="d-block w-100 h-100"
							alt="Ảnh" style="object-fit: cover;">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleControlsNoTouching"
					data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleControlsNoTouching"
					data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</section>

		<div class="row mt-5">

			<div class="">
				<div class="col-12 row">
					<div class="form-outline col-6" data-mdb-input-init>
						<label class="form-label text-white" for="form12">Mã cá
							nhân</label> <input readonly type="text" id="form12"
							class="text-white form-control border border-secondary"
							style="background-color: transparent;" value="${nguoiDung.id}" />
					</div>
					<div class="form-outline col-6" data-mdb-input-init>
						<form action="/Assignment_JAVA/QuanTriCaNhan/username" >
							<label class="form-label text-white" for="form12">Họ và
								tên</label>
							<div class="input-group mb-3">
								<input value="${nguoiDung.fullname}" type="text"
									name="username"
									class="text-white form-control"
									placeholder="Recipient's username"
									style="background-color: transparent;"
									aria-label="Recipient's username"
									aria-describedby="button-addon2">
								<button class="btn btn-outline-secondary" type="submit"
									id="button-addon2">Chỉnh sửa</button>
							</div>
						</form>
					</div>

					<div class="form-outline col-6" data-mdb-input-init>
						<form action="/Assignment_JAVA/QuanTriCaNhan/email">
							<label class="form-label text-white" for="form12">Email</label>
							<div class="input-group mb-3">

								<input value="${nguoiDung.email}" type="text"
									class="text-white form-control" placeholder="Email"
									style="background-color: transparent;" aria-label="Email"
									aria-describedby="button-addon2" name="mail">
								<button class="btn btn-outline-secondary" type="submit"
									id="button-addon2">Chỉnh sửa</button>

							</div>
						</form>
					</div>

					<div class="form-outline col-6" data-mdb-input-init>
						<label class="form-label text-white" for="form12">Quyền </label>
						<div class="input-group mb-3">
							<input value="${nguoiDung.admin ? 'Admin' : 'Người Dùng'}"
								type="text" class="text-white form-control"
								placeholder="Recipient's username"
								style="background-color: transparent;"
								aria-label="Recipient's username"
								aria-describedby="button-addon2">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">Chỉnh sửa</button>
						</div>
					</div>
					<div class="form-outline col-6" data-mdb-input-init>
						<label class="form-label text-white" for="form12">Mật khẩu</label>
						<div class="input-group mb-3">
							<input value="${nguoiDung.password}" type="password"
								class="text-white form-control"
								placeholder="Recipient's username"
								style="background-color: transparent;"
								aria-label="Recipient's username"
								aria-describedby="button-addon2">
							<button type="button" class="btn btn-outline-secondary "
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								Đổi mật khẩu</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Đổi mật
							khẩu</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="/Assignment_JAVA/QuanTriCaNhan/pass" method="post">
						<div class="modal-body">
							<label> Nhập mật khẩu hiện tại của bạn </label>
							 <input
								type="text"
								name='oldPass'
								class="text-black form-control"
								placeholder="Nhập mật khẩu hiện tại"
								style="background-color: transparent;"
								aria-label="Recipient's username"
								aria-describedby="button-addon2"> <label class="mt-2">
								Nhập mật khẩu mới </label>
								 <input  
								type="text"
								class="text-black form-control"
								placeholder="Nhập mật khẩu mới"
								style="background-color: transparent;"
								aria-label="Recipient's username"
								name='newPass'
								aria-describedby="button-addon2">
							
								<label class="mt-2"> Xác nhận mật khẩu</label>
								<input 
								name='newPassAngain'
								type="text" class="text-black form-control"
								placeholder="Nhập lại mật khẩu mới"
								style="background-color: transparent;"
								aria-label="Recipient's username"
								aria-describedby="button-addon2">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Đóng</button>
							<button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<footer class="py-5 ">
			<ul class="nav justify-content-center  ">
				<li class="nav-item"><a class="nav-link active text-white"
					aria-current="page" href="#">Phan Phúc Đại</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">Gmail:
						dai582005@gmail.com</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">Địa
						chỉ: Đường đến tương lai</a></li>
				<li class="nav-item"><a class="nav-link disabled"
					aria-disabled="true">Disabled</a></li>
			</ul>
		</footer>
	</div>
	<script src="js/jquery-1.11.0.min.js"></script>
	<script
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