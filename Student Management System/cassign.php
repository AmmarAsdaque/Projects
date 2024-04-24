<?php
    include 'connection.php';
    $sql="INSERT INTO teaches VALUES('" .$_POST['cid'] ."','" .$_POST['tid'] ."')";
    if(mysqli_query($link,$sql)){
        echo "successful";
    }else{
        echo "error";
    }
?>