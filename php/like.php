<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 03.05.2017
 * Time: 14:47
 */

  require "config.php";

  //$rawdata = file_get_contents('php://input');
  //echo $rawdata;

  //App must send multipart/formdata encoded data
  $pid = $_GET['pid'];
  $uuid = $_GET['uuid'];

  $result = mysqli_query($con, "SELECT * FROM Likes WHERE unique_user_id = '$uuid' and product_id='$pid'");

  if($row = mysqli_fetch_array($result)) {
      echo 0;
  }
  else{
      echo 1;
      mysqli_query($con,
          "INSERT INTO Likes (unique_user_id, product_id) VALUES ('$uuid', '$pid')");

      mysqli_query($con,
          "UPDATE Product SET likes = (select count(*) from Likes where product_id = '$pid') WHERE id='$pid'");
  }

  mysqli_close($con);
?>