<?php
    $link = mysqli_connect("localhost", "root", "","ass2");
    if($link === false){
        die("ERROR: Could not connect. " . mysqli_connect_error());
    }
?>