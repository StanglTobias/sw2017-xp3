<?php
include_once 'config.php';

$conn = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

if(conn){
	echo "<h3> Connection success!</h3>";
}
?>