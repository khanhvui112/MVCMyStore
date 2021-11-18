<%--
  Created by IntelliJ IDEA.
  User: san vui
  Date: 11/11/2021
  Time: 12:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/fonts/icomoon/style.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/home/owl.carousel.min.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!-- Font Awesome -->
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/home/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/home.css">

    </head>
    <body>
        <header class="site-navbar js-sticky-header site-navbar-target bg-info" role="banner">
            <div class="px-4">
                <div class="row align-items-center position-relative">
                    <div class="site-logo">
                        <a href="#" id="logone" class="text-black"><span style="font-size: 2rem;" class="text-white">My Store</a>
                    </div>
                    <div class="col-12">
                        <nav class="site-navigation text-right ml-auto menu-hover-hover" role="navigation">


                            <ul class="site-menu main-menu js-clone-nav ml-auto d-none d-lg-block">
                                <div class="menu-search">
                                    <input class="inline-block home-menu-search mr-sm-2 " type="search" placeholder="Bạn muốn tìm gì?" aria-label="Search">
                                    <button class="btn btn-primary my-2 my-sm-0" type="submit">Tìm</button>
                                </div>
                                <p style="display: none;" id="username">${loginDTO.getUserName()}</p>
                            </ul>
                        </nav>

                    </div>
                </div>
            </div>
        </header>
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/views/assets/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/views/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/views/assets/js/jquery.sticky.js"></script>
        <script src="${pageContext.request.contextPath}/views/assets/js/home.js"></script>
    </body>
</html>