<?php
    include 'connection.php';
    $sql="SELECT * FROM teaches t";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<select id='slt'><option disabled selected>Select Course to register</option>";
            while($row=mysqli_fetch_array($result)){
                echo "<option>" .$row['cid']."</option>";
            }
            echo "</select><select class='slt2' id='slt2'></select><button class='btn'>Register</button>";
        }
    }
?>
<script>
    $(document).ready(function(){
        $("#slt").on('change',function(event){
            event.preventDefault();
            var formData = {
                cid: $('#slt').val(),
            };
            $.ajax({
                url: 'teacherSelect.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    $(".slt2").html(response);
                }
            });
        });
        $(".btn").on('click',function(event){
            event.preventDefault();
            var formData = {
                cid: $('#slt').val(),
                tid: $('#slt2').val(),
            };
            $.ajax({
                url: 'postRegistration.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    window.location.href='home.php';
                }
            });
        });
    });
</script>