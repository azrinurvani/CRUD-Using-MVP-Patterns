<?php

require 'koneksi.php';

$title = $_POST['title'];
$note = $_POST['note'];
$color = $_POST['color'];

$query = "INSERT INTO notes (title,note,color) VALUES ('$title','$note','$color') ";

if (mysqli_query($con, $query)) {
    $response['success'] = true;
    $response['message'] = "Successfully";
} else {
    $response['success'] = false;
    $response['message'] = "Failure";
}
echo json_encode($response);
