<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 03.05.2017
 * Time: 13:16
 */

require "config.php";

$pid = $_GET['pid'];
$uuid = $_GET['uuid'];
$result = mysqli_query($con,
"SELECT * FROM Likes where product_id='$pid' and unique_user_id='$uuid'");

if($row = mysqli_fetch_array($result)) {
    echo 1;
}
else{
    echo 0;
}

mysqli_free_result($result);
mysqli_close($con);
?>