<?php
    session_start();
    include 'connection.php';
    $sql="SELECT * from enrolled where cid='" .$_POST['cid'] ."' and tid='" .$_SESSION['id'] ."'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<table><thead><th>Rollno</th><th>Attendance</th><th></th></thead>";
            while($row=mysqli_fetch_array($result)){
                echo "<tr><td>" .$row['sid'] ."</td><td><select id='slt'><option>Present</option><option>Absent</option></select></td>";
                echo "<td><button class='update btn' data-id='".$row['sid']."'>submit</button></td></tr>";
            }
            echo "</table>";
        }
    }
?>
<script>
    $(document).ready(function(){
        $(".update").on('click',function(event){
            event.preventDefault();
            var sid = $(this).data('id');
            var cid = ('<?php echo $_POST['cid']; ?>');
            var date = ('<?php echo $_POST['date']; ?>');
            console.log(cid);
            console.log(date);
            var formData = {
                sid: sid,
                ccid: cid,
                ddate: date,
                des: $('#slt').val()=="Present"?0:1,
            };
            $.ajax({
                url: 'submitAttendance.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    $("#fetch").html(response);
                }
            });
        });
    });
</script>