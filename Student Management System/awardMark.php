<?php
    include 'connection.php';
    $sql="INSERT into marks VALUES('" .$_POST['cid'] ."','" .$_POST['sid'] ."','" .$_POST['tid'] ."','" .$_POST['mark'] ."')";
    if(mysqli_query($link,$sql)){
        echo "Added";
    }else{
        echo "Error";
    }