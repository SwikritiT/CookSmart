<?php
include("connection.php");
$sql="SELECT * from `profile` ";
 $result=mysqli_query($con,$sql);

$data = mysqli_fetch_all($result,MYSQLI_ASSOC) ;

echo json_encode(array('response_list'=>$data));

$sql1= "TRUNCATE TABLE profile;";
$result=mysqli_query($con,$sql1);


?>