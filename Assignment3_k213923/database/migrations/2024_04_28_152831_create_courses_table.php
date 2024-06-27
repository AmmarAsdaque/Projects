<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateCoursesTable extends Migration
{
    public function up()
    {
        Schema::create('courses', function (Blueprint $table) {
            $table->string('id', 8)->primary();
            $table->string('name',55);
        });
    }

    public function down()
    {
        Schema::dropIfExists('courses');
    }
}
