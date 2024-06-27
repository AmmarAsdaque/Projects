<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body class='bg-primary'>
    <script>console.log('{{session('id')." ".session('role')}}'); </script>
    <nav class="navbar navbar-expand-lg navbar-light bg-info">
        <span class="navbar-brand text-center mx-auto fw-bold font-monospace fs-3">Student Management System</span>
    </nav>
    <div class="container">
        <div class="row">
            <div class="row">
                <div class="bg-secondary text-center d-flex align-items-center text-white fw-semibold">
                    @php
                        $role = session('role');    
                    @endphp
                    @isset($role)
                        @if($role==0)
                            <span class="mx-auto">You are Admin</span>       
                        @elseif($role==1)
                            <span class="mx-auto">You are Teacher</span>
                        @else
                            <span class="mx-auto">You are Student</span>
                        @endif
                    @endisset    
                    <a href="{{ route('logout') }}" class="btn btn-danger mr-0 ml-auto">Logout</a>
                </div>
            </div>
            <div class="row" style="height: 600px">
                <div class="col-md-3 bg-warning">
                    @isset($role)
                        @if($role==0)
                            <div class="text-center">
                                <a class='btn btn-success btn-block my-1'style="width : 100%; height : 100px; padding: 40px 0;" id='btn0'>Request Approval</a><br>
                                <a class='btn btn-success btn-block my-1'style="width : 100%; height : 100px; padding: 40px 0;" id='btn1'>Update/Delete Student</a><br>
                                <a class='btn btn-success btn-block my-1'style="width : 100%; height : 100px; padding: 40px 0;" id='btn2'>Offer/Update/Delete Courses</a><br>
                                <a class='btn btn-success btn-block my-1'style="width : 100%; height : 100px; padding: 40px 0;" id='btn3'>Add/Update/Delete Teacher</a><br>                                
                                <a class='btn btn-success btn-block my-1'style="width : 100%; height : 100px; padding: 40px 0;" id='btn4'>Assign Course to Faculty</a><br>
                            </div>        
                        @elseif($role==1)
                            <div class='text-center'>
                                <a class='btn btn-success btn-block my-3'style="width : 100%; height : 100px; padding: 40px 0;" id='btn5'>Add Attendance</a><br>
                                <a class='btn btn-success btn-block my-3'style="width : 100%; height : 100px; padding: 40px 0;" id='btn6'>Add Marks</a><br>
                            </div>
                        @else
                            <div class='text-center'>
                                <a class='btn btn-success btn-block my-3'style="width : 100%; height : 100px; padding: 40px 0;" id='btn7'>View Details</a><br>
                                <a class='btn btn-success btn-block my-3'style="width : 100%; height : 100px; padding: 40px 0;" id='btn8'>View Marks</a><br>
                                <a class='btn btn-success btn-block my-3'style="width : 100%; height : 100px; padding: 40px 0;" id='btn9'>Register Course</a><br>
                                <a class='btn btn-success btn-block my-3'style="width : 100%; height : 100px; padding: 40px 0;" id='btn10'>View Attendance</a><br>
                            </div>
                        @endif
                    @endisset       
                </div>
                {{--Div to be updated--}}
                <div id="fetch" class="bg-white mx-auto col-md-9"></div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#btn0").click(function(event){
                event.preventDefault();
                for (let index = 1; index <= 4; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn0").hidden = true;
                $.ajax({
                    url: '{{route("approval")}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            }); 
            $("#btn1").click(function(event){
                event.preventDefault();
                for (let index = 0; index <= 4; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn1").hidden = true;
                $.ajax({
                    url: '{{route("showStudent")}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn2").click(function(event){
                event.preventDefault();
                for (let index = 0; index <= 4; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn2").hidden = true;
                $.ajax({
                    url: '{{route("course")}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn3").click(function(event){
                event.preventDefault();
                for (let index = 0; index <= 4; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn3").hidden = true;
                $.ajax({
                    url: '{{route("teacher")}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn4").click(function(event){
                event.preventDefault();
                for (let index = 0; index <= 3; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn4").hidden = true;
                $.ajax({
                    url: '{{route("assignment")}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn5").click(function(event){
                event.preventDefault();
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn6").hidden = false;    
                document.getElementById("btn5").hidden = true;
                $.ajax({
                    url: "{{route('addAttendance')}}",
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn6").click(function(event){
                event.preventDefault();
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn5").hidden = false;    
                document.getElementById("btn6").hidden = true;
                $.ajax({
                    url: "{{route('addMark')}}",
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn7").click(function(event){
                event.preventDefault();
                for (let index = 8; index <= 10; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn7").hidden = true;
                $.ajax({
                    url: '{{route('details')}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn8").click(function(event){
                event.preventDefault();
                for (let index = 7; index <= 10; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn8").hidden = true;
                $.ajax({
                    url: '{{route('marks')}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn9").click(function(event){
                event.preventDefault();
                for (let index = 7; index <= 10; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn9").hidden = true;
                $.ajax({
                    url: '{{route('registration')}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    }
                });
            });
            $("#btn10").click(function(event){
                event.preventDefault();
                for (let index = 7; index <= 9; index++) {
                    document.getElementById("btn"+index).hidden = false;    
                }
                document.getElementById("fetch").innerHTML = "";
                document.getElementById("btn10").hidden = true;
                console.log('Ammar');
                $.ajax({
                    url: '{{route('attendance')}}',
                    type: 'GET',
                    success: function(response){
                        $("#fetch").html(response);
                    },
                });
            });
        });
    </script>
</body>
</html>