<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 05.04.2017
 * Time: 11:47
 */
header("Content-type: image/jpeg");
require "config.php";
$id = $_GET['id'];
$sql = "SELECT * FROM Image WHERE product_id=$id";
$result = mysqli_query($con, $sql);
$row = mysqli_fetch_assoc($result);
mysqli_close($con);
echo $row['image'];
?>