<?php
    include 'connection.php';
    $sql="INSERT into attendance VALUES('" .$_POST['ccid'] ."','" .$_POST['sid'] ."','" .$_POST['ddate'] ."','" .$_POST['des'] ."')";
    if(mysqli_query($link,$sql)){
        echo "Added";
    }else{
        echo "Error";
    }