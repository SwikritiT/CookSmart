<?php
// $response=array();
// $response['flag']=0;
 include("connection.php");


if($_SERVER['REQUEST_METHOD']=='POST')
{
	$inputDrinksList=$_POST['ingredients_array'];

	 $drinksArray=json_decode($inputDrinksList,true);
	 //array_unique($inputDrinksList);
	 $arringredient=array();
	 $arringredientname=array();
	 print_r($drinksArray);
	 foreach ($drinksArray as $drinkArray) {
	 	foreach ($drinkArray as $key => $value) {
	 		// print_r($value);
	 		// echo "\n";
	 	$sql= "SELECT * from `ingredient` WHERE ingredient_name='$value';";
		$result=mysqli_query($con,$sql);
		$resultCheck= mysqli_num_rows($result);
		if($resultCheck>0)
		{
			while($row=mysqli_fetch_assoc($result)){  //fetch_assoc fetches the data in an arrAY SO HERE ROW IS AN ARRAY

				$id= $row['ingredient_id'];
			}
			
			print_r($id);
			echo "\n";
		
		 $sql2="SELECT * FROM  `can_be_made` WHERE ingredient_id='$id';";
		 $result2=mysqli_query($con,$sql2);
		 $resultCheck2= mysqli_num_rows($result2);
		 if($resultCheck2>0)
		{
			while($row=mysqli_fetch_assoc($result2)){  //fetch_assoc fetches the data in an arrAY SO HERE ROW IS AN ARRAY

				$arr= $row['recipe_id'];
				array_push($arringredient, $arr);
				//print_r($arr);
			}
			//echo "\n";
			
		}
	}
	 	}
	 	# code...
	 }
	sort($arringredient);
	print_r($arringredient);
	$dups = array();
	foreach(array_count_values($arringredient) as $val => $c)
	    if($c > 1) $dups[] = $val;
	   print_r($dups);

	   //to print recipe from database:

	  for($i=0;$i<count($dups);$i++)
	 	{
	 	
	 	$sql3= "INSERT into `display` SELECT * from `recipe` WHERE recipe_id='$dups[$i]'&& category='drinks.php';";//&& category='Drinks'
		$result3=mysqli_query($con,$sql3);
		$resultCheck3= mysqli_num_rows($result3);
		
		}
		
	

}


?>