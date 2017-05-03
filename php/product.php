<?php
require "config.php";
header('Content-type: text/html; charset=utf-8');

$id = $_GET['id'];
$result = mysqli_query($con,"SELECT * FROM Product where id='$id'");
$json = array();

while($row = mysqli_fetch_array($result))
{
    $json[]= array(
        'id' => $row['id'],
        'name' => $row['name'],
        'is_bio' => $row['is_bio'],
        'price' => $row['price'],
        'user_id' => $row['user_id'],
        'type_id' => $row['type_id'],
        'quantity' => $row['quantity'],
        'unit_type' => $row['unit_type'],
        'image_id' => $row['image_id']
    );
}

$jsonstring = json_encode($json);
echo $jsonstring;
mysqli_free_result($result);
mysqli_close($con);
?>