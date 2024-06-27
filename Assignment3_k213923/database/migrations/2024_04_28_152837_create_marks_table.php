<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateMarksTable extends Migration
{
    public function up()
    {
        Schema::create('marks', function (Blueprint $table) {
            $table->string('cid', 8);
            $table->string('sid', 8);
            $table->string('tid', 8);
            $table->integer('mark');
            $table->primary(['cid', 'sid', 'tid']);
            $table->foreign('cid')->references('id')->on('courses')->onDelete('cascade')->onUpdate('cascade');
            $table->foreign('sid')->references('id')->on('students')->onDelete('cascade')->onUpdate('cascade');
            $table->foreign('tid')->references('id')->on('teacher')->onDelete('cascade')->onUpdate('cascade');
            
        });
    }

    public function down()
    {
        Schema::dropIfExists('marks');
    }
}
