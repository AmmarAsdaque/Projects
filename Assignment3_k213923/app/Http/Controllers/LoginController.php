<?php

namespace App\Http\Controllers;


use App\Models\NewStudent;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use App\Models\User;

class LoginController extends Controller
{
    public function showLogin(){
        return view('login');
    }
    public function authenticateLogin(Request $request){
        $rules = [
            'id' => 'required|min:5',
            'password' => 'required|min:3',
        ];
        $validator = Validator::make($request->all(),$rules);
        if($validator->fails()){
            return redirect()->route('login')->withErrors($validator)->withInput();
        }
        $user = User::where('id','=',$request->id)->first();
        if ($user && $request->password == $user->password){
            // Authentication successful
            session(['role' => $user->role]);
            session(['id' => $request->id]);
            return redirect()->route('home')->with('Login Success');
        }

        // Authentication failed
        return redirect()->route('login')->withErrors(['loginError' => 'Invalid username or password']);

    }

    public function showSignUp(){
        return view('signup');

    }
    public function showHome(Request $request){
        return view('home');
    }

    public function store(Request $request){
        $rules = [
            'fname' => 'required|alpha',
            'lname' => 'required|alpha',
            'id' => 'required|size:8|regex:/^[0-2][0-9][A-Z]-[0-9]{4}$/',
            'password' => 'required|min:3',
            'repassword' => 'required|same:password'
        ];
        $validator = Validator::make($request->all(),$rules,
        ['id.regex' => 'The ID format is invalid. It should be in the format DDA-DDDD (D is digit, A is Alphabet, and all capital letters)',
        'repassword.same' => 'The password confirmation does not match the password.',]);
        if($validator->fails()){
            return redirect()->route('signup')->withErrors($validator)->withInput();
        }
        $user = User::where('id', $request->id)->exists();
        $newStudent = NewStudent::where('id', $request->id)->exists();

        if ($user || $newStudent){
            return redirect()->route('signup')->withErrors(['signupError' => 'ID already exists']);
        }
        $student = new NewStudent();
        $student->fname = $request->fname;
        $student->lname = $request->lname;
        $student->id = $request->id;
        $student->password = $request->password;
        $student->save();
        return redirect()->route('login')->with('success', 'SignUp successful. Your account is pending.');
    }
    public function signOut(){
        session()->forget('id');
        session()->forget('role');
        return redirect()->route('login'); 
    }
}
