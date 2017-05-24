<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 17.05.2017
 * Time: 11:44
 */

  require "config.php";

  $id = $_GET["id"];
  $lon = $_GET["lon"];
  $lat = $_GET["lat"];

  mysqli_query($con,
    "UPDATE User SET longitude = '$lon' WHERE id='$id'");

  mysqli_query($con,
    "UPDATE User SET latitude = '$lat' WHERE id='$id'");
?>


