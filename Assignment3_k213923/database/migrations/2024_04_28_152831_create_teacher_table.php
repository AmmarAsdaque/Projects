<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateTeacherTable extends Migration
{
    public function up()
    {
        Schema::create('teacher', function (Blueprint $table) {
            $table->string('id', 8)->primary();
            $table->string('name',55);
            $table->foreign('id')->references('id')->on('user')->onDelete('cascade')->onUpdate('cascade');
        });
    }

    public function down()
    {
        Schema::dropIfExists('teacher');
    }
}

