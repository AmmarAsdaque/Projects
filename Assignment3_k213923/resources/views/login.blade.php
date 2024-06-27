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
            @if(session('success'))
            <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                {{ session('success') }}
            </div>
        @endif
        <h3 class="text-center font-weight-bold mb-4 font-monospace">Login</h3>
        <form method="POST" action="{{ route('signin') }}">
            @csrf
            <div class="form-group">
                @if ($errors->has('loginError'))
                    <p class="alert alert-danger">{{ $errors->first('loginError') }}</p>
                @endif
                <label for="id" class="text-gray-700">Username</label>
                <input  id="id" type="text" name="id" class="form-control @error('id') is-invalid @enderror" placeholder="ID" autofocus>
                @error('id')
                    <p class="invalid-feedback">{{$message}}</p>
                @enderror
            </div>
            <div class="form-group">
                <label for="password" class="text-gray-700">Password</label>
                <input id="password" type="password" name="password" class="form-control @error('password') is-invalid @enderror"  placeholder="Password">
                @error('password')
                    <p class="invalid-feedback">{{$message}}</p>
                @enderror
            </div>
            <div class="form-group text-center mx-auto">
                <button class="btn btn-primary m-1" type="submit">Sign In</button><br>
                <a href="{{ route('signup') }}" class="m-1">Or sign up?</a>
            </div>
        </form>
    </div>
</body>
</html>
