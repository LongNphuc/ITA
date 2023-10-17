<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="assets/img/favicon.png">
        <title>
            OTP-Verify Email
        </title>
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
        <!-- Nucleo Icons -->
        <link href="assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
        <!-- CSS Files -->
        <link id="pagestyle" href="assets/css/material-dashboard.css?v=3.0.5" rel="stylesheet" />
        <!-- Nepcha Analytics (nepcha.com) -->
        <!-- Nepcha is a easy-to-use web analytics. No cookies and fully compliant with GDPR, CCPA and PECR. -->
        <script defer data-site="YOUR_DOMAIN_HERE" src="https://api.nepcha.com/js/nepcha-analytics.js"></script>
    </head>
    <body class="">
        <%@include file="component/header-authen.jsp" %>
        <main class="main-content  mt-0">
            <section>
                <div class="page-header min-vh-100">
                    <div class="container">
                        <div class="row">
                            <div class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
                                <div class="position-relative bg-gradient-primary h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center" style="background-image: url('assets/img/illustrations/illustration-signup.jpg'); background-size: cover;">
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
                                <div class="card card-plain">
                                    <div class="card-header">
                                        <h4 class="font-weight-bolder">Fill Information</h4>
                                        <p class="mb-0">Infor to register</p>
                                    </div>
                                    <div class="card-body">
                                        <form action="register" method="post" id="user-register">
                                        <div class="input-group input-group-outline mb-3">
                                            <input name="mobile" id="mobile" type="number" class="form-control" id="floatingInput" placeholder="Your Mobile" required>
                                        </div>
                                        <p id="fl-mobile-feedback" class="error-message" style="display: none;color: red">InValid Mobile! Try Again! </p>
                                        <div class="input-group input-group-outline mb-3">
                                            <input name="mobile" id="password" type="text" class="form-control" id="floatingInput" placeholder="Your Password" required>
                                        </div>
                                        <p id="fl-password-feedback" class="error-message" style="display: none;color: red">Password needed start with UpperCharacter and need contains one special character </p>
                                        <div class="input-group input-group-outline mb-3">
                                            <fieldset class="row accordion-body">
                                                <legend class="col-form-label col-10 pt-0">You are </legend>
                                                <div class="col-10">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="role"
                                                               id="gridRadios1" value="${sessionScope.roleTeacher}" checked>
                                                        <label class="form-check-label" for="gridRadios1">
                                                            Teacher
                                                        </label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="role"
                                                               id="gridRadios2" value="${sessionScope.roleStudent}">
                                                        <label class="form-check-label" for="gridRadios2">
                                                            Student
                                                        </label>
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                        </form>
                                        <div class="text-center">
                                            <button type="submit" class="btn btn-lg bg-gradient-primary btn-lg w-100 mt-4 mb-0" onclick="checkMobile()">Register</button>
                                        </div>
                                    </div>
                                    <div class="card-footer text-center pt-0 px-lg-2 px-1">
                                        <p class="mb-2 text-sm mx-auto">
                                            Already have an account?
                                            <a href="sign-in.html" class="text-primary text-gradient font-weight-bold">Sign in</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <!--   Core JS Files   -->
        <script src="assets/js/core/popper.min.js"></script>
        <script src="assets/js/core/bootstrap.min.js"></script>
        <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
        <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
        <script>
                                                var win = navigator.platform.indexOf('Win') > -1;
                                                var fl_email_feedback = document.getElementById('fl-otp-feedback');
                                                if (win && document.querySelector('#sidenav-scrollbar')) {
                                                    var options = {
                                                        damping: '0.5'
                                                    }
                                                    Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
                                                }

        </script>
        <script>
            function checkMobile(){
                var mobile = document.getElementById('mobile').value;
                var password = document.getElementById('password').value;
                const patterPassword = /^(?=.*[A-Z])(?=.*[\W_]).{8,}$/;
                console.log(patterPassword.test(password));
                if(mobile.length !== 10) {
                    document.getElementById('fl-mobile-feedback').style.display = 'block';
                }else if(!patterPassword.test(password)){
                     document.getElementById('fl-password-feedback').style.display = 'block';
                }else {
                    document.getElementById('user-register').submit();
                }
            }
        </script>
    </body>
</html>
