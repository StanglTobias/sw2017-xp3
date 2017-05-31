<?php
/**
 * Created by PhpStorm.
 * User: ztsc
 * Date: 5/24/17
 * Time: 3:38 PM
 */

require "config.php";
header('Content-type: text/html; charset=utf-8');

$query = $_GET["query"];
$cat0 = $_GET["cat0"];
$cat1 = $_GET["cat1"];
$cat2 = $_GET["cat2"];
$cat3 = $_GET["cat3"];
$cat4 = $_GET["cat4"];
$cat5 = $_GET["cat5"];
$isbio = $_GET["isbio"];
$distance = $_GET["dist"];
$lonStart = $_GET["lon"]; //47.050068 - graz example
$latStart = $_GET["lat"]; //15.454002
$earth_radius = 6371;

if($isbio != 0){
    $bio_string = "AND p.is_bio='$isbio'";
}
else{
    $bio_string = "";
}


$maxLat = $latStart + rad2deg($distance/$earth_radius);
$minLat = $latStart - rad2deg($distance/$earth_radius);
$maxLon = $lonStart + rad2deg(asin($distance/$earth_radius) / cos(deg2rad($latStart)));
$minLon = $lonStart - rad2deg(asin($distance/$earth_radius) / cos(deg2rad($latStart)));

$type_result = mysqli_query($con,"SELECT id FROM Type WHERE type LIKE '%$query%' LIMIT 1");
$type_array = mysqli_fetch_array($type_result);
$type = $type_array[0];

if($cat0 == 0 && $cat1 == 0 && $cat2 == 0 && $cat3 == 0 && $cat4 == 0 && $cat5 == 0){
    $cat_string = "";
}
else{
    $cat_string = " AND (type_id='$type' OR type_id='$cat0' OR type_id='$cat1' OR type_id='$cat2'
                        OR type_id='$cat3' OR type_id='$cat4' OR type_id='$cat5')";
}

if($lon == 0 && $lat == 0){
    $coord_string = "";
}
else{
    $coord_string = " AND u.latitude BETWEEN '$minLat' AND '$maxLat'
    AND u.longitude BETWEEN '$minLon' AND '$maxLon' ";
}

$result = mysqli_query($con,"SELECT p.id, p.*, u.longitude, u.latitude FROM Product p
                                    INNER JOIN User u ON p.user_id=u.id 
                                    WHERE p.name LIKE '%$query%' ".$bio_string.$coord_string.$cat_string);

while($r = mysqli_fetch_assoc($result)){
    $rows[] = array_map(null, $r);
}

echo json_encode($rows);
mysqli_free_result($result);
mysqli_free_result($type_result);
mysqli_close($con);
?>