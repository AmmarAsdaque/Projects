<h3 class="text-center">View/Edit Student</h3>
<table class="table table-dark table-striped" {{$i = 1}}>
    <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        @foreach($students as $student)
        <tr>
            <th id="td0-{{$i}}">{{$student->id}}</th>
            <td id="td1-{{$i}}">{{$student->fname}}</td>
            <td id="td2-{{$i}}">{{$student->lname}}</td>
            <td id="td3-{{$i}}">{{$student->password}}</td>
            <td id="td4-{{$i}}" class="d-flex">
                <a href="" id="btn-{{$i}}" class="btn btn-warning custom">EDIT</a>
                <a href="" id="delete-{{$i}}" class="btn btn-danger custom">DELETE</a>
                <a href="" id="save-{{$i++}}" class="btn btn-success custom" hidden>SAVE</a>
            </td>
        </tr>
        @endforeach
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
            var fname = document.getElementById('td1-'+match[1]).innerText;
            var lname = document.getElementById('td2-'+match[1]).innerText;
            var password = document.getElementById('td3-'+match[1]).innerText;
            document.getElementById('td1-'+match[1]).innerHTML = '<input type="text" id="fname-'+match[1]+'" value="'+fname+'">';
            document.getElementById('td2-'+match[1]).innerHTML = '<input type="text" id="lname-'+match[1]+'" value="'+lname+'">';
            document.getElementById('td3-'+match[1]).innerHTML = '<input type="text" id="password-'+match[1]+'" value="'+password+'">';
            document.getElementById(id).hidden = true;
            document.getElementById('delete-'+match[1]).hidden = true;
            document.getElementById('save-'+match[1]).hidden = false;
        }else if(match1){
            var sid = document.getElementById('td0-'+match1[1]).innerText;
            console.log('In delete ' + match1[1]);
            console.log(sid);
            $.ajax({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                url: '{{route("deleteStudent")}}',
                type: 'DELETE',
                data: {
                    id : sid,
                },
                success: function(response){
                    if(response)
                        alert('Delete Successful');
                    else
                        alert('Delete Unsuccessful');
                    $.ajax({
                        url: '{{route("showStudent")}}',
                        type: 'GET',
                        success: function(response){
                            $("#fetch").html(response);
                        }
                    });
                },
            });
        }else if(match2){
            var sid = document.getElementById('td0-'+match2[1]).innerText;
            var fname = document.getElementById('fname-'+match2[1]).value;
            var lname = document.getElementById('lname-'+match2[1]).value;
            var password = document.getElementById('password-'+match2[1]).value;
            var formData = {
                id : sid,
                fname : fname,
                lname : lname,
                password : password,
            };
            $.ajax({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                url: '{{route("edit")}}',
                type: 'PUT',
                data: formData,
                success: function(response){
                    if(response)
                        alert('Update Successful');
                    else
                        alert('Update Unsuccessful');
                    $.ajax({
                        url: '{{route("showStudent")}}',
                        type: 'GET',
                        success: function(response){
                            $("#fetch").html(response);
                        }
                    });
                },
            });
        }
    });
    // // Update the pagination links click event to fetch paginated data
    // $(document).on('click', '.pagination a', function(event) {
    //     event.preventDefault();
    //     var page = $(this).attr('href').split('page=')[1];
    //     fetchStudents(page);
    // });

    // // Function to fetch paginated students data
    // function fetchStudents(page) {
    //     $.ajax({
    //         url: '{{ route("showStudent") }}?page=' + page,
    //         type: 'GET',
    //         success: function(response) {
    //             $("#fetch").html(response);
    //         },
    //         error: function(xhr, status, error) {
    //             console.error('Error fetching students:', error);
    //         }
    //     });
    // }

</script>