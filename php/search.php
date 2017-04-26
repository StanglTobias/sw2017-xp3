<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 26.04.2017
 * Time: 08:27
 */

require "config.php";

$query = $_GET["q"];
$result = mysqli_query($con,"SELECT * FROM Product where name like '%$query%'");

$rows = array();
while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map('utf8_encode', $r);
}

echo json_encode($rows);
mysqli_free_result($result);
mysqli_close($con);

?>