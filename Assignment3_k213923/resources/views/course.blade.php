<h3 class="text-center">Add/View/Edit Course</h3>
<table class="table table-dark table-striped" {{$i = 1}}>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        @foreach($courses as $course)
        <tr>
            <th id="td0-{{$i}}">{{$course->id}}</th>
            <td id="td1-{{$i}}">{{$course->name}}</td>
            <td id="td2-{{$i}}" class="d-flex">
                <a href="" id="btn-{{$i}}" class="btn btn-warning custom">EDIT</a>
                <a href="" id="delete-{{$i}}" class="btn btn-danger custom">DELETE</a>
                <a href="" id="save-{{$i++}}" class="btn btn-success custom" hidden>SAVE</a>
            </td>
        </tr>
        @endforeach
        <tr>
            <td id="td1-{{$i}}"><input type="text" id="id-{{$i}}"></td>
            <td id="td2-{{$i}}"><input type="text" id="name-{{$i}}"></td>
            <td id="td3-{{$i}}" class="d-flex">
                <a href="" id="add-{{$i}}" class="btn btn-success custom">ADD</a>
            </td>
        </tr>
    </tbody>
</table>
<script>
        var oid;
        var oname;
        $(".custom").on('click',function(event){
            event.preventDefault();
            id = $(this).attr('id');
            console.log(id);
            const regex = /^btn-(\d+)$/;
            const regex1 = /^delete-(\d+)$/;
            const regex2 = /^save-(\d+)$/;
            const match = id.match(regex);
            const match1 = id.match(regex1);
            const match2 = id.match(regex2);
            //gets rows of table
            if(match){
                oid = document.getElementById('td0-'+match[1]).innerText;
                oname = document.getElementById('td1-'+match[1]).innerText;
                document.getElementById('td0-'+match[1]).innerHTML = '<input type="text" id="id-'+match[1]+'" value="'+oid+'">';
                document.getElementById('td1-'+match[1]).innerHTML = '<input type="text" id="name-'+match[1]+'" value="'+oname+'">';
                document.getElementById(id).hidden = true;
                document.getElementById('delete-'+match[1]).hidden = true;
                document.getElementById('save-'+match[1]).hidden = false;
            }else if(match1){
                var cid = document.getElementById('td0-'+match1[1]).innerText;
                console.log('In delete ' + match1[1]);
                console.log(cid);
                $.ajax({
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    url: '{{route("deleteCourse")}}',
                    type: 'DELETE',
                    data: {
                        id : cid,
                    },
                    success: function(response){
                        if(response)
                            alert('Delete Successful');
                        else
                            alert('Delete Unsuccessful');
                        $.ajax({
                            url: '{{route("course")}}',
                            type: 'GET',
                            success: function(response){
                                $("#fetch").html(response);
                            }
                        });
                    },
                });
            }else if(match2){
                var cid = document.getElementById('id-'+match2[1]).value;
                var name = document.getElementById('name-'+match2[1]).value;
                var formData = {
                    oid : oid,
                    nid : cid,
                    o_name : oname,
                    n_name : name,

                };
                $.ajax({
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    url: '{{route("update")}}',
                    type: 'PUT',
                    data: formData,
                    success: function(response){
                        if(response)
                            alert('Update Successful');
                        else
                            alert('Update Unsuccessful');
                        $.ajax({
                            url: '{{route("course")}}',
                            type: 'GET',
                            success: function(response){
                                $("#fetch").html(response);
                            }
                        });
                    },
                });
            }else if(id == 'add-'+{{$i}}){
                var cid = document.getElementById('id-{{$i}}').value;
                var name = document.getElementById('name-{{$i}}').value;
                console.log(cid);
                console.log(cid == '');
                var formData = {
                    id : cid,
                    name : name,
                };
                if(name == '' || cid == ''){
                    alert('Enter some values for id, name');
                    return;
                }
                $.ajax({
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    url: '{{route("offer")}}',
                    type: 'POST',
                    data: formData,
                    success: function(response){
                        if(response)
                            alert('Add Successful');
                        else
                            alert('Add Unsuccessful');
                        $.ajax({
                            url: '{{route("course")}}',
                            type: 'GET',
                            success: function(response){
                                $("#fetch").html(response);
                            }
                        });
                    },
                });
            }
    });
</script>