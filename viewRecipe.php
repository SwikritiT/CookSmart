<?php
include("connection.php");


$query="SELECT name,link from `display`";
 $result=mysqli_query($con,$query);

//$data=$result->fetch_all(MYSQLI_ASSOC);
$recipes = mysqli_fetch_all($result, 	MYSQLI_ASSOC) ;
		foreach($recipes as $id => $r ){
			foreach($r as $k => $v){
				if($k == 'ingredient' || $k == 'instruction' ){
					// $str = mb_convert_encoding($str, "UTF-8", "Windows-1252");
					$recipes[$id][$k] = mb_convert_encoding($v, "UTF-8", "Windows-1252");
				}
			}
			echo json_encode(array('response_list'=>$recipes));
		}


$query= "TRUNCATE TABLE display;";
$result=mysqli_query($con,$query);



?>