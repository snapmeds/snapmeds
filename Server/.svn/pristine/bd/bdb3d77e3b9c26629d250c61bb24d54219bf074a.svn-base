<?php
require_once("sensitive_data.php");

// protects from SQL injection
function format($str) {
	$str = trim($str);
	return "\"".$str."\"";

}

$username = $_POST['username'];
$password = $_POST['password'];

if ($username === "restful" && $password == "api"){
	$connection = new mysqli(get_database_server(), get_database_user(), get_database_password(), get_database_name());
	echo "ndc: ".$ndc."\n";
	$conflictingconditions = $connection->real_escape_string($_POST['conflictingconditions']);
	$genericnames = $connection->real_escape_string($_POST['genericnames']);
	$ndc = $connection->real_escape_string($_POST['ndc']);
	$precautions = $connection->real_escape_string($_POST['precautions']);
	$uses = $connection->real_escape_string($_POST['uses']);
	$warnings = $connection->real_escape_string($_POST['warnings']);
	$setid = strtolower($connection->real_escape_string($_POST['setid']));
	$adversereactions = $connection->real_escape_string($_POST['adversereactions']);
	$boxwarnings = $connection->real_escape_string($_POST['boxwarnings']);
	$name = $connection->real_escape_string($_POST['name']);
	$medicationguide = $connection->real_escape_string($_POST['medicationguide']);
	
	if ($ndc != "\"\""){
		$query = "INSERT INTO NDC (setid, ndc) VALUES ( ?, ? )";
        if($stmt = $connection -> prepare($sst)){
           $stmt -> bind_param("ssssssssss", $setid,$adversereactions,$boxwarnings,$conflictingconditions,$genericnames,$precautions,$uses,$name,$warnings,$medicationguide);
           $stmt -> bind_param("ss", $setid,$ndc);
           /* Execute it */
           $stmt -> execute();
           /* Bind results */
           $stmt -> bind_result($result);
           /* Fetch the value */
           $stmt -> fetch();
           }
	} else {

		$sst = "INSERT INTO INFO ".
		"(setid, adversereactions, boxwarnings, conflictingconditions,genericnames,precautions,uses,name,warnings,medicationguide) VALUES ".
		"( ? , ? , ?, ?, ?, ?, ? , ?, ?, ? )";
        if($stmt = $connection -> prepare($sst)){
           $stmt -> bind_param("ssssssssss", $setid,$adversereactions,$boxwarnings,$conflictingconditions,$genericnames,$precautions,$uses,$name,$warnings,$medicationguide);
           /* Execute it */
           $stmt -> execute();
           /* Bind results */
           $stmt -> bind_result($result);
           /* Fetch the value */
           $stmt -> fetch();
           }

	}
	
		
	
	$connection->close();
}


?>
