<?php
    include 'connection.php';
    session_start();
    $sql="INSERT INTO enrolled VALUES('" .$_POST['cid'] ."','" .$_SESSION['id'] ."','" .$_POST['tid'] ."')";
    if(mysqli_query($link,$sql)){
        echo "successful";
    }else{
        echo "error";
    }
?>