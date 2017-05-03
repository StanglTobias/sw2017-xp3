<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 05.04.2017
 * Time: 13:22
 */
require "config.php";
header('Content-type: text/html; charset=utf-8');

$id = $_GET["id"];
$result = mysqli_query($con,"SELECT * FROM Type where id='$id'");
$json = array();

while($row = mysqli_fetch_array($result))
{
    $json[]= array(
        'id' => $row['id'],
        'type' => $row['type']
    );
}

echo json_encode($json);
mysqli_free_result($result);
mysqli_close($con);

?>