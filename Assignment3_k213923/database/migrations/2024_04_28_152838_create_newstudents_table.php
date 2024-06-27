<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateNewstudentsTable extends Migration
{
    public function up()
    {
        Schema::create('newStudents', function (Blueprint $table) {
            $table->string('id', 8)->primary();
            $table->string('fname',20);
            $table->string('lname',20);
        }); 
    }

    public function down()
    {
        Schema::dropIfExists('newStudents');
    }
}
