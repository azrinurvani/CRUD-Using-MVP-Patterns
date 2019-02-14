<?php

require_once 'koneksi.php';

$query = mysqli_query($con,"SELECT * FROM notes");

$response = array();

while ($row = mysqli_fetch_assoc($query)){
    array_push($response, 
            array(
                'id'    => $row['id'],
                'title' => $row['title'],
                'note'  => $row['note'],
                'color' => $row['color'],
                'date'  => $row['date'],
                                
            ));
}
echo json_encode($response);