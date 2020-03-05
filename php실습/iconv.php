iconv — Convert string to requested character encoding<br>
iconv ( string $in_charset , string $out_charset , string $str ) : string<br>

<?php
$text = "This is the Euro symbol '€'.";

echo 'Original : '.$text."<br>";
echo 'TRANSLIT : ', iconv("UTF-8", "ISO-8859-1//TRANSLIT", $text)."<br>";
echo 'IGNORE   : ', iconv("UTF-8", "ISO-8859-1//IGNORE", $text)."<br>";
echo 'Plain    : ', iconv("UTF-8", "ISO-8859-1", $text)."<br>";

?>