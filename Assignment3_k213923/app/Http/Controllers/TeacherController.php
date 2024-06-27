<?php

namespace App\Http\Controllers;

use App\Models\Attendance;
use App\Models\Enrolled;
use App\Models\Mark;
use App\Models\Teacher;
use App\Models\Teaches;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class TeacherController extends Controller
{
    //
    public function index()
    {
        $courses = Teaches::join('courses','courses.id','=','teaches.cid')
        ->where('teaches.tid',session('id'))
        ->get(['id','name']);
        return view('mark',compact('courses'));
    }
    public function return(Request $request){
        $teacherId = session('id');
        $courseId = $request->cid;

        $students = Enrolled::join('students', function ($join) {
        $join->on('enrolled.sid', '=', 'students.id');
        })
        ->where('enrolled.cid', '=', $request->cid)
        ->where('enrolled.tid', '=', $teacherId)
        ->whereNotIn('enrolled.sid', function ($query) use ($courseId,$teacherId) {
        $query->select('sid')
                ->from('marks')
                ->where('marks.cid', '=', $courseId)->where('marks.tid','=',$teacherId);
        })
        ->select(DB::raw('CONCAT(fname, " ", lname) AS name'), 'students.id')
        ->get();
        return response()->view('studentTable',compact('students'));
        // return response()->json(['students' => $students]);

    }
    public function create(Request $request)
    {
        $marks = new Mark();
        $marks->cid = $request->cid;
        $marks->tid = session('id');
        $marks->sid = $request->sid;
        $marks->mark = $request->mark;
        $marks->save();
        return response()->json([true]);
    }
    public function store(Request $request)
    {
        for($i = 0; $i < count($request->students); $i++){
            $attendance = new Attendance();
            $attendance->cid = $request->cid;
            $attendance->date = $request->date;
            $attendance->sid = $request->students[$i][0];
            $attendance->present = $request->students[$i][1];
            $attendance->save();
        }
        return response()->json([true]);
    }
    public function show(Request $request)
    {
        $teacher = Teacher::where('id',session('id'))->first();
        $courses = DB::table('teaches')
        ->join('courses', 'courses.id', '=', 'teaches.cid')
        ->where('teaches.tid',$teacher->id)->get(['id','name']);
        return view('addAttendance',['courses'=> $courses,'teacher'=> $teacher]);
    }
    public function view(Request $request)
    {
        $data = DB::table('enrolled')
        ->join('students','students.id','=','enrolled.sid')
        ->where('enrolled.tid',session('id'))
        ->where('enrolled.cid',$request->cid)
        ->select('id',DB::raw('CONCAT(fname, " ", lname) AS name'))->get(['id','name']);
        return response()->json(['data' => $data]);
    }
    
}
