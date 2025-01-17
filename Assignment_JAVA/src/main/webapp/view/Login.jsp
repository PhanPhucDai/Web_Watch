<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<title>Dự án của tôi</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<div class="container-fluid p-0 d-flex position-relative"
		style="height: 100vh;">
		<img
			class="w-100 vh-100 position-absolute top-0 start-0 object-fit-cover z-0"
			src="./images/backgroup.jpg" alt="Background Image">

 		<div class=" position-relative z-1 text-white p-5">
			<h1 class="text-white">Chào mừng bạn đến với chúng tôi!</h1>
			<p>Chúng tôi cung cấp các dịch vụ tuyệt vời.</p>
		</div>
		<c:if test="${empty resultCheckEmail}">
			<div
				class="z-2 position-fixed rounded-2 justify-content-center align-items-center "
				style="top: 50%; left: 50%; width: 600px; transform: translate(-50%, -50%); padding: 20px;">
				<div class="d-flex justify-content-center align-items-center vh-100">
					<div class="card shadow-lg p-4 " style="background-color: rgba(91, 88, 85, 0.53); width: 100%; max-width: 500px;">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation"><a
								class="nav-link active" id="login-tab" data-bs-toggle="tab"
								href="#menu1" role="tab" aria-controls="menu1"
								aria-selected="true">Đăng Nhập</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								id="register-tab" data-bs-toggle="tab" href="#menu2" role="tab"
								aria-controls="menu2" aria-selected="false">Đăng Ký</a></li>
							<li><a aria-controls="menu3" aria-selected="false"
								href="#menu3" id="forget-password" class="nav-link  "
								data-bs-toggle="tab">Quên mật khẩu ?</a></li>


						</ul>

						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="menu1" role="tabpanel"
								aria-labelledby="login-tab">
								<h3 class="mt-3">Đăng Nhập</h3>
								<form action="/Assignment_JAVA/Login/index" method="post">
									<div class="mb-3">
										<label for="taiKhoan" class="form-label">Tài khoản</label> <input
											type="text" class="form-control" id="taikhoan"
											name="taiKhoan" placeholder="Nhập tài khoản">
									</div>
									<div class="mb-3">
										<label for="matkhau" class="form-label">Mật khẩu</label> <input
											type="password" role="tab" class="form-control" id="matKhau"
											name="matKhau" placeholder="Nhập mật khẩu">
									</div>
									<span style="color: red;">${error}</span>
									<div>
										<button type="submit" class="btn btn-primary w-100 text-white">Đăng
											Nhập</button>
									</div>
								</form>
							</div>
							<div class="tab-pane fade" id="menu2" role="tabpanel" aria-labelledby="register-tab">
								<h3 class="mt-3">Đăng Ký</h3>
								<form action="/Assignment_JAVA/Login/register" method="post">
									<div class="row">
										<div class="col-6 mb-3">
											<label for="tenDangNhap" class="form-label">Tên đăng nhập</label> <input type="text" class="form-control"
												id="tenDangNhap" name="tenDangNhap"
												placeholder="Tên đăng nhập" required>
										</div>
										<div class="col-6  mb-3">
											<label for="hoVaTen" class="form-label">Họ và tên</label> <input
												type="text" class="form-control" id="hoVaTen" name="hoVaTen"
												placeholder="Họ và tên" required>
										</div>

									</div>
									<div class="row">
										<div class="col-6 mb-3">
											<label for="tenDangNhap" class="form-label">Email
											</label> <input type="email" class="form-control"
												id="tenDangNhap" name="email"
												placeholder="Tên đăng nhập" required>
										</div>
										<div class="col-6  mb-3">
											<label for="hoVaTen" class="form-label">Mật khẩu</label> <input
												type="password" class="form-control" id="hoVaTen" name="matKhau"
												placeholder="Họ và tên" required>
										</div>

									</div> 
									<div class="row">
										<div class=" mb-3">
											<label for="email" class="form-label">Xác nhận mật khẩu</label> <input
												type="password" class="form-control" id="email" name="xacNhanMatKhau"
												placeholder="xacNhanMatKhau" required>
										</div>
										 
									</div>

									 
									<div>
										<button type="submit" class="btn btn-primary w-100">Đăng
											Ký</button>
									</div>
								</form>
							</div>
							<div class="tab-pane fade" id="menu3" role="tabpanel"
								aria-labelledby="forget-password">
								<h3 class="mt-3">Quên Mật Khẩu</h3>
								<form action="/Assignment_JAVA/Login/confirmGmail" method="post">
									<div class="mb-3">
										<label for="Gmail" class="form-label">Nhận Gmail của
											bạn</label> <input type="text" class="form-control" id=Gmail
											name="Gmail" placeholder="Nhập Gmail của bạn" required>
									</div>
									<div>
										<button type="submit" class="btn btn-primary w-100">Xác
											nhận</button>
									</div>
								</form>
							</div>


						</div>

					</div>
				</div>
			</div>
		</c:if>
 
		<c:choose>
			<c:when test="${resultCheckEmail == 'false'}" >
				<div class="z-2 position-fixed rounded-2 justify-content-center align-items-center "
					style=" top: 50%; left: 50%; width: 600px; transform: translate(-50%, -50%); padding: 20px;">
					<div class="d-flex justify-content-center align-items-center vh-100">
						<div class="card" style="width: 18rem; background-color: rgba(91, 88, 85, 0.53);">
							<div class="card-body">
								<h3 class="mt-3 text-white">Gmail không chính xác</h3>
								  <a href="/Assignment_JAVA/Login" class="btn btn-primary " style="width: 100%;"  >Quay lại</a>
							</div>
						</div>
					</div>
				</div>
			</c:when>
		</c:choose>
		<footer class="position-absolute bottom-0 w-100 bg-dark py-3 ">
			<ul class="nav justify-content-center text-white">
				<li class="nav-item"><a class="nav-link active text-white"
					aria-current="page" href="#">Về tôi</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">Họ
						và tên: Phan Phúc Đai</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">Gmail:
						dai582005@gmail.com</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">Địa
						chỉ: Đường đến với Vì Sao</a></li>
				<li class="nav-item"><a class="nav-link disabled"
					aria-disabled="true">Disabled</a></li>
			</ul>
		</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>