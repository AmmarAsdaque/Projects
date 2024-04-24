<?php
    session_start();
    include 'connection.php';
    $sql="SELECT * from enrolled where cid='" .$_POST['cid'] ."' and tid='" .$_SESSION['id'] ."'";
    if($result=mysqli_query($link,$sql)){
        if(mysqli_num_rows($result)>0){
            echo "<table><thead><th>Rollno</th><th>Marks</th><th></th></thead>";
            while($row=mysqli_fetch_array($result)){
                echo "<tr><td>" .$row['sid'] ."</td><td><input class='inp' name='mark' type='text'></td>";
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
            var cid = '<?php echo $_POST['cid']; ?>';
            var tid = '<?php echo $_SESSION['id']; ?>';
            var formData = {
                sid: sid,
                cid: cid,
                tid: tid,
                mark: $('input[name=mark]').val(),
            };
            $.ajax({
                url: 'awardMark.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    window.location.href="home.php";
                }
            });
        });
    });
</script>