
(function($) {

    $(".toggle-password").click(function() {

        $(this).toggleClass("zmdi-eye zmdi-eye");
        var input = $($(this).attr("toggle"));
        if (input.attr("type") == "password") {
            input.attr("type", "text");
        } else {
            input.attr("type", "password");
        }
    });

})(jQuery);

$(document).ready(function() {

    $("#submitBtn"). click(function() {
        const url = "http://localhost:8080/Home/signup";
        const urlSuccess = "http://localhost:8080/Home/login";

        let gender = 'Nam';
        if($("#female").prop("checked") == true) {
            gender = "Nữ";
        }
        let fname = $("#fname").val();
        let lname = $("#lname").val();
        let username = $("#usernameRegis").val();
        let phone = $("#phoneRegis").val();
        let email = $("#emailRegis").val();
        let address = $("#address").val();
        let password = $("#password").val();
        let repassword = document.getElementById("repassword").value;
        let dateOfBirth = document.getElementById("birthday").value;

        //check validate
        let check1 = validateUserName(username);
        let check2= validatePhone(phone);
        let check3 = validateEmail(email);
        let check4 = validatePassword(password, repassword);
        let check5 = validateDate(dateOfBirth);
        if(check1 === true
            && check2 ===true
            && check3 === true
            && check4 === true
            && check5 === true){
            $.ajax({
                url:url,
                type:'post',
                data:{
                    "fname":fname,
                    "lname":lname,
                    "gender" : gender,
                    "username":username,
                    "phone":phone,
                    "email":email,
                    "password":password,
                    "repassword":repassword,
                    "address":address,
                    "dateOfBirth":dateOfBirth,

                },
                success:function(data){
                    let parsedData = JSON.parse(data);
                    if(parsedData.status === 'true'){
                        location.replace(urlSuccess)
                    }else {
                        bindingData(parsedData);
                    }

                }
            });
        }

    })
})
function bindingData(parsedData) {

    /*
    * username
    * */
    if(parsedData.userName === undefined) {
        $("#spanUserName").text('');
        $("#username").css('border','1px solid #ebebeb');
    }else{
        $("#spanUserName").text(parsedData.userName);
        $("#username").css('border','1px solid #fa4b4b');
    }

    /*
    * password
    * */
    if(parsedData.password === undefined) {
        $("#spanPassword").text('');
        $("#password").css('border','1px solid #ebebeb');
    }else{
        $("#spanPassword").text(parsedData.password);
        $("#password").css('border','1px solid #fa4b4b');
    }


    /*
    * email
    * */
    if(parsedData.email === undefined) {
        $("#spanEmail").text('');
        $("#email").css('border','1px solid #ebebeb');
    }else {
        $("#spanEmail").text(parsedData.email);
        $("#email").css('border','1px solid #fa4b4b');
    }

    /*
    * phone
    * */
    if(parsedData.phone === undefined) {
        $("#spanPhone").text('');
        $("#phone").css('border','1px solid #ebebeb');

    }else {
        $("#spanPhone").text(parsedData.phone);
        $("#phone").css('border','1px solid #fa4b4b');
    }

    /*
    * repassword
    * */

    if(parsedData.rePassword === undefined) {
        $("#spanRePassword").text('');
        $("#repassword").css('border','1px solid #ebebeb');

    }else{
        $("#spanRePassword").text(parsedData.rePassword);
        $("#repassword").css('border','1px solid #fa4b4b');
    }

    /*
    * date of birth
    * */
    if(parsedData.dateOfBirth === undefined) {
        $("#spanDateOfBirth").text('');
        $("#birthday").css('border','1px solid #ebebeb');
    }else {
        $("#spanDateOfBirth").text(parsedData.dateOfBirth);
        $("#birthday").css('border','1px solid #fa4b4b');
    }

    if(parsedData.status === 'false'){
        $("#spanStatus").text('Đăng ký thất bại.');
    }
}
function validateUserName(userName) {
    let check = true;
    let text;
    if(userName.length < 6 || userName.length > 100 ){
        text = 'Tên đăng nhập tối thiểu là 6 tối đa là 100.';
        check = false;
    }
    if(userName === ''){
        text = 'Vui lòng nhập tên đăng nhập.';
        check = false;
    }
    if(!check){
        $("#spanUserName").text(text);
        $("#username").css('border','1px solid #fa4b4b');
        return false;
    }else {
        $("#spanUserName").text('');
        $("#username").css('border','1px solid #ebebeb');
        return true;
    }

}
function validatePhone(phone) {
    let vnf_regex = /(([\+84|84|0]+(3|5|7|8|9|1))+([0-9]{8})\b)/g;

    let checkPhone = true;
    let textPhone;

    if(phone !==''){
        if (!vnf_regex.test(phone))
        {
            textPhone = 'Số điện thoại của bạn không đúng định dạng!';
            checkPhone = false;
        }
    }else{
       textPhone = 'Bạn chưa điền số điện thoại!';
       checkPhone = false;
    }

    if(!checkPhone){
        $("#spanPhone").text(textPhone);
        $("#phone").css('border','1px solid #fa4b4b');
        return false;
    }else {
        $("#spanPhone").text('');
        $("#phone").css('border','1px solid #ebebeb');
        return true;
    }

}
function validateEmail(email) {
    const emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    let checkMail = true;


    let textMail;
    if(email!==''){
        let index = email.indexOf('@');
        let sizeMail = email.substring(0,index);
        if(!emailReg.test(email) || sizeMail.length < 3){
            textMail = 'Email không đúng vui lòng kiểm tra lại!';
            checkMail = false
        }
    }else {
        textMail = 'Bạn chưa điền email!';
        checkMail = false;
    }
    if(!checkMail){
        $("#spanEmail").text(textMail);
        $("#email").css('border','1px solid #fa4b4b');
        return false;
    }else{
        $("#spanEmail").text('');
        $("#email").css('border','1px solid #ebebeb');
        return true;
    }

}
function validatePassword(password, repassword) {
    const passwordReg = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,100}$/;

    let checkPass = true;


    let textPass;
    if(password!==''){
        if(!passwordReg.test(password)){
            textPass = 'Mật khẩu quá yếu!';
            checkPass = false;
        }
        if(password.length < 8){
            textPass = 'Mật khẩu quá ngắn!';
            checkPass = false;
        }
        if(password.length > 100){
            textPass = 'Mật khẩu quá dài!';
            checkPass = false;
        }
        if(repassword !== password){
            checkPass = false;
            $("#spanRePassword").text('Mật khẩu không trùng nhau!');
            $("#repassword").css('border','1px solid #fa4b4b');
        }
    }else {
        textPass = 'Bạn chưa điền mật khẩu!';
        checkPass = false;
    }

    if(!checkPass){
        $("#spanPassword").text(textPass);
        $("#password").css('border','1px solid #fa4b4b');
        return false;
    }else{
        $("#spanPassword").text('');
        $("#password").css('border','1px solid #ebebeb');
        $("#spanRePassword").text('');
        $("#repassword").css('border','1px solid #ebebeb');
        return true;
    }

}
function validateDate(date) {
    let dateVali = new Date(date);
    let dateNow = new Date();
    let checkDate = true;
    let textDate;
    if(!isNaN(dateVali)){
        if(dateVali.getTime() > dateNow.getTime()){
            textDate = 'Vui lòng nhập ngày sinh hợp lệ!'
            checkDate = false;
        }
    }else {
        textDate = 'Bạn chưa nhập ngày sinh!'
        checkDate = false;
    }
    if(!checkDate){
        $("#spanDateOfBirth").text(textDate);
        $("#birthday").css('border','1px solid #fa4b4b');
        return false;
    }else {
        $("#spanDateOfBirth").text('');
        $("#birthday").css('border','1px solid #ebebeb');
        return true;
    }

}