<?PHP
include("connection.php");
$response=array();
$response['flag']=0;
if($_SERVER['REQUEST_METHOD']=='POST')
{

	$user=$_POST['username'];
	$pass=$_POST['password'];
	$query="SELECT username, password from `user` where username='$user' and password='$pass'";
	$result=mysqli_query($con,$query);
	$count=mysqli_num_rows($result);
	if($count > 0)
	{
		$response['flag']=1;
		
	}
	else{
		$response['flag']=0;
		$response['message']="login failed...";


	}
	echo json_encode($response);
}
	?>