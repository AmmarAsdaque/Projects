<?php
    include 'connection.php';
    $sql="SELECT * FROM students WHERE id='" .$_POST['studentId'] ."'";
    if($result=mysqli_query($link,$sql)){
        $row=mysqli_fetch_array($result);
        echo "<table class='tbl'><thead class='tbl'><th colspan='2'>Update ".$_POST['studentId'] ."</thead>";
        echo "<tr class='tbl'><td>First name:";
        echo "</td><td><input type='text' name='fname'>";
        echo "<tr class='tbl'><td>Last name:";
        echo "</td><td><input type='text' name='lname'>";
        echo "<tr class='tbl'><td>Password:";
        echo "</td><td><input type='password' name='password'>";
        echo "</td></tr><tr><td colspan='2'><button class='update btn' data-id='".$row['id']."'>Update</button></td></tr>";
        echo "</table>";
    }
?>
<script>
    $(document).ready(function(){
        $(".update").on('click',function(event){
            event.preventDefault();
            var studentId = $(this).data('id');
            var formData = {
                studentId: studentId,
                fname: $('input[name=fname]').val(),
                lname: $('input[name=lname]').val(),
                password: $('input[name=password]').val()
            };
            $.ajax({
                url: 'upd.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    $.ajax({
                        url: 'updateuser.php',
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
