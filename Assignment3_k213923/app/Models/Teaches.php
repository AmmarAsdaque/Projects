<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Teaches extends Model
{
    protected $table = 'teaches';
    protected $primaryKey = ['cid', 'tid'];
    public $incrementing = false;
    protected $keyType = 'string';
    public $timestamps = false;

    protected $fillable = [
        'cid', 'tid',
    ];

    // Define relationships or additional methods here
}
