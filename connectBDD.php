<?php
// On se connecte a la base
$db_host = 'localhost';
$db_user = 'root';
$db_mdp = 'mysql1992';
$db_name = 'shapter';
$db_con = mysql_connect($db_host,$db_user,$db_mdp) or die('could not connect (in script php)');

mysql_select_db('ue', $db_name); or die('could not select the database (in script php)')
$req=mysql_query("SELECT * FROM app_course");
while($row=mysql_fetch_assoc($req))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>