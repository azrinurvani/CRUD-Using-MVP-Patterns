<?php

require 'koneksi.php';

$id = $_POST['id'];

$query = "DELETE FROM notes WHERE id='".$id."' ";

if (mysqli_query($con, $query)) {
    $response['success'] = true;
    $response['message'] = "Successfully";
} else {
    $response['success'] = false;
    $response['message'] = "Failure";
}
echo json_encode($response);
