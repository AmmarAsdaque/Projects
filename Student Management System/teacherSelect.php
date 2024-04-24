<?php
    include 'connection.php';
    $sql="SELECT * FROM teaches C WHERE C.CID='" .$_POST['cid'] ."'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<option disabled selected>Select Teacher that teaching that course</option>";
            while($row=mysqli_fetch_array($result)){
                echo "<option>" .$row['tid']."</option>";
            }
        }
    }else{
        echo "error";
    }