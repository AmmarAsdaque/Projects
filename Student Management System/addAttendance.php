<?php
    session_start();
    include 'connection.php';
    $sql="SELECT * FROM teaches WHERE TID='" .$_SESSION['id'] ."'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<select id='slt'><option disabled selected>Select course</option>";
            while($row=mysqli_fetch_array($result)){
                echo "<option>" .$row['cid'] ."</option>";
            }
            echo "</select>";
        }
    }
    echo "<input class='inp' name='date' type='text' placeholder='Enter date'><button class='btn' style='width: 250px;'>Add attendance</button>";
?>
<script>
    $(document).ready(function(){
        $(".btn").on('click',function(event){
            event.preventDefault();
            var formData = {
                date: $('input[name=date]').val(),
                cid: $('#slt').val(),
            };
            $.ajax({
                url: 'markAttendance.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    $("#fetch").html(response);
                }
            });
        });
    });
</script>