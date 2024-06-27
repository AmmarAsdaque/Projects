<h3 class="text-center m-2">Marks</h3>
<table class="table table-dark table-striped" {{$i = 1}}>
    <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Course</th>
          <th scope="col">Teacher</th>
          <th scope="col">Mark</th>
        </tr>
    </thead>
    @foreach ($marks as $mark)
        <tr>
            <th>
                {{$i++}}
            </th>
            <td>
                {{$mark->course}}
            </td>
            <td>
                {{$mark->teacher}}
            </td>
            <td>
                {{$mark->mark}}
            </td>
        </tr>
    @endforeach
</table>