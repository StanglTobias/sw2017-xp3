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
    "INSERT INTO User (longitude, latitude) VALUES ('$lon', '$lat') 
            where id='$id'");
?>


