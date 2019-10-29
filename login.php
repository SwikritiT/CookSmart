<?PHP
include("connection.php");
$response=array();
$response['flag']=0;
if($_SERVER['REQUEST_METHOD']=='POST')
{

	$user=$_POST['username'];
	$pass=$_POST['password'];
	$query="SELECT user_name, password from `users` where user_name='$user' and password='$pass'";
	$result=mysqli_query($con,$query);
	$count=mysqli_num_rows($result);
	if($count > 0)
	{
		$response['flag']=1;

	$query="INSERT into `profile` SELECT `user_name`,`fullname`,`email`,`phonenumber` from `users` WHERE user_name='$user' and password='$pass'";
	$result=mysqli_query($con,$query);
		
	}
	else{
		$response['flag']=0;
		$response['message']="login failed...";


	}
	echo json_encode($response);
}
	?>