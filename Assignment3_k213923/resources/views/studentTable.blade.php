@foreach ($students as $student)
<tr>
    <th>{{$loop->index + 1}}</th>
    <td id="id-{{$loop->index}}">{{$student->id}}</td>
    <td>{{$student->name}}</td>
    <td><input type="number" id="marks-{{$loop->index}}" name="marks" min="0" max="100" value="50">
    <a class="btn btn-primary add" id="add-{{$loop->index}}">ADD</a></td>
</tr>
@endforeach
<script>
    $(".add").on('click',function(event){
        event.preventDefault();
        id = $(this).attr('id');
        console.log(id);
        const regex = /^add-(\d+)$/;
        const match = id.match(regex);
        var sid,marks;
        console.log(match[1]);
        if(match){
            sid = document.getElementById('id-'+match[1]).innerText;
            marks = document.getElementById('marks-'+match[1]).value;
        }
        console.log(sid);
        console.log(marks);
        var formData = {
            cid: $('#slt').val(),
            sid: sid,
            mark: marks,
        };
        $.ajax({
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            url: '{{route("storeMark")}}',
            type: 'POST',
            data: formData,
            success: function(response){
                if(response)
                    alert('Marks Added Successfully');
                else
                    alert('Marking Failed');
                $.ajax({
                    url: "{{route('addMark')}}",
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            },
        });
    });
</script>