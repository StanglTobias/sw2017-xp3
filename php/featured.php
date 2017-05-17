<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 05.04.2017
 * Time: 09:13
 */
header('Content-Type: application/json');
require "config.php";
header('Content-type: text/html; charset=utf-8');

$sql = "select * from Product order by likes desc limit 10";
$result = mysqli_query($con, $sql);

$rows = array();
while($r = mysqli_fetch_assoc($result)){
  $rows[] = array_map(null, $r);
}

echo json_encode($rows);
mysqli_free_result($result);
mysqli_close($con);
?>