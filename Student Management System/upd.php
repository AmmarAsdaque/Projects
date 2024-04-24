<?php
include 'connection.php';
$id = $_POST['studentId'];
$fname = $_POST['fname'];
$lname = $_POST['lname'];
$password = $_POST['password'];

$sql = "UPDATE students SET fname='".$fname ."', lname='".$lname ."', password='".$password ."' WHERE id='".$id."'";

if (mysqli_query($link, $sql)) {
    $ins = "UPDATE user SET password='".$password."' WHERE id='".$id."'";
    mysqli_query($link, $ins);
    echo "Record Updated successfully";
} else {
    echo "Error updating record: " . mysqli_error($link);
}
mysqli_close($link);
?>
