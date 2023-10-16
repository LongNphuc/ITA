<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to IMS</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            overflow: hidden;
            font-family: Arial, sans-serif;
        }
        .background-image {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            z-index: -1;
        }
        .landing-page {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .welcome-text {
            font-size: 48px;
            margin-bottom: 20px;
            color: aqua;
        }
        .animated-text {
            font-size: 36px;
            white-space: nowrap;
            overflow: hidden;
            border-right: 4px solid transparent;
            animation: typing 2s steps(30, end);
            color: aqua;
        }
        @keyframes typing {
            from {
                width: 0
            }
            to {
                width: 100%
            }
        }
        @keyframes blink-caret {
            from, to {
                border-color: transparent
            }
            50% {
                border-color: orange
            }
        }
        .buttons {
            position: absolute;
            top:    10px;
            right: 20px;
        }
        .button {
            background-color: violet;
            color: black;
            padding: 10px 20px;
            border: none;
            border-radius: 90px;
            margin: 3px;
            cursor: pointer;
            text-decoration: none;
        }
        .info-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 80%;
            max-width: 800px;
            background: rgba(0, 0, 0, 0.7);
            border-radius: 10px;
            padding: 10px;
            margin-top: 20px;
        }
        .info-box {
            flex: 1;
            text-align: center;
            padding: 10px;
            color: white;
        }
        .info-box .icon {
            font-size: 36px;
        }
    </style>
</head>
<body>
    <img class="background-image" src="https://img.freepik.com/premium-photo/colorful-wave-wallpaper_969965-5020.jpg?size=626&ext=jpg&ga=GA1.1.1413502914.1697155200&semt=ais" alt="Full Screen Background Image">
    <div class="landing-page">
        <div class="welcome-text">Welcome</div>
        <div class="animated-text">IMS</div>
        <div class="animated-text">System Controlling Issue</div>
        <br><br>
        <br>
        <div class="info-section">
            <div class="info-box">
                <div class="icon">⏰</div>
                <div>Time</div>
                <div>Saving time to resolve issue</div>
            </div>
            <div class="info-box">
                <div class="icon">🔍</div>
                <div>Reach</div>
                <div>Easy to use and goal reached</div>
            </div>
            <div class="info-box">
                <div class="icon">✔</div>
                <div>Effective</div>
                <div>Resolve issue easy</div>
            </div>
        </div>
    </div>
    <div class="buttons">
        <a href="sign-up.jsp" class="button">Sign Up</a>
        <button class="button">Sign In</button>
    </div>
</body>
</html>
