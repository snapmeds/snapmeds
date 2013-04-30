<?php
require_once("sensitive_data.php");

// protects from SQL injection
function format($str) {
	$str = trim($str);
	if ($str){
		return "\"".$str."\"";
	} else {
		return $str;
	}
}

$username = $_GET['username'];
$password = $_GET['password'];

if ($username === "restful" && $password == "api"){
	$connection = new mysqli(get_database_server(), get_database_user(), get_database_password(), get_database_name());
	
	$conflictingconditions = $connection->real_escape_string($_GET['conflictingconditions']);
	$genericnames = $connection->real_escape_string($_GET['genericnames']);
	$ndc = $connection->real_escape_string($_GET['ndc']);
	$precautions = $connection->real_escape_string($_GET['precautions']);
	$uses = $connection->real_escape_string($_GET['uses']);
	$warnings = $connection->real_escape_string($_GET['warnings']);
	$conflictingconditions = $connection->real_escape_string($_GET['conflictingconditions']);
	$setid = $connection->real_escape_string($_GET['setid']);
	$adversereactions = $connection->real_escape_string($_GET['adversereactions']);
	$boxwarnings = $connection->real_escape_string($_GET['boxwarnings']);
	$name = $connection->real_escape_string($_GET['name']);
	$medicationguide = $connection->real_escape_string($_GET['medicationguide']);
    if($stmt = $connection -> prepare("INSERT INTO NDC (setid, ndc) VALUES ( ? , ?)")) {
        $stmt -> bind_param("ss", $setid,$ndc);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO ADVERSEREACTIONS (setid, adversereactions) VALUES ( ?, ? )")) {
        $stmt -> bind_param("ss", $setid,$adversereactions);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO CONFLICTINGCONDITIONS (setid,conflictingconditions) VALUES ( ? , ? )")) {
        $stmt -> bind_param("ss", $setid,$conflictingconditions);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO GENERICNAMES (setid, genericnames) VALUES ( ? , ? )")) {
        $stmt -> bind_param("ss", $setid,$genericnames);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO PRECAUTIONS (setid, precautions) VALUES ( ? , ? )")){
        $stmt -> bind_param("ss", $setid,$precautions);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO BOXWARNINGS (setid, boxwarnings) VALUES ( ? , ? )")){
        $stmt -> bind_param("ss", $setid,$boxwarnings);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO USES (setid, uses) VALUES ( ? , ? )")){
        $stmt -> bind_param("ss", $setid,$uses);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO WARNINGS (setid, warnings) VALUES ( ? , ? )")){
        $stmt -> bind_param("ss", $setid,$warnings);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO NAME (setid, name) VALUES ( ? , ? )")){
        $stmt -> bind_param("ss", $setid,$name);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
    if($stmt = $connection -> prepare("INSERT INTO MEDICATIONGUIDE (setid, medicationguide) VALUES ( ? , ? )")){
        $stmt -> bind_param("ss", $setid, $medicationguide);
        $stmt -> execute();
        /* Bind results */
        $stmt -> bind_result($result);
        /* Fetch the value */
        $stmt -> fetch();
    }
	
	$connection->close();
}


?>
