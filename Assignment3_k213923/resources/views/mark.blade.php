<h5 class="text-center mx-auto">Welcome!! <br>Marking</h5>
<div>
<select id='slt' class="mx-auto">
    <option disabled selected value="default">Select course</option>
    @foreach ($courses as $course)
        <option value="{{$course->id}}">{{$course->id.'-'.$course->name}}</option>
    @endforeach
</select>
</div>
<table class='table table-dark table-striped'>
    <thead>
        <th>#</th>
        <th>Rollno</th>
        <th>Name</th>
        <th>Marks</th>
    </thead>
    <tbody id='tbody'>

    </tbody>
</table>
<script>
    $(document).ready(function(){
        $("#slt").on('change',function(event){
            event.preventDefault();
            var formData = {
                cid: $('#slt').val(),
            };
            $.ajax({
                url: '{{route("studentTable")}}',
                type: 'GET',
                data: formData,
                success: function(response){
                    console.log(response);
                    $("#tbody").html(response);
                }
            });
        });
    });
</script>