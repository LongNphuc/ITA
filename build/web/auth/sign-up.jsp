<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Favicon -->
        <%@include file="../component/header.jsp" %>
    </head>
    <body>
        <div class="container-fluid position-relative bg-white d-flex p-0">
            <!--            cho Loading           Spinner Start 
                        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                                <span class="sr-only">Loading...</span>
                            </div>
                        </div>-->
            <!--Spinner End-->  



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
                            <form action="register" method="get" id="register-mobile">
                                <input type="hidden" name="flag" value="mobile">
                                <div class="form-floating mb-3" id="sender">
                                    <input type="text" name="mobile" class="form-control" id="mobile" placeholder="#113" required>
                                    <label for="floatingText">Mobile</label>
                                    <div id="recaptcha-container"></div>
                                    <input type="button" class="btn btn-primary py-3 w-100 mb-4" id="send" value="Send OTP" onClick="checkMobileExist()">
                                    <a href="${sessionScope.loginGoogleURL}"             
                                       class="btn btn-danger w-100 mb-1 text-start">
                                        <i class="fa-brands fa-google me-3" style="color: #ffffff;"></i>
                                        Sign-up with <b>Google</b>
                                    </a>
                                    <p class="text-center mb-0">Already have an Account? <a href="LoginGoogleController?signin=true">Sign In</a></p>
                                    <div style="color: red" id="error"></div>
                                </div>
                            </form>
                            <div id="verifier" style="display: none">
                                <input type="text" id="verificationcode" placeholder="OTP Code">
                                <input type="button" id="verify" value="Verify" onClick="codeverify()">
                                <div class="p-conf">Number Verified</div>
                                <div class="n-conf">OTP ERROR</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-app-compat.js"></script>
        <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-auth-compat.js"></script>
        <script>
                                    var number = document.getElementById('mobile').value.trim();
                                    if (number.charAt(0) === '0') {
                                        number = '+84' + number.substr(1);
                                    }
                                    // For Firebase JS SDK v7.20.0 and later, measurementId is optional
                                    const firebaseConfig = {
                                        apiKey: "AIzaSyC-inrRSFkrUDlVql7fmFwbGUnK-ZPcDL8",
                                        authDomain: "ita301.firebaseapp.com",
                                        projectId: "ita301",
                                        storageBucket: "ita301.appspot.com",
                                        messagingSenderId: "294131505979",
                                        appId: "1:294131505979:web:fbf8938901cf6d29e9351e",
                                        measurementId: "G-PZ8H0XR4X3"
                                    };

                                    firebase.initializeApp(firebaseConfig);
                                    render();
                                    function render() {
                                        window.recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
                                        recaptchaVerifier.verify();
                                    }
                                    // function for send message

                                    function phoneAuth() {
                                        firebase.auth().signInWithPhoneNumber(number, window.recaptchaVerifier).then(function (confirmationResult) {
                                            window.confirmationResult = confirmationResult;
                                            coderesult = confirmationResult;
                                            document.getElementById('sender').style.display = 'none';
                                            document.getElementById('verifier').style.display = 'block';
                                        }).catch(function (error) {
                                            alert(error.message);
                                        });
                                    }
                                    // function for code verify
                                    function codeverify() {
                                        var code = document.getElementById('verificationcode').value;
                                        coderesult.confirm(code).then(function () {
                                            document.getElementsByClassName('p-conf')[0].style.display = 'block';
                                            document.getElementsByClassName('n-conf')[0].style.display = 'none';
                                            document.getElementById('register-mobile').submit();
                                        }).catch(function () {
                                            document.getElementsByClassName('p-conf')[0].style.display = 'none';
                                            document.getElementsByClassName('n-conf')[0].style.display = 'block';
                                            return false;
                                        });
                                    }

                                    var listMobile;
                                    let flag = false;
                                    function checkMobileExist() {
                                        const request = new XMLHttpRequest();
                                        request.open("get", "sourcServiceApi?key=listMobileExist", true);
                                        request.onreadystatechange = function () {
                                            if (this.readyState === 4 && this.status === 200) {
                                                listMobile = JSON.parse(this.responseText);
                                                console.log(listMobile);
                                                listMobile.forEach(function (mobile) {
                                                    if (number === mobile) {
                                                        flag = true;
                                                        console.log(mobile);
                                                    }
                                                });
                                                if (flag) {
                                                    const errorDiv = document.getElementById("error");
                                                    errorDiv.textContent = "Mobile was exist";
                                                }else {
                                                    phoneAuth();
                                                }
                                            }
                                        };
                                        request.send();
                                    }

        </script>
        <script>

        </script>

        <%@include file="../component/footer.jsp"%>
    </body>
</html>
