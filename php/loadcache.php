<?php
/**
 * Created by PhpStorm.
 * User: ystc
 * Date: 26.04.2017
 * Time: 13:53
 */

require "config.php";
header('Content-type: text/html; charset=utf-8');

$sql_query = "SELECT * FROM Product INNER JOIN User ON Product.user_id=User.id limit 10";
$result_set = mysqli_query($con, $sql_query);
$count = 0;
$data = array();

while($fetched_row = mysqli_fetch_array($result_set)){

    $product = (object) array(
        'pid' => $fetched_row['id'],
        'name' => $fetched_row['name'],
        'is_bio' => $fetched_row['is_bio'],
        'price' => $fetched_row['price'],
        'user_id' => $fetched_row['user_id'],
        'type_id' => $fetched_row['type_id'],
        'quantity' => $fetched_row['quantity'],
        'unit_type' => $fetched_row['unit_type'],
        'image_id' => $fetched_row['image_id']
    );

    $user = (object) array(
        'uid' => $fetched_row['id'],
        'first_name' => $fetched_row['first_name'],
        'last_name' => $fetched_row['last_name'],
        'company_name' => $fetched_row['company_name'],
        'address' => $fetched_row['address'],
        'city' => $fetched_row['city'],
        'address' => $fetched_row['address'],
        'postal_code' => $fetched_row['postal_code'],
        'email' => $fetched_row['email'],
        'phone_number' => $fetched_row['phone_number'],
        'password' => $fetched_row['password'],
        'is_bio' => $fetched_row['is_bio'],
        'likes' => $fetched_row['likes']
    );

    //&$joined_rows['Data']['Product'] = $row['product_data'];
    //$joined_rows['Data']['User'] = $row['user_data'];

    $data[] = $product;
    $data[] = $user;
}


foreach ($data as $inner){
    echo json_encode($inner);
}
mysqli_free_result($result_set);
mysqli_close($con);
?>