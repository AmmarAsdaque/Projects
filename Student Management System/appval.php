<?php
include 'connection.php';
$id = $_POST['studentId'];
$sql1="SELECT * FROM newstudents WHERE id='" .$id ."'";
$sql = "DELETE FROM newstudents WHERE id='".$id."'";
if ($result=mysqli_query($link, $sql1)) {
    $row=mysqli_fetch_array($result);
    $ins="INSERT INTO user VALUES('" .$row['id'] ."','" .$row['password'] ."'," ."2)";
    mysqli_query($link,$ins);
    $ins2="INSERT INTO students VALUES('" .$row['id'] ."','" .$row['fname'] ."','" .$row['lname'] ."','" .$row['password'] ."')";
    mysqli_query($link,$ins2);
    mysqli_query($link,$sql);
    echo "Record deleted successfully";
} else {
    echo "Error deleting record: " .mysqli_error($link);
}
mysqli_close($link);
?>
