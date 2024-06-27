<h3 class="text-center">Add/View/Edit Teacher</h3>
<table class="table table-dark table-striped" {{$i = 1}}>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        @foreach($teachers as $teacher)
        <tr>
            <th id="td0-{{$i}}">{{$teacher->id}}</th>
            <td id="td1-{{$i}}">{{$teacher->name}}</td>
            <td id="td2-{{$i}}">{{$teacher->password}}</td>
            <td id="td3-{{$i}}" class="d-flex">
                <a href="" id="btn-{{$i}}" class="btn btn-warning custom">EDIT</a>
                <a href="" id="delete-{{$i}}" class="btn btn-danger custom">DELETE</a>
                <a href="" id="save-{{$i++}}" class="btn btn-success custom" hidden>SAVE</a>
            </td>
        </tr>
        @endforeach
        <tr>
            <td id="td1-{{$i}}"><input type="text" id="id-{{$i}}"></td>
            <td id="td2-{{$i}}"><input type="text" id="name-{{$i}}"></td>
            <td id="td3-{{$i}}"><input type="text" id="password-{{$i}}"></td>
            <td id="td4-{{$i}}" class="d-flex">
                <a href="" id="add-{{$i}}" class="btn btn-success custom">ADD</a>
            </td>
        </tr>
    </tbody>
</table>
<script>
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
                var name = document.getElementById('td1-'+match[1]).innerText;
                var password = document.getElementById('td2-'+match[1]).innerText;
                document.getElementById('td1-'+match[1]).innerHTML = '<input type="text" id="name-'+match[1]+'" value="'+name+'">';
                document.getElementById('td2-'+match[1]).innerHTML = '<input type="text" id="password-'+match[1]+'" value="'+password+'">';
                document.getElementById(id).hidden = true;
                document.getElementById('delete-'+match[1]).hidden = true;
                document.getElementById('save-'+match[1]).hidden = false;
            }else if(match1){
                var tid = document.getElementById('td0-'+match1[1]).innerText;
                console.log('In delete ' + match1[1]);
                console.log(tid);
                $.ajax({
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    url: '{{route("deleteTeacher")}}',
                    type: 'DELETE',
                    data: {
                        id : tid,
                    },
                    success: function(response){
                        if(response)
                            alert('Delete Successful');
                        else
                            alert('Delete Unsuccessful');
                        $.ajax({
                            url: '{{route("teacher")}}',
                            type: 'GET',
                            success: function(response){
                                $("#fetch").html(response);
                            }
                        });
                    },
                });
            }else if(match2){
                var tid = document.getElementById('td0-'+match2[1]).innerText;
                var name = document.getElementById('name-'+match2[1]).value;
                var password = document.getElementById('password-'+match2[1]).value;
                var formData = {
                    id : tid,
                    name : name,
                    password : password,
                };
                $.ajax({
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    url: '{{route("modify")}}',
                    type: 'PUT',
                    data: formData,
                    success: function(response){
                        if(response)
                            alert('Update Successful');
                        else
                            alert('Update Unsuccessful');
                        $.ajax({
                            url: '{{route("teacher")}}',
                            type: 'GET',
                            success: function(response){
                                $("#fetch").html(response);
                            }
                        });
                    },
                });
            }else if(id == 'add-'+{{$i}}){
                var tid = document.getElementById('id-{{$i}}').value;
                var name = document.getElementById('name-'+{{$i}}).value;
                var password = document.getElementById('password-'+{{$i}}).value;
                console.log(tid);
                console.log(tid == '');
                var formData = {
                    id : tid,
                    name : name,
                    password : password,
                };
                if(name == '' || tid == ''|| password == ''){
                    alert('Enter some values for id,name and password');
                    return;
                }
                $.ajax({
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    url: '{{route("create")}}',
                    type: 'POST',
                    data: formData,
                    success: function(response){
                        if(response)
                            alert('Add Successful');
                        else
                            alert('Add Unsuccessful');
                        $.ajax({
                            url: '{{route("teacher")}}',
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