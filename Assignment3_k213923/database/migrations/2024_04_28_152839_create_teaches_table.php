<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateTeachesTable extends Migration
{
    public function up()
    {
        Schema::create('teaches', function (Blueprint $table) {
            $table->string('cid', 8);
            $table->string('tid', 8);
            
            $table->primary(['cid', 'tid']);
            $table->foreign('cid')->references('id')->on('courses')->onDelete('cascade')->onUpdate('cascade');
            $table->foreign('tid')->references('id')->on('teacher')->onDelete('cascade')->onUpdate('cascade');
        });
    }

    public function down()
    {
        Schema::dropIfExists('teaches');
    }
}
