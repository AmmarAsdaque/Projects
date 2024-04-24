<?php
    include 'connection.php';
    echo "<select id='cid'><option>Select course id</option>";
    $sql1="SELECT * FROM courses";
    $sql2="SELECT * FROM teacher";
    if($result=mysqli_query($link,$sql1)){
        if(mysqli_num_rows($result)>0){
            while($row=mysqli_fetch_array($result)){
                echo "<option>" .$row['id'] ."</option>";
            }
            echo "</select>";
        }
    }
    echo "<select id='tid'><option>Select teacher</option>";
    if($result=mysqli_query($link,$sql2)){
        if(mysqli_num_rows($result)>0){
            while($row=mysqli_fetch_array($result)){
                echo "<option>" .$row['id'] ."</option>";
            }
            echo "</select>";
        }
    }
    echo "<button id='sbt' class='btn'>Assign</button>";
?>
<script>
    $(document).ready(function(){
        $("#sbt").on('click',function(event){
            event.preventDefault();
            var formData = {
                cid: $('#cid').val(),
                tid: $('#tid').val(),
            };
            $.ajax({
                url: 'cassign.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    $("#fetch").html(response);
                    $.ajax({
                        url: 'assign.php',
                        type: 'POST',
                        success: function(response){
                            $("#fetch").html(response);
                        }
                    });
                }
            });
        });
    });
</script>