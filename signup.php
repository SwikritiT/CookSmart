<?php
$response=array();
$response['flag']=0;
 include("connection.php");


if($_SERVER['REQUEST_METHOD']=='POST')
{
	
	$user=$_POST['username'];
	$pass=$_POST['password'];
	$full=$_POST['fullname'];
	$email=$_POST['email'];
	$phone=$_POST['phonenumber'];
	$query="INSERT INTO `users`(user_name,password,fullname,email,phonenumber) VALUES('$user','$pass','$full','$email','$phone')";
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