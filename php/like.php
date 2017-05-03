<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 03.05.2017
 * Time: 14:47
 */

require "config.php";

$pid = $_POST['pid'];
$uuid = $_POST['uuid'];
mysqli_query($con,
    "INSERT INTO Likes (unique_user_id, product_id)VALUES ('$uuid', '$pid')");

mysqli_query($con,
    "UPDATE Product SET likes = likes + 1 WHERE id='$pid'");

mysqli_close($con);
?>