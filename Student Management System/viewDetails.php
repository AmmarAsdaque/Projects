<?php
    session_start();
    include 'connection.php';
    $sql="SELECT * from students where id='" .$_SESSION['id'] ."'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<table><thead><th>Fname</th><th>Lnsme</th><th>ID</th><th>Password</th><th>Image</th></thead>";
            while($row=mysqli_fetch_array($result)){
                echo "<tr><td>" .$row['fname'] ."</td><td>" .$row['lname'] ."</td><td>" .$row['id'] ."</td><td>" .$row['password'] ."</td><td><img src='" .$row['lname'] ."'></td></tr>";
            }
            echo "</table>";
        }
    }