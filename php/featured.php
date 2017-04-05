<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 05.04.2017
 * Time: 09:13
 */
//header('Content-Type: application/json');
require "config.php";

$sql = "SELECT * FROM Product";
$result = mysqli_query($con, $sql);

$rows = array();
while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map('utf8_encode', $r);
}

echo json_encode($rows);
mysqli_free_result($result);
mysqli_close($con);
?>