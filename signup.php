<?php
$response=array();
$response['flag']=0;
 include("connection.php");


if($_SERVER['REQUEST_METHOD']=='POST')
{
	
	$user=$_POST['username'];
	$pass=$_POST['password'];
	$query="INSERT INTO user(username,password) VALUES('$user','$pass')";
	$result=mysqli_query($con,$query);
	if($result)
	{
		$response['flag']=1;
    }
    else{
    	$response['flag']=0;
    	$response['message']="sign up failed";
    }
    echo json_encode($response);
}
?>