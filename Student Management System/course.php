<?php
    include 'connection.php';
    if(isset($_POST['id']) && isset($_POST['name'])) {
        $id = $_POST['id'];
        $name = $_POST['name'];
        $sql = "INSERT INTO courses VALUES ('".$id."','".$name."')";
        if(mysqli_query($link,$sql)) {
            echo "Course offered successfully";
        } else {
            echo "Oops! Something went wrong. Please try again later.";
        }
    } else {
        echo "Error: Course ID or Name not provided.";
    }
?>