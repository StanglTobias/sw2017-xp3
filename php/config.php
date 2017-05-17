<?php
define('DB_USER', "sw-ma-xp3"); // db user
define('DB_PASSWORD', "sw2017"); // db password (mention your db password here)
define('DB_DATABASE', "sw-ma-xp3"); // database name
define('DB_HOST', "localhost"); // db server

$con=mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
mysqli_query ($con,'SET NAMES UTF8;');
//mysqli_query ($con,'SET COLLATION_CONNECTION=utf8_unicode_ci;');
?>