<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','blood');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$username = $_POST['username'];
$password = $_POST['password'];
//$username="as";
//$password="dsa";
//echo $username;
//echo $password; 
$sql = "select * from user where username='$username' and password='$password'";

$res = mysqli_query($con,$sql);
 
$check = mysqli_fetch_array($res);
 
if(isset($check)){
echo 'success';
}else{
echo 'failure';
}
 
mysqli_close($con);
?>