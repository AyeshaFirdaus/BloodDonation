<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','blood');
 
$con = mysqli_connect(HOST,USER,PASS,DB);

if($_SERVER['REQUEST_METHOD']=='POST'){
 $name = $_POST['name'];
 $username = $_POST['username'];
 $password = $_POST['password'];
 $email = $_POST['email'];
 $phone = $_POST['phone'];
 $state = $_POST['state'];
 $city = $_POST['city'];
 $area = $_POST['area'];
 $bg = $_POST['blood_group'];

 if($name == '' || $username == '' || $password == '' || $email == ''|| $phone == '' || $state == '' || $city == '' || $area == ''|| $bg == ''){
 echo 'please fill all values';
 }else{

 $sql = "SELECT * FROM user WHERE username='$username' OR email='$email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 if(isset($check)){
 echo 'username or email already exist';
 }else{ 
 $sql = "INSERT INTO user (name,username,email,phone,state,city,area,password,blood_group) VALUES('$name','$username','$email','$phone','$state','$city','$area','$password','$bg')";
 if(mysqli_query($con,$sql)){
 echo 'successfully registered';
 }else{
 echo 'oops! Please try again!';
 }
 }
 mysqli_close($con);
 }
}else{
echo 'error';
}