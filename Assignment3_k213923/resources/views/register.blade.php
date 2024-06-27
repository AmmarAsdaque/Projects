<h3 class="text-center m-2">Course Registration</h3>
<div class="d-flex justify-content-between">
    <div>
    <select id='slt'>
        <option disabled selected value="default">Select Course to Register</option>
        @foreach ($courses as $course)
            <option value="{{$course->id}}">{{$course->id.'-'.$course->name}}</option>
        @endforeach
    </select>
    <select id='slt2'></select>
    </div>
    <a class='btn btn-warning custom'>Register</a>
</div>
<script>
$(document).ready(function(){
    $("#slt").on('change',function(event){
        event.preventDefault();
        var formData = {
            cid: $('#slt').val(),
        };
        if(formData['cid']=='default'){
            alert('Please select course');
            return;
        }
        $.ajax({
            url: '{{route("show")}}',
            type: 'GET',
            data: formData,
            success: function(response){
                console.log(response);
                var teachers = response.teachers;
                var html = '';
                if (teachers.length > 0){
                    html += '<option disabled selected value="default">Select Teacher to Register</option>'
                    for (let index = 0; index < teachers.length; index++) {
                        var teacher = teachers[index];
                        html += '<option value="'+teacher['id']+'">'+teacher['name']+'</option>';    
                    }
                }
                else
                    html += '<option value="default">No teachers</option>';
                $("#slt2").html(html);
            }
        });
    });
    $(".custom").on('click',function(event){
        event.preventDefault();
        var formData = {
            cid: $('#slt').val(),
            tid: $('#slt2').val(),
        };
        if(formData['cid']=='default'||formData['tid']=='default'||formData['cid']==undefined||formData['tid']==undefined){
            return;
        }
        $.ajax({
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            url: '{{route("register")}}',
            type: 'POST',
            data: formData,
            success: function(response){
                if(response)
                    alert('Registered Successfully');
                else
                    alert('Registration Failed');
            },
        });
    });
});
</script>