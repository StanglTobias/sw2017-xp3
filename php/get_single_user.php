<?php
require_once __DIR__ . '/config.php';

   $con=mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }else{
	   echo "Connection successful!\n";
   }

   //$uid = $_GET['uid'];
   $result = mysqli_query($con,"SELECT * FROM User where id='1'");
   $row = mysqli_fetch_array($result);
   $data = $row;

   if($data){
	   echo "got data...\n";
      echo $data[1];
   }
   mysqli_close($con);
?>