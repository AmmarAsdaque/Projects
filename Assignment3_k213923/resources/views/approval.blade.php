<h3 class="text-center">Approve New Student</h3>
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
                <a href="" id="accept-{{$i}}" class="btn btn-warning custom">ACCEPT</a>
                <a href="" id="reject-{{$i++}}" class="btn btn-danger custom">REJECT</a>
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
        const regex1 = /^accept-(\d+)$/;
        const regex2 = /^reject-(\d+)$/;
        const match1 = id.match(regex1);
        const match2 = id.match(regex2);
        if(match1){
            var sid = document.getElementById('td0-'+match1[1]).innerText;
            console.log('In delete ' + match1[1]);
            console.log(sid);
            $.ajax({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                url: '{{route("approve")}}',
                type: 'POST',
                data: {
                    id : sid,
                },
                success: function(response){
                    if(response)
                        alert('Approved Successfully');
                    else
                        alert('Approve Unsuccessful');
                    $.ajax({
                        url: '{{route("approval")}}',
                        type: 'GET',
                        success: function(response){
                            $("#fetch").html(response);
                        }
                    });
                },
            });
        }else if(match2){
            var sid = document.getElementById('td0-'+match2[1]).innerText;
            var formData = {
                id : sid,
            };
            $.ajax({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                url: '{{route("reject")}}',
                type: 'DELETE',
                data: formData,
                success: function(response){
                    if(response)
                        alert('Reject Successful');
                    else
                        alert('Reject Unsuccessful');
                    $.ajax({
                        url: '{{route("approval")}}',
                        type: 'GET',
                        success: function(response){
                            $("#fetch").html(response);
                        }
                    });
                },
            });
        }
    });
    // $(document).on('click', '.pagination a', function(event) {
    //     event.preventDefault();
    //     var page = $(this).attr('href').split('page=')[1];
    //     fetchStudents(page);
    // });
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