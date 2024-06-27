{{-- <h1 class="text-center">Check Attendance</h1>
<div class="mx-auto text-center mt-5">
    <select id='slt' class="">
        <option disabled selected>Select course</option>
        @foreach ($courses as $course)
            <option value="{{$course->id}}">{{$course->id . '-'.$course->name}}</option>
        @endforeach
    </select><br>
    <a href="#" class="btn btn-primary items-center text-center mt-3">Check Attendance</a>
</div>
<script>
    $(document).ready(function(){
        $(".btn").on('click',function(event){
            event.preventDefault();
            var formData = {
                cid: $('#slt').val(),
            };
            console.log($('#slt').val());
            if(formData.cid == null){
                return;
            }
            document.getElementById("fetch").innerHTML = "";
            $.ajax({
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    url: '{{route('viewAttendance')}}',
                    type: 'POST',
                    async: false,
                    data: formData,
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
        });
</script> --}}
<h3 class="text-center m-2">Attendance</h3>
<table class="table table-dark table-striped" {{$i = 1}}>
    <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Course</th>
          <th scope="col">Date</th>
          <th scope="col">Present</th>
        </tr>
    </thead>
    @foreach ($attendance as $attend)
        <tr>
            <th>
                {{$i++}}
            </th>
            <td>
                {{$attend->name}}
            </td>
            <td>
                {{$attend->date}}
            </td>
            <td>
                {{$attend->present?'Present':'Absent'}}
            </td>
        </tr>
    @endforeach
</table>