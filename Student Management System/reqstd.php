<?php
    include 'connection.php';
    $sql="SELECT * FROM newstudents";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<table class='tbl'><thead class='tbl'><th>First name</th><th>Last name</th><th>Student id</th><th>Approval</th></thead>";
            while($row=mysqli_fetch_array($result)){
                echo "<tr class='tbl'><td>".$row['fname'];
                echo "</td><td>".$row['lname'];
                echo "</td><td name='idd'>".$row['id'];
                echo "</td><td><button class='approved' data-id='".$row['id']."'>Approved</button></td></tr>";
            }
            echo "</table>";
        }
    }
?>
<script>
    $(document).ready(function(){
        $(document).on('click', '.approved', function(event){
            event.preventDefault();
            var studentId = $(this).data('id');
            $.ajax({
                url: 'appval.php',
                type: 'POST',
                data: { studentId: studentId },
                success: function(response){
                    $.ajax({
                        url: 'reqstd.php',
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
