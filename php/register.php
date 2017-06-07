<?php
require "config.php";
header('Content-type: text/html; charset=utf-8');

$company_name   = $_POST["company_name"];
$first_name     = $_POST["first_name"];
$last_name      = $_POST["last_name"];
$email          = $_POST["email"];
$phone_number   = $_POST["phone_number"];
$city           = $_POST["city"];
$postal_code    = $_POST["postal_code"];
$address        = $_POST["address"];
$password       = $_POST["password"];
//TODO: apply these fields in UI and in SQL query below instead of hard-coded values
$is_bio         = $_POST["is_bio"];
$longitude      = $_POST["longitude"];
$latitude       = $_POST["latitude"];
$commercial     = $_POST["commercial"];
$delivery       = $_POST["delivery"];
$yard_sale      = $_POST["yard_sale"];
$self_harvest   = $_POST["self_harvest"];
if(empty($last_name) or empty($email))
{
  echo '2';
}
else
{



	$result = mysqli_query($con, "INSERT INTO User 
	(company_name, first_name, last_name, email, phone_number, city, postal_code, address, password, is_bio, longitude, latitude, commercial, delivery, yard_sale, self_harvest) 
	VALUES  ( '$company_name',   '$first_name',   '$last_name',  '$email',  '$phone_number',  '$city',  '$postal_code',  '$address',  '$password',   '0',   '15.439790',   '47.073383',  '0',  '0',  '0',   '0');"); 

	echo $result;
}

?>