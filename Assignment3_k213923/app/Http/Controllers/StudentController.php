<?php

namespace App\Http\Controllers;

use App\Models\Course;
use App\Models\Attendance;
use App\Models\Enrolled;
use App\Models\Student;
use App\Models\Teaches;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class StudentController extends Controller
{
    function viewDetails(Request $request){
        $details = Student::where('id',session('id'));
        $details = $details->first();
        return response()->view('studentDetails',['details'=> $details]);
    }
    function viewAttendance(Request $request){
        try{
            $attendance = DB::table('courses')
            ->join('attendance','attendance.cid', '=', 'courses.id')->where('sid',session('id'))->get(['name','date','present']);
            Log::info('Attendance: ' . $attendance);
            return response()->view('attendance',['attendance'=> $attendance]);
        } catch (\Exception $e) {
            Log::error($e->getMessage());
            return response();
        }
    }
    function viewMarks(Request $request){
        try{
            $mark = DB::table('courses')
            ->join('marks','marks.cid', '=', 'courses.id')->join('teacher','teacher.id', '=', 'marks.tid')->where('sid',session('id'))
            ->select('courses.name as course','teacher.name as teacher','marks.mark as mark')->get(['course','teacher','mark']);
            return response()->view('marks',['marks'=> $mark]);
        } catch (\Exception $e) {
            Log::error($e->getMessage());
            return response();
        }
    }
    function show(Request $request){
        $course = $request->cid;
        $teachers = DB::table('teaches')
        ->join('teacher','teaches.tid', '=', 'teacher.id')->where('cid',$course)->get(['teacher.id','teacher.name']);
        return response()->json(['teachers'=> $teachers]);
    }
    function register(Request $request){
        $courses = DB::table('courses')
        ->join('teaches','teaches.cid', '=', 'courses.id')->groupBy('courses.id','courses.name')->get(['courses.id','courses.name']);
        return response()->view('register',['courses'=> $courses]);
    }
    function store(Request $request){
        $enrollment = new Enrolled();
        $enrollment->sid = session('id');
        $enrollment->cid = $request->cid;
        $enrollment->tid = $request->tid;
        if($enrollment->save())
            return response()->json(true);
        else
            return response()->json(false);
    }
    // function courseSelect(Request $request){
    //     try{
    //         $courses =  DB::table('courses')
    //         ->join('attendance','attendance.cid', '=', 'courses.id')
    //         ->where('attendance.sid',session('id'))->get(['courses.id','courses.name']);
    //         Log::info('Course selected ' . $courses);
    //         return response()->view('attendance',['courses'=> $courses]);
    //     } catch (\Exception $e) {
    //         Log::error($e->getMessage());
    //         return response()->json(['error' => 'Internal Server Error'], 500);
    //     }
    // }
}
