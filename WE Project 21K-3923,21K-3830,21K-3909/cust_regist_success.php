<!DOCTYPE html>
<html lang="en">

<head>
    <?php session_start(); include("conn_db.php"); include('head.php');?>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/login.css" rel="stylesheet">

    <title>Successfully Registered | EATERIO</title>
</head>

<body class="d-flex flex-column h-100">
    <header class="navbar navbar-light fixed-top bg-light shadow-sm mb-auto">
        <div class="container-fluid mx-4">
            <a href="index.php">
                <img src="img/LOGO_BLACK.png" width="125" class="me-2" alt="EATERIO Logo">
            </a>
        </div>
    </header>
    <div class="mt-5"></div>
    <div class="container form-signin text-center reg-success mt-auto">
            <i class="mt-4 bi bi-check-circle text-success h1 display-2"></i>
            <h3 class="mt-2 mb-3 fw-normal text-bold">Your account is ready!</h3>
            <p class="mb-3 fw-normal text-bold">Welcome and enjoy your food with EATERIO</p>
            <a class="btn btn-success btn-sm w-50" href="index.php">Return to Home</a>
    </div>

    <footer
    class="footer d-flex flex-wrap justify-content-between align-items-center px-5 py-3 mt-auto bg-secondary text-light">
        <span class="smaller-font">&copy; 2024 WE Project<br /><span class="xsmall-font">Ammar A.,Anser T.,Faaiz K.</span>
        </span>
        <ul class="nav justify-content-end list-unstyled d-flex">
            <li class="ms-3"><a class="text-light" target="_blank" href="https://github.com/FaaizKadiwal"><i
                        class="bi bi-github"></i></a></li>
                        <li class="ms-3"><a class="text-light" target="_blank" href="https://www.linkedin.com/in/syed-anser-tayyab-7ba232258?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"><i
                        class="bi bi-linkedin"></i></a></li>
                        <li class="ms-3"><a class="text-light" target="_blank" href="https://www.linkedin.com/in/syed-ammar-asdaque-25b984b0/"><i
                        class="bi bi-linkedin"></i></a></li>
        </ul>
    </footer>
</body>

</html>