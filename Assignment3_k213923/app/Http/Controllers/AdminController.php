<?php

namespace App\Http\Controllers;

use App\Models\Course;
use App\Models\NewStudent;
use App\Models\Student;
use App\Models\Teacher;
use App\Models\Teaches;
use App\Models\User;
use Illuminate\Contracts\Validation\Rule;
use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class AdminController extends Controller
{
    public function index()//Show students needed to approve
    {
        $students = NewStudent::all();
        return view('approval', compact('students'));
    }
    public function exhibit()//Show students that are currently signed up for the app
    {
        $students = DB::table('user')
        ->join('students','students.id', '=', 'user.id')
        ->get(['user.id','fname','lname','password']);
        return view('student', compact('students'));
    }
    public function view()//View the faculty members
    {
        $teachers = DB::table('user')
        ->join('teacher', 'teacher.id', '=', 'user.id')
        ->get(['user.id','name','password']);
        return view('teacher', compact('teachers'));
    }
    public function create(Request $request)//Creates and stores a new teacher
    {
        $user = new User();
        $user->id = $request->id;
        $user->password = $request->password;
        $user->role = 1;
        $user->save();
        $teacher = new Teacher();
        $teacher->id = $request->id;
        $teacher->name = $request->name;
        $teacher->save();
        return response()->json([true]);
    }
    public function approve(Request $request)//Approve new student
    {
        $details = NewStudent::where('id', $request->id)->first();
        $user = new User();
        $user->id = $details->id;
        $user->password = $details->password;
        $user->role = 2;
        $user->save();
        $student = new Student();
        $student->id = $user->id;
        $student->fname = $details->fname;
        $student->lname = $details->lname;
        $student->save();
        NewStudent::where('id', $request->id)->delete();
        return response()->json([true]);
    }
    public function reject(Request $request){//Reject new student
        NewStudent::where('id', $request->id)->delete();
        return response()->json([true]);
    }
    public function show(){//Show all courses
        $courses = Course::all();
        return view('course',compact('courses'));
    }
    public function offer(Request $request){//Offer new course
        $course = new Course();
        $course->id = $request->id;
        $course->name = $request->name;
        $course->save();
        return response()->json([true]);
    }
    public function update(Request $request){//Update course
        if($request->oid != $request->nid){
            Course::where('id', $request->oid)->update(['id' => $request->nid,'name'=>$request->n_name]);
        }else if($request->o_name != $request->n_name){
            Course::where('id', $request->oid)->update(['name'=>$request->n_name]);
        }
        return response()->json([true]);
    }
    public function edit(Request $request){//Update student
        Student::where('id', $request->id)->update(['fname'=>$request->fname,'lname'=>$request->lname]);
        $password = User::where('id', $request->id)->get(['password'])->first();
        if($request->password != $password->password){
            User::where('id', $request->id)->update(['password'=>$request->password]);
        }
        return response()->json([true]);
    }
    public function modify(Request $request){//Update teacher
        $password = User::where('id', $request->id)->get(['password'])->first();
        Teacher::where('id', $request->id)->update(['name'=>$request->name]);
        if($request->password != $password->password){
            User::where('id', $request->id)->update(['password'=>$request->password]);
        }
        return response()->json([true]);
    }
    public function deleteCourse(Request $request){
        Course::where('id', $request->id)->delete();
        return response()->json([true]);
    }

    public function deleteStudent(Request $request){
        Log::info('Deleting student '.$request->id);
        User::where('id', $request->id)->delete();
        return response()->json([true]);
    }
    public function deleteTeacher(Request $request){
        User::where('id', $request->id)->delete();
        return response()->json([true]);
    }
    public function assignCourse(Request $request){
        $teaches = new Teaches();
        $teaches->tid = $request->tid;
        $teaches->cid = $request->cid;
        $teaches->save();
        return response()->json([true]);
    }
    public function showCourse(){
        $courses = Course::all();
        return view('assign',compact('courses'));
    }
    public function showTeacher(Request $request){
        $teaches = DB::table('teacher')
        ->join('teaches', 'teacher.id', '=', 'teaches.tid')
        ->join('courses', 'courses.id', '=', 'teaches.cid')
        ->where('courses.id', $request->cid)
        ->pluck('teacher.id')
        ->toArray();
        $teachers = Teacher::whereNotIn('id',$teaches)->get();
        return response()->json(['teachers'=>$teachers]);
    }
}