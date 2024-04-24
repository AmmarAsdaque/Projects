<?php
session_start();
    include 'connection.php';
    $sql="SELECT * from attendance where sid='" .$_SESSION['id'] ."' and cid='" .$_POST['cid'] ."'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<table><thead><th>Date</th><th>Description</th></thead>";
            while($row=mysqli_fetch_array($result)){
                echo "<tr><td>" .$row['date'] ."</td><td>" .$row['present']==1?"P":"A" ."</td></tr>";
            }
            echo "</table>";
        }
    }