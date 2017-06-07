<?php
require "config.php";
header('Content-type: text/html; charset=utf-8');

$email = $_GET["email"];

$result = mysqli_query($con, " SELECT * FROM User WHERE email like '$email'"); 

  if($row = mysqli_fetch_array($result)) {
      echo 1; //User is already in database
  }
  else{
      echo 0;
  }
?>