<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Favicon -->
        <%@include file="header.jsp" %>
    </head>
    <body>
        <div class="container-fluid position-relative bg-white d-flex p-0">
            <!--  cho Loading           Spinner Start 
                        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                                <span class="sr-only">Loading...</span>
                            </div>
                        </div>
                         Spinner End --> 


            <!-- Sign Up Start -->
            <div class="container-fluid">
                <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                    <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                        <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                            <div class="d-flex align-items-center justify-content-between mb-3">
                                <a href="index.html" class="">
                                    <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>IMS</h3>
                                </a>
                                <h3>Sign Up</h3>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingText" placeholder="#113" required>
                                <label for="floatingText">Mobile</label>
                            </div>
                            <button type="submit" class="btn btn-primary py-3 w-100 mb-4">Sign Up</button>
                            <a href="${sessionScope.loginGoogleURL}"             
                               class="btn btn-danger w-100 mb-1 text-start">
                                <i class="fa-brands fa-google me-3" style="color: #ffffff;"></i>
                                Sign-up with <b>Google</b>
                            </a>
                            <p class="text-center mb-0">Already have an Account? <a href="">Sign In</a></p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Sign Up End -->
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
