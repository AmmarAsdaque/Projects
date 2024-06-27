<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Mark extends Model
{
    protected $table = 'marks';
    protected $primaryKey = ['cid', 'sid', 'tid'];
    public $incrementing = false;
    protected $keyType = 'string';
    public $timestamps = false;

    protected $fillable = [
        'cid', 'sid', 'tid', 'mark',
    ];

    // Define relationships or additional methods here
}
