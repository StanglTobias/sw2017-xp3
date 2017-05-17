<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 17.05.2017
 * Time: 10:00
 */

  require "config.php";
  header('Content-type: text/html; charset=utf-8');

  $lonStart = $_GET["lon"]; //47.050068 - graz example
  $latStart = $_GET["lat"]; //15.454002
  $earth_radius = 6371;

  $maxLat = $latStart + rad2deg(10/$earth_radius);
  $minLat = $latStart - rad2deg(10/$earth_radius);
  $maxLon = $lonStart + rad2deg(asin(10/$earth_radius) / cos(deg2rad($latStart)));
  $minLon = $lonStart - rad2deg(asin(10/$earth_radius) / cos(deg2rad($latStart)));

  $sql = "Select *
            From User
            Where latitude Between '$minLat' And '$maxLat'
              And longitude Between '$minLon' And '$maxLon'";

  $result = mysqli_query($con, $sql);

  $rows = array();
  while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map(null, $r);
  }

  echo json_encode($rows);
  mysqli_free_result($result);
  mysqli_close($con);

?>