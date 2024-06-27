<h5 class="text-center mx-auto">Welcome {{$teacher->name}}!! <br>Attendance</h5>
<div>
<select id='slt' class="mx-auto">
    <option disabled selected value="default">Select course</option>
    @foreach ($courses as $course)
        <option value="{{$course->id}}">{{$course->id.'-'.$course->name}}</option>
    @endforeach
</select>
<input id='date' name='date' type='date' placeholder='Enter date' value="{{ date('Y-m-d') }}">
</div>
<table class='table table-dark table-striped'>
    <thead>
        <th>#</th>
        <th>Rollno</th>
        <th>Name</th>
        <th>Attendance</th>
    </thead>
    <tbody id='tbody'>

    </tbody>
</table>
<a href="" class="mx-auto btn btn-warning custom">Submit</a>
<script>
    $(document).ready(function(){
        $("#slt").on('change',function(event){
            event.preventDefault();
            var formData = {
                cid: $('#slt').val(),
            };
            if(formData['cid']==''){
                alert('Please select course');
                return;
            }
            $.ajax({
                url: '{{route("student")}}',
                type: 'GET',
                data: formData,
                success: function(response){
                    console.log(response);
                    var data = response.data;
                    console.log(data);
                    var html = '';
                    if (data.length > 0){
                        for (let index = 1; index <= data.length; index++) {
                            var datum = data[index-1];
                            html += '<tr>\
                                <th>'+index+'</th>\
                                <td>'+datum.id+'</td>\
                                <td>'+datum.name+'</td>\
                                <td><select id="slt2"><option value="0" selected>Absent</option><option value="1">Present</option></select></td>\
                            </tr>';    
                        }
                    }
                    else
                        html += '<tr><td colspan="4">No students in this class</td></tr>';
                    $("#tbody").html(html);
                }
            });
        });
        $(".custom").on('click',function(event){
            event.preventDefault();
            var oTable = document.querySelector('.table');
            //gets rows of table
            var rowLength = oTable.rows.length;
            let dat = [];
            for (i = 1; i < rowLength; i++){
                dat.push([])
                var oCells = oTable.rows.item(i).cells;
                var cellVal = oCells.item(1).innerHTML;
                dat[i-1].push(cellVal);
                cellVal = document.getElementById('slt2').value;
                dat[i-1].push(cellVal);
                console.log(cellVal);
            }
            var formData = {
                cid: $('#slt').val(),
                date: $('#date').val(),
                students: dat,
            };
            if(formData['cid']=='default'||formData['cid']==undefined||formData['date']==undefined||formData['students'].length==0){
                return;
            }
            $.ajax({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                url: '{{route("store")}}',
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