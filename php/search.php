<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 26.04.2017
 * Time: 08:27
 */

require "config.php";
header('Content-type: text/html; charset=utf-8');

$query = $_GET["q"];
$result = mysqli_query($con,"SELECT * FROM Product WHERE name LIKE '%$query%'");

$rows = array();
while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map('utf8_encode', $r);
}

$type_result = mysqli_query($con,"SELECT * FROM Type WHERE type LIKE '%$query%'");
$type_array = mysqli_fetch_array($type_result);
echo $type_array['id'];

/*$result = mysqli_query($con,"SELECT * FROM Product WHERE type_id='$type'");
while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map('utf8_encode', $r);
}*/

echo json_encode($rows);
mysqli_free_result($result);

($con);

?>