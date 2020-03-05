<?php
//url 분석 함수
$url = 'http://username:password@hostname:9090/path?arg=value#anchor';
foreach (parse_url($url) as $key => $val) { 
    echo $key." => ".$val."<br>";
}
echo "<br>";
echo var_dump(parse_url($url, PHP_URL_SCHEME))."<br>";
echo var_dump(parse_url($url, PHP_URL_USER))."<br>";
echo var_dump(parse_url($url, PHP_URL_PASS))."<br>";
echo var_dump(parse_url($url, PHP_URL_HOST))."<br>";
echo var_dump(parse_url($url, PHP_URL_PORT))."<br>";
echo var_dump(parse_url($url, PHP_URL_PATH))."<br>";
echo var_dump(parse_url($url, PHP_URL_QUERY))."<br>";
echo var_dump(parse_url($url, PHP_URL_FRAGMENT))."<br>";

//URL 인코딩 디코딩    
$encodedURL = "https%3A%2F%2Fwww.urlencoder.org%2F";
echo "<br>decoded URL: ".urldecode($encodedURL)."<br>";    
$decodedURL = "https://www.urlencoder.org/";
echo "<br>encoded URL: ".urlencode($decodedURL)."<br>";    

?>

