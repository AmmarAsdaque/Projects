<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WE Assignment 2 k213923</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        body{
            margin: 0%;
        }
        nav{
            background-color: deepskyblue;
            padding: 2vh;
            height: 5vh;
            text-align: center;
            font-size: 20px;
            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
        }
        .content{
            display: flex;
        }
        .sidebar{
            width: 25%;
            height: 91vh;
            background-color: burlywood;
        }
        .container{
            width: 75%;
        }
        .info{
            background-color: chartreuse;
            text-align: center;
            font-size: 20px;
            padding-top: 20px;
            padding-bottom: 30px;
        }
        .btn{
            margin-top: 20px;
            background-color: chartreuse;
            border: none;
            width: 90%;
            margin-left: 20px;
            font-size: 20px;
            padding-top: 10px;
            padding-bottom: 10px;
            padding-left: 40px;
            padding-right: 40px;
            border-radius: 5rem;
        }
        .approved{
            background-color:cornflowerblue;
            border: none;
            font-size: 20px;
            padding-top: 10px;
            padding-bottom: 10px;
            padding-left: 40px;
            padding-right: 40px;
            border-radius: 5rem;
        }
        .approved:hover{
            background-color: burlywood;
            border: 2px solid white;
        }
        table{
            width: 95%;
            margin: 30px;
            background-color: burlywood;
        }
        table,thead,tr,td,th{
            border-radius: 10px;
            padding: 20px;
            border: 2px solid white;
        }
        .btn:hover{
            background-color: burlywood;
            border: 2px solid white;
        }
        #logout{
            float: right;
            background-color: burlywood;
            padding-top: 10px;
            padding-bottom: 10px;
            padding-right: 20px;
            padding-left: 20px;
            border: none;
            border-radius: 5rem;
            margin-right: 10px;
        }
        #logout:hover{
            background-color: chartreuse;
            border: 2px solid white;
        }
        #cid,#tid{
            background-color: chartreuse;
            border: 2px solid burlywood;
            width: 25%;
            margin: 40px;
            border-radius: 5rem;
            padding: 10px;
        }
    </style>
</head>
<body>
    <nav>
        <span>Student Management System</span>
    </nav>
    <div class="content">
        <div class="sidebar">
            <?php
                session_start();
                if(isset($_SESSION['role'])){
                    if($_SESSION['role']==0){
                        echo "<div style='margin-top: 50px;'><button class='btn' id='btn0'>Request Approval</button><br>";
                        echo "<button class='btn' id='btn1'>Update/Delete User's</button><br>";
                        echo "<button class='btn' id='btn2'>Offer Courses</button><br>";
                        echo "<button class='btn' id='btn3'>Assign Course to Faculty</button><br></div>";
                    }else if($_SESSION['role']==1){
                        echo "<div style='margin-top: 50px;'><button class='btn' id='btn4'>Add Attendance</button><br>";
                        echo "<button class='btn' id='btn5'>Add Marks</button><br></div>";
                    }else{
                        echo "<div style='margin-top: 50px;'><button class='btn' id='btn6'>View Details</button><br>";
                        echo "<button class='btn' id='btn7'>View Marks</button><br>";
                        echo "<button class='btn' id='btn8'>Register Course</button><br>";
                        echo "<button class='btn' id='btn9'>View Attendance</button><br></div>";
                    }
                }
            ?>
        </div>
        <div class="container">
            <div class="info">
                <?php
                    if(isset($_SESSION['role'])){
                        if($_SESSION['role']==0){
                            echo "<span>You are Admin</span>";
                        }else if($_SESSION['role']==1){
                            echo "<span>You are Teacher</span>";
                        }else{
                            echo "<span>You are Student</span>";
                        }
                    }
                ?>
                <button id="logout" onclick="logout()">Logout</button>
            </div>
            <div id="fetch">

            </div>
        </div>
    </div>
    <script>
        $(document).ready(function(){
            $("#btn0").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'reqstd.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            }); 
            $("#btn1").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'updateuser.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn2").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'offerC.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn3").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'assign.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn4").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'addAttendance.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn5").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'addMark.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn6").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'viewDetails.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn7").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'viewMarks.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn8").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'courseRegistration.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn9").click(function(event){
                event.preventDefault();
                $.ajax({
                    url: 'checkAttendance.php',
                    type: 'POST',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
        });
        function logout(){
            window.location.href="login.php";
            session_destroy();
        }
    </script>
</body>
</html>