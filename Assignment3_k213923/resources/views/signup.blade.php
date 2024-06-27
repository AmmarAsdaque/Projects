<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SMS Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-dark d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="bg-white shadow p-4 rounded w-25">
        <h3 class="text-center font-weight-bold mb-4">Sign Up</h3>
        <form method="POST" action="{{ route('join') }}">
            @csrf
            <div class="form-group">
                @if ($errors->has('signupError'))
                    <p class="alert alert-danger">{{ $errors->first('signupError') }}</p>
                @endif
                <label for="id" class="text-gray-700">Username/ID:</label>
                <input  id="id" type="text" name="id" class="form-control @error('id') is-invalid @enderror" placeholder="ID" autofocus>
                @error('id')
                    <p class="invalid-feedback">{{$message}}</p>
                @enderror
            </div>
            <div class="form-group">
                <label for="fname" class="text-gray-700">First Name:</label>
                <input id="fname" type="text" name="fname" class="form-control @error('fname') is-invalid @enderror"  placeholder="First Name">
                @error('fname')
                    <p class="invalid-feedback">{{$message}}</p>
                @enderror
            </div>
            <div class="form-group">
                <label for="lname" class="text-gray-700">Last Name:</label>
                <input id="lname" type="text" name="lname" class="form-control @error('lname') is-invalid @enderror"  placeholder="Last Name">
                @error('lname')
                    <p class="invalid-feedback">{{$message}}</p>
                @enderror
            </div>
            <div class="form-group">
                <label for="password" class="text-gray-700">Password:</label>
                <input id="password" type="password" name="password" class="form-control @error('password') is-invalid @enderror"  placeholder="Password">
                @error('password')
                    <p class="invalid-feedback">{{$message}}</p>
                @enderror
            </div>
            <div class="form-group">
                <input id="repassword" type="password" name="repassword" class="form-control @error('repassword') is-invalid @enderror"  placeholder="Re Enter Password">
                @error('repassword')
                    <p class="invalid-feedback">{{$message}}</p>
                @enderror
            </div>
            <div class="form-group text-center">
                <button class="btn btn-primary m-1" type="submit">Sign Up</button><br>
                <a href="{{ route('login') }}" class="m-1">Already have an account? Login</a>
            </div>
        </form>
    </div>
</body>
</html>
