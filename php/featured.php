<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 05.04.2017
 * Time: 09:13
 */
header('Content-Type: application/json');
require "config.php";

$sql = "SELECT * FROM Product ORDER BY RAND() limit 10";
$result = mysqli_query($con, $sql);

$counter = 0;
while($row = $result->fetch_assoc()) {
    $post_data = array(
        $counter => array(
            'id' => $row["id"],
            'name' => $row["name"],
            'is_bio' => $row["is_bio"],
            'price' => $row["price"],
            'user_id' => $row["user_id"],
            'type_id' => $row["type_id"],
            'quantity' => $row["quantity"],
            'unit_type' => $row["unit_type"],
        )
    );
    echo json_encode($post_data);
}



/*
while ($row = mysqli_fetch_array($result)){
    $row_array['id'] = $row['id'];
    $row_array['name'] = $row['name'];
    $row_array['is_bio'] = $row['is_bio'];
    echo json_encode($row_array);
}
*/
?>