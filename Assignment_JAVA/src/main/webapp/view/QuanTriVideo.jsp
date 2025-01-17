<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 
<!DOCTYPE html>
<html lang="vi">
<head>
<title>Tiểu Phẩm</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="author" content>
    <meta name="keywords" content>
    <meta name="description" content>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">

    <link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
      crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/vendor.css">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
      href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap"
      rel="stylesheet">
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body style="background-color: rgba(29, 31, 34, 0.99);">
	<div class="container">
		<header>
			<nav class="navbar navbar-expand-sm">
				<div class="container-fluid">
					<a class="navbar-brand" href=" "> <img
						src="images/logo-energy-pilates.png" alt style="width: 150px;">
					</a>
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
									href="/Assignment_JAVA/Admin/QuanTriNguoiDung"><i
										class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí
										người dùng</a></li>
										<li class="nav-item"><a
								class="nav-link text-white  text-white"
								href="/Assignment_JAVA/QuanTriCaNhan"><i class="fa-solid fa-film bi bi-file-earmark-text "></i>Quản lí cá nhân</a></li>
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
					<form action="/Assignment_JAVA/User/search" class="d-flex" role="search" style="height: 40px;">
						<input style="height: 40px;" class="form-control " type="search" placeholder="Tìm kiếm" aria-label="Search" name="keySearch">
						<button class="btn btn-outline-success" type="submit" style="height: 40px; width:140px">Tìm kiếm</button>
					</form>
				</div>
			</nav>
		</header>

		<div class="row mt-5">

			<div class="col-8">
					<div class="w-100 collapse" id="detailVideo" style="height: 410px;">
					<div>${videos.detailVideo}</div>
				</div>
				<button type="button" class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target="#detailVideo">Close</button>
				<form action="/Assignment_JAVA/Admin/doThing" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
					<div class="row">
						<div class="col-6">
							<div class="m-2">
								<label class="text-white">Mã tiểu phẩm</label>
								<br> 
								<input value="${videos.id}" type="text" placeholder="Mã tiểu phẩm" class="form-control" readonly name="id">
							</div>
							<div class="m-2">
								<label class="text-white" for="formFile" class="form-label">Ảnh đại diện tiểu phẩm</label> 
								<input value="${videos.poster}" name="poster" class="form-control" type="file" id="formFile">
							</div>
							<div class="m-2">
								<label class="text-white" for="formFile" class="form-label">Ẩn/Hiện</label>
								<div>
									<input class="form-check-input" type="checkbox" id="checkboxNoLabel" aria-label="..." value="${videos.active}" name="active" ${videos.active == true ? 'checked' : ''}>
								</div>
							</div>
						</div>
						<div class="col-6">
							<div class="m-2">
								<label class="text-white" for="formFile" class="form-label"> Tên tiểu phẩm </label> 
								<input value="${videos.title}" class="form-control" type="text" id="formFile" name="tenTieuPham">
							</div>
							<div class="m-2">
								<label class="text-white" for="formFile" class="form-label">Link/Path tiểu phẩm</label> 
								<br> 
								<input value="${videos.detailVideo}" class="form-control" type="file" id="linkTieuPham" name="linkTieuPham">
							</div>
							<div class="m-2">
								<label class="text-white" for="formFile" class="form-label"> Lượt xem của tiểu phẩm</label> 
								<input class="form-control" type="text" id="view" readonly  value="${videos.views }" name="view">
							</div>
						</div>
						<div class="m-2">
							<label class="text-white">Mô tả</label>
							<textarea type="text" placeholder="Mô tả" class="form-control" name="moTa">${videos.description}</textarea>
						</div>
						<div class="m-2">
							<button type="submit" class="btn btn-primary" name="action" value="add">Đăng tiểu phẩm</button>
							<button type="submit" class="btn btn-warning" name="action" value="edit">Sửa tiểu phẩm</button>
							<button type="submit" class="btn btn-danger" name="action"  value="delete">Xóa tiểu phẩm</button>
							<button type="submit" class="btn btn-danger" name="action"  value="reset">Làm mới</button>
						</div>
					</div>
				</form>
			</div>

			<div class=" col-4 border border-white rounded bg-dark shadow" style="height: 410px;">
	<!-- Tabs Header -->
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="max-view-tab" data-bs-toggle="tab" data-bs-target="#max-view" type="button" role="tab" aria-controls="max-view" aria-selected="true">
				Nhiều lượt xem nhất
			</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="max-like-tab" data-bs-toggle="tab" data-bs-target="#max-like" type="button" role="tab" aria-controls="max-like" aria-selected="false">
				Nhiều lượt thích nhất
			</button>
		</li>
	</ul>

	<!-- Tabs Content -->
	<div class="tab-content border-top border-white rounded-bottom" style="height: 300px; overflow-y: auto;">
		<!-- Danh sách nhiều lượt xem -->
		<div class="tab-pane fade show active" id="max-view" role="tabpanel" aria-labelledby="max-view-tab">
			<ul class="list-group mt-2">
				<c:forEach var="item" items="${videoMaxView}">
					<li class="list-group-item text-white bg-dark">
						<a href="/Assignment_JAVA/Admin/QuanTriVideo/GetVideoByID/${item.id}">
							<img style="width: 100px; height: 100px;" src="${item.poster}">
							<span>${item.title}</span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<!-- Danh sách nhiều lượt thích -->
		<div class="tab-pane fade" id="max-like" role="tabpanel" aria-labelledby="max-like-tab">
			<ul class="list-group mt-2">
				<c:forEach var="item" items="${videosMaxFavourite}">
					<li class="list-group-item text-white bg-dark">
						<a href="/Assignment_JAVA/Admin/QuanTriVideo/GetVideoByID/${item.id}">
							<img style="width: 100px; height: 100px;" src="${item.poster}">
							<span>${item.title}</span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>

		</div>
		<footer class="py-5">
			<ul class="nav justify-content-center">
				<li class="nav-item"><a class="nav-link active text-white"
					aria-current="page" href="#">Về chúng tôi</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">Gmail:
						dai582005@gmail.com</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="#">Địa
						chỉ: 40/8/1 Tân thới hòa - Tân phú</a></li>
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