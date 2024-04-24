<?php
    session_start();
    include 'connection.php';
    $sql="SELECT DISTINCT C.name FROM attendance A,courses C WHERE A.sid='" .$_SESSION['id'] ." AND A.cid = C.id'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<select id='slt'><option disabled selected>Select course</option>";
            while($row=mysqli_fetch_array($result)){
                echo "<option>" .$row['C.name'] ."</option>";
            }
            echo "</select>";
        }
    }
    echo "<button class='btn' style='width: 250px;'>Check attendance</button>";
?>
<script>
    $(document).ready(function(){
        $(".btn").on('click',function(event){
            event.preventDefault();
            var formData = {
                cid: $('#slt').val(),
            };
            $.ajax({
                url: 'viewAttendance.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    $("#fetch").html(response);
                }
            });
        });
    });
</script>