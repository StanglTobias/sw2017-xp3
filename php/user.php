<?php
require "config.php";

$id = $_GET["id"];
$result = mysqli_query($con,"SELECT * FROM User where id='$id'");
$json = array();

while($row = mysqli_fetch_array($result))
{
    $json[]= array(
        'id' => $row['id'],
        'first_name' => $row['first_name'],
        'last_name' => $row['last_name'],
        'company_name' => $row['company_name'],
        'address' => $row['address'],
        'city' => $row['city'],
        'address' => $row['address'],
        'postal_code' => $row['postal_code'],
        'email' => $row['email'],
        'phone_number' => $row['phone_number'],
        'password' => $row['password'],
        'is_bio' => $row['is_bio'],
        'likes' => $row['likes']
    );
}

$jsonstring = json_encode($json);
echo $jsonstring;
mysqli_free_result($result);
mysqli_close($con);
?>