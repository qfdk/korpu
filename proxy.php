<?php
    header('Access-Control-Allow-Origin:*');
	echo file_get_contents($_GET['url']);
    //echo $file;
?>