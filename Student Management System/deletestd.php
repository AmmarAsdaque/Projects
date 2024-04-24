<?php
include 'connection.php';
$id = $_POST['studentId'];
$sql = "DELETE FROM students WHERE id='".$id."'";
if (mysqli_query($link, $sql)) {
    $ins="DELETE FROM user WHERE id='".$id."'";
    mysqli_query($link,$ins);
    echo "Record deleted successfully";
} else {
    echo "Error deleting record: " .mysqli_error($link);
}
mysqli_close($link);
?>
