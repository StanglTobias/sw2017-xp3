<?php
require "config.php";
header('Content-type: text/html; charset=utf-8');

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
        'longitude' => $row['longitude'],
        'latitude' => $row['latitude'],
        'address' => $row['address'],
        'commercial' => $row['commercial'],
        'yard_sale' => $row['yard_sale'],
        'self_harvest' => $row['self_harvest'],
        'delivery' => $row['delivery'],
        'postal_code' => $row['postal_code'],
        'email' => $row['email'],
        'phone_number' => $row['phone_number'],
        'is_bio' => $row['is_bio'],
    );
}

$jsonstring = json_encode($json);
echo $jsonstring;
mysqli_free_result($result);
mysqli_close($con);
?>