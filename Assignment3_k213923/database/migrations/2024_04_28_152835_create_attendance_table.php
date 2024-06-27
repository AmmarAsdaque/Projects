<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAttendanceTable extends Migration
{
    public function up()
    {
        Schema::create('attendance', function (Blueprint $table) {
            $table->string('cid',8);
            $table->string('sid', 8);
            $table->date('date');
            $table->boolean('present');
            
            $table->foreign('cid')->references('id')->on('courses')->onDelete('cascade')->onUpdate('cascade');
            $table->foreign('sid')->references('id')->on('students')->onDelete('cascade')->onUpdate('cascade');
            $table->primary(['cid', 'sid', 'date']);
        });
    }

    public function down()
    {
        Schema::dropIfExists('attendance');
    }
}

