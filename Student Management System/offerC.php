<?php
    include 'connection.php';
        echo "<table class='tbl'><thead class='tbl'><th colspan='2'>Offer Course</thead>";
        echo "<tr class='tbl'><td>Course ID:";
        echo "</td><td><input type='text' name='id'>";
        echo "<tr class='tbl'><td>Name:";
        echo "</td><td><input type='text' name='name'>";
        echo "</td></tr><tr><td colspan='2'><button type='submit' class='btn' id='sbt'>Offer</button></td></tr>";
        echo "</table>";
?>
<script>
    $(document).ready(function(){
        $("#sbt").on('click',function(event){
            event.preventDefault();
            var formData = {
                id: $('input[name=id]').val(),
                name: $('input[name=name]').val(),
            };
            $.ajax({
                url: 'course.php',
                type: 'POST',
                data: formData,
                success: function(response){
                    $("#fetch").html(response);
                    $.ajax({
                        url: 'offerC.php',
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
