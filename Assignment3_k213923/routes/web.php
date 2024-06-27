<?php

use App\Http\Controllers\AdminController;
use App\Http\Controllers\TeacherController;
use App\Http\Controllers\StudentController;
use App\Http\Controllers\LoginController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});
//login methods
Route::get('/login', [LoginController::class,'showLogin'])->name('login');
Route::post('/signin', [LoginController::class,'authenticateLogin'])->name('signin');
//signup methods
Route::get('/signup', [LoginController::class,'showSignUp'])->name('signup');
Route::post('/signup-success', [LoginController::class,'store'])->name('join');
//successful login
Route::get('/home', [LoginController::class,'showHome'])->name('home');
//logout
Route::get('/logout', [LoginController::class,'signOut'])->name('logout');

//approve methods
Route::get('/approval', [AdminController::class,'index'])->name('approval');   
Route::post('/approve', [AdminController::class,'approve'])->name('approve');
Route::delete('/reject', [AdminController::class,'reject'])->name('reject');
//add.edit.delete courses methods
Route::get('/course', [AdminController::class,'show'])->name('course');   
Route::post('/offer', [AdminController::class,'offer'])->name('offer');   
Route::put('/update', [AdminController::class,'update'])->name('update');
Route::delete('/delete-course', [AdminController::class,'deleteCourse'])->name('deleteCourse');
//view.edit.delete student methods
Route::get('/student', [AdminController::class,'exhibit'])->name('showStudent');
Route::put('/edit-student', [AdminController::class,'edit'])->name('edit');   
Route::delete('/delete-student', [AdminController::class,'deleteStudent'])->name('deleteStudent');
//assign course methods
Route::get('/show-course', [AdminController::class,'showCourse'])->name('assignment');
Route::get('/show-teacher', [AdminController::class,'showTeacher'])->name('showTeacher');
Route::post('/assign', [AdminController::class,'assignCourse'])->name('assign');   
//add.view.modify.delete teacher methods
Route::get('/teacher', [AdminController::class,'view'])->name('teacher');   
Route::put('/teacher-modify', [AdminController::class,'modify'])->name('modify');
Route::delete('/delete-teacher', [AdminController::class,'deleteTeacher'])->name('deleteTeacher');   
Route::post('/add-teacher', [AdminController::class,'create'])->name('create');

//add attendance methods
Route::get('/add-attendance', [TeacherController::class,'show'])->name('addAttendance');
Route::get('/get-student', [TeacherController::class,'view'])->name('student');
Route::post('/store-attendance', [TeacherController::class,'store'])->name('store');
//add mark methods
Route::get('/add-mark', [TeacherController::class,'index'])->name('addMark');
Route::get('/show-student', [TeacherController::class,'return'])->name('studentTable');
Route::post('/store-mark', [TeacherController::class,'create'])->name('storeMark');


//view student method
Route::get('/details', [StudentController::class,'viewDetails'])->name('details');
//view mark method
Route::get('/marks', [StudentController::class,'viewMarks'])->name('marks');
//course registration methods
Route::get('/registration',[StudentController::class,'register'])->name('registration');
Route::get('/get-teacher', [StudentController::class,'show'])->name('show');
Route::post('/store', [StudentController::class,'store'])->name('register');
//view attendance methods
Route::get('/attendance', [StudentController::class,'viewAttendance'])->name('attendance');

