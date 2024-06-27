<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class NewStudent extends Model
{
    protected $table = 'newstudents';
    protected $primaryKey = 'id';
    public $incrementing = false;
    protected $keyType = 'string';
    public $timestamps = false;

    protected $fillable = [
        'id', 'fname', 'lname', 'password',
    ];

    // Define relationships or additional methods here
}
