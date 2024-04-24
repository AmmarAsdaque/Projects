<?php
    include 'connection.php';
    $sql="SELECT * FROM students";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<table class='tbl'><thead class='tbl'><th>Student id</th><th>Delete</th><th>Update</th></thead>";
            while($row=mysqli_fetch_array($result)){
                echo "<tr class='tbl'><td>".$row['id'];
                echo "</td><td><button class='delete btn' data-id='".$row['id']."'>Delete</button>";
                echo "</td><td><button class='update btn' data-id='".$row['id']."'>Update</button></td></tr>";
            }
            echo "</table>";
        }
    }
?>
<script>
    $(document).ready(function(){
        $(document).on('click', '.delete', function(event){
            event.preventDefault();
            var studentId = $(this).data('id');
            $.ajax({
                url: 'deletestd.php',
                type: 'POST',
                data: { studentId: studentId },
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
        $(document).on('click', '.update', function(event){
            event.preventDefault();
            var studentId = $(this).data('id');
            $.ajax({
                url: 'update.php',
                type: 'POST',
                data: { studentId: studentId },
                success: function(response){
                    $("#fetch").html(response);
                }
            });
        });
    });
</script>
