<?php

require 'koneksi.php';

$id = $_POST['id'];
$title = $_POST['title'];
$note = $_POST['note'];
$color = $_POST['color'];

$query = "UPDATE notes SET title='".$title."', note='".$note."', color='".$color."' WHERE id='".$id."' ";

if (mysqli_query($con, $query)) {
    $response['success'] = true;
    $response['message'] = "Successfully";
} else {
    $response['success'] = false;
    $response['message'] = "Failure";
}
echo json_encode($response);
