<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 03.05.2017
 * Time: 15:21
 */

require "config.php";
header('Content-type: text/html; charset=utf-8');

$query = $_GET["q"];
$result = mysqli_query($con,"SELECT * FROM Product WHERE name LIKE '%$query%'");

$rows = array();
while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map(null, $r);
}

$type_result = mysqli_query($con,"SELECT id FROM Type WHERE type LIKE '%$query%' LIMIT 1");
$type_array = mysqli_fetch_array($type_result);

$type = $type_array[0];

$result = mysqli_query($con,"SELECT * FROM Product WHERE type_id='$type'");
while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map(null, $r);
}

echo json_encode($rows);
mysqli_free_result($result);

($con);

?>