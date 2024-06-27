<h3 class="text-center">{{"Welcome ".$details->id."!!"}}</h3>
<p class="text-center">Here are your details</p>
<table class='table table-dark table-striped'>
    <thead>
        <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Image</th>
        </tr>
    </thead>
    <tr>
        <td>{{$details->fname}}</td>
        <td>{{$details->lname}}</td>
        <td><img src="" alt="{{$details->id.'.img'}}"></td>
    </tr>
</table>
<h5 class="text-center">If there is any decrepancy, feel free to contact k213923@nu.edu.pk</h5>
