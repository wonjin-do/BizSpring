<?php
$subject = 'coding everybody http://opentutorials.org egoing@egoing.com 010-0000-0000';
preg_match('~(http://\w+\.\w+)\s(\w+@\w+\.\w+)~', $subject, $match);
//var_dump($match);
echo "\$match에 담긴 값: <br>";
foreach($match as $key => $val){
    echo $key.": ".$val."<br>";
}
?>

<?php
//?: 를 통해 ( )안의 내용이 $match에 담기지 않게 된다.
preg_match('@^(?:http://)?([^/]+)@i',
            "http://www.php.net/index.html",
             $matches);
$host = $matches[1];

preg_match('/[^.]+\.[^.]+$/', $host, $matches);
echo "<br>http://www.php.net/index.html 에서 도메인 추출: <br>";
echo "$matches[0]\n";

$str = 'foobar: 2008';
preg_match('/(?P<name>\w+): (?P<digit>\d+)/', $str, $matches);
print_r($matches);

echo "<br>";

$string = 'April 15, 2003';
$pattern = '/(\w+) (\d+), (\d+)/i';
$replacement = '${1}1,$3';
echo preg_replace($pattern, $replacement, $string);
echo "<br>";


$string = 'The quick brown fox jumped over the lazy dog.';
$patterns = array();
$patterns[0] = '/quick/';
$patterns[1] = '/brown/';
$patterns[2] = '/fox/';
$replacements = array();
//index에 주의할 것!
$replacements[2] = 'bear';
$replacements[1] = 'black';
$replacements[0] = 'slow';
echo preg_replace($patterns, $replacements, $string);
echo "<br>";


$patterns = array ('/(19|20)(\d{2})-(\d{1,2})-(\d{1,2})/',
                   '/^\s*{(\w+)}\s*=/');
$replace = array ('\3/\4/\1\2', '$\1 =');
// $startDate = 5/27/1999
echo preg_replace($patterns, $replace, '{startDate} = 1999-5-27');

?>