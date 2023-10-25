<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/caube.css">
    <script src="https://kit.fontawesome.com/c9f5871d83.js" crossorigin="anonymous"></script>
    <title>LOGIN</title>
</head>
<body>
    <div class="container">
        <header class="header">
            <nav class="nav">
                <div class="nav-left">
                <a href="#"><i class="fa-solid fa-paper-plane"></i>IMS</a>
                </div>
                <div class="nav-right">
                    <ul>
                        <a href="${pageContext.request.contextPath}"><li class="active">Home</li></a>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="login">
            <div class="left">
                <form action='LoginGoogleController' method='get'>
                    <input type='hidden' name='loginnormal' value='true'>
                    <div class="top">
                        <h2>Welcome to IMS</h2>
                        <h4>Please login</h4>
                    </div>
                    <div class="input">
                        <input type="text" name='signin1' placeholder="Email or Mobile" required>
                        <i class="fa-solid fa-envelope"></i>
                    </div>
                    <div class="input">
                        <input type="password" name='signin2' placeholder="Password" required>
                        <i class="fa-solid fa-eye"></i>
                    </div>
                    <div class="forget">
                        <a href="#">Forget password?</a><p style="color: red">${mess}</p>
                    </div>
                    <div class="btn">
                        <button>Login</button>
                    </div>
                    <div class="icon">
                        <a href="${sessionScope.loginGoogleURL}"><i class="fa-brands fa-google-plus-g"></i>Login Google</a>
                    </div>
                    <div class="or">
                        <a href='register'><p>Don't have account. Click here to sign-up</p></a>
                    </div>
                   
                </form>
            </div>
            <div class="right">
                <img src="assets/img/illustrations/illustration-signin.jpg">
            </div>
        </div>
    </div>
</body>
</html>