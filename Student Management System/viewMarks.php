<?php
session_start();
    include 'connection.php';
    $sql="SELECT * from marks where sid='" .$_SESSION['id'] ."'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<table><thead><th>Course</th><th>Marks</th></thead>";
            while($row=mysqli_fetch_array($result)){
                echo "<tr><td>" .$row['cid'] ."</td><td>" .$row['marks'] ."</td></tr>";
            }
            echo "</table>";
        }
    }