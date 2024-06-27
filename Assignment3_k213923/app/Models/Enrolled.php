<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Enrolled extends Model
{
    protected $table = 'enrolled';
    protected $primaryKey = ['cid', 'sid', 'tid'];
    public $incrementing = false;
    protected $keyType = 'string';
    public $timestamps = false;

    protected $fillable = [
        'cid', 'sid', 'tid',
    ];

    // Define relationships or additional methods here
}
