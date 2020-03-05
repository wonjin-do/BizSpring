<?php
$path = "C:\ginx-1.16.1\html\phpinfo.php";
//해당 파일이 소속한 디렉토리 절대경로
echo "dirname(path) :";
echo dirname($path).PHP_EOL;
echo "<br>";

echo "realpath(__FILE__): ";
echo realpath(__FILE__);
echo "<br>";

echo "__FILE__: ";
echo __FILE__;
echo "<br>";

//파일명
echo "<br><br>basename()<br>";
//확장자 제외
echo "1) ".basename("/etc/sudoers.d", ".d")."<br>";
//확장자 포함
echo "2) ".basename("/etc/sudoers.d")."<br>"; 

$path_parts = pathinfo($path);    
//var_dump($path_parts);

//절대경로를 구성하는 요소
echo "<br>pathinfo(): Returns information about a file path<br>";
foreach ($path_parts as $key => $val) { 
    echo $key." => ".$val."<br>";
} 
echo "<br><br>";
?>
