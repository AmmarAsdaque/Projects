<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateUserTable extends Migration
{
    public function up()
    {
        Schema::create('user', function (Blueprint $table) {
            $table->string('id', 8)->primary();
            $table->string('password',20);
            $table->integer('role');
        });
    }

    public function down()
    {
        Schema::dropIfExists('user');
    }
}
