<%--
  Created by IntelliJ IDEA.
  User: san vui
  Date: 11/12/2021
  Time: 1:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>My Store</title>
        <!-- Font Icon -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/css/regis.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="./_header.jsp"></jsp:include>
        <div class="main">
            <section class="signup">
                <!-- <img src="images/signup-bg.jpg" alt=""> -->
                <div class="container">
                    <div class="signup-content">
                        <h2 class="form-title">Đăng ký tài khoản</h2>
                        <form method="POST" id="signup-form" class="./registration">
                            <%--@declare id="basic-url"--%>
                                <label class="d-block text-center" for="basic-url">Chú ý nội dung có <span style = "color:red;">(*)</span> là bắt buộc </label>
                                <br>

                                <label class="d-block text-center" id="spanStatus" style="color: red;" for="basic-url"></label>
                                <br>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="basic-url">Họ và tên đệm</label>
                                        <input type="text" class="form-control" name="fname" id="fname" placeholder="First Name" >
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="basic-url">Tên</label>
                                        <input type="text" class="form-control" name="lname" id="lname" placeholder="Last Name" >
                                    </div>
                                </div>

                                <label  for="basic-url">Tên đăng nhập <span style = "color:red;">(&ast;)</span>
                                    <span id="spanUserName" style = "color:red;"></span>
                                </label>
                            <div class="form-group">
                                <input type="text" class="form-input" name="usernameRegis" id="usernameRegis" placeholder="User Name"/>
                            </div>

                                <label  for="basic-url">Số điện thoại <span style = "color:red;">(&ast;)</span>
                                    <span id="spanPhone" style = "color:red;">  </span>
                                </label>
                            <div class="form-group">
                                <input type="text" class="form-input" name="phoneRegis" id="phoneRegis" placeholder="Phone number"/>
                            </div>

                                <label  for="basic-url">Ngày sinh <span style = "color:red;">(&ast;)</span>
                                    <span id="spanDateOfBirth" style = "color:red;">  </span>
                                </label>
                            <div class="form-group">
                                <input type="date" class="form-input" data-date-format="dd/M/yyyy" name="birthday" id="birthday"/>
                            </div>

                                <label  for="basic-url">Giới tính </label>
                            <div class="form-check form-check-inline">
                                <input checked class="form-check-input" type="radio" name="gender" id="male" value="Nam">
                                <label class="form-check-label" for="male">Nam</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="female" value="Nữ">
                                <label class="form-check-label" for="female">Nữ</label>
                            </div>
                                <br>
                                <label  for="basic-url">Email <span style = "color:red;">(&ast;)</span>
                                    <span id="spanEmail" style = "color:red;"></span>
                                </label>
                            <div class="form-group">
                                <input type="email" class="form-input" name="emailRegis" id="emailRegis" placeholder="Your Email"/>
                            </div>

                                <label  for="basic-url">Địa chỉ </label>
                            <div class="form-group">
                                <input type="text" class="form-input" name="address" id="address" placeholder="Address"/>
                            </div>


                                <label  for="basic-url">Mật khẩu <span style = "color:red;">(&ast;)</span>
                                    <span id="spanPassword" style = "color:red;"> </span>
                                </label>
                            <div class="form-group">
                                <input type="password" class="form-input" name="password" id="password" placeholder="Password"/>
                                <span toggle="#password" class="zmdi zmdi-eye-off field-icon toggle-password"></span>
                            </div>

                                <label  for="basic-url">Xác nhận mật khẩu <span style = "color:red;">(&ast;)</span>
                                    <span id="spanRePassword" style = "color:red;"> </span>
                                </label>
                            <div class="form-group">
                                <input type="password" class="form-input" name="repassword" id="repassword" placeholder="Repeat your password"/>
                                <span toggle="#repassword" class="zmdi zmdi-eye-off field-icon toggle-password"></span>
                            </div>
                            <div class="form-group">
                                <input type="submitBtn" name="submitBtn" id="submitBtn" class="form-submit" value="Đăng Ký"/>
                            </div>
                        </form>
                        <p class="loginhere">
                            Bạn đã có tài khoản ? <a href="./login" class="loginhere-link">Đăng nhập</a>
                        </p>
                    </div>
                </div>
            </section>
        </div>
        <jsp:include page="./_footer.jsp"></jsp:include>

        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/views/assets/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/views/assets/js/regis.js"></script>
    </body>
</html>
