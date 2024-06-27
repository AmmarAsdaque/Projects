<!DOCTYPE html>
<html lang="en">

<head>
    <?php session_start(); include("conn_db.php"); include('head.php');?>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/login.css" rel="stylesheet">
    <title>Forgot Password | EATERIO</title>
</head>

<body class="d-flex flex-column h-100">
    <header class="navbar navbar-light fixed-top bg-light shadow-sm mb-auto">
        <div class="container-fluid mx-4">
            <a href="index.php">
                <img src="img/LOGO_BLACK.png" width="125" class="me-2" alt="EATERIO Logo">
            </a>
        </div>
    </header>

    <div class="container form-signin mt-auto">
        <a class="nav nav-item text-decoration-none text-muted" href="#" onclick="history.back();">
            <i class="bi bi-arrow-left-square me-2"></i>Go back 
        </a>
        <form method="POST" action="cust_reset_pwd.php" class="form-floating">
            <h2 class="mt-4 mb-3 fw-normal text-bold"><i class="bi bi-key me-2"></i>Forgot Password?</h2>
            <p class="mt-4 mb-3 fw-normal">Enter your information below.</p>
            <div class="form-floating mb-2">
                <input type="text" class="form-control" id="fp_username" placeholder="Username" name="fp_username" required>
                <label for="fp_username">Username</label>
            </div>
            <div class="form-floating mb-2">
                <input type="email" class="form-control" id="fp_email" placeholder="Email" name="fp_email" required>
                <label for="fp_email">Email</label>
            </div>
            <button class="w-100 btn btn-success mb-3" type="submit">Next</button>
        </form>
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