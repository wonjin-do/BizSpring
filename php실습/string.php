<?php
//문자열 쪼개기
$data = "foo:*:1023:1000::/home/foo:/bin/sh";
list($user, $pass, $uid, $gid, $gecos, $home, $shell) = explode(":", $data);
echo $user; // foo
echo $pass; // *

//Join array elements with a string
$array = array('lastname', 'email', 'phone');
$comma_separated = implode(",", $array);
echo $comma_separated; // lastname,email,phone




?>

<?php 
    //I\'m a boy 로 바꾸어 주는 작업 addslashes
    $str1 = "I'm a boy";
    echo addslashes($str1)."<br>";

    //I'm a boy 로 바꾸어 주는 작업stripslashes
    $str2 = "I\'m a boy";
    echo stripslashes($str2)."<br>";

    //XSS공격 방지하는 함수 htmlspecialchars

    $entity= "<b>b 태그요소가 문자열로 출력화면에 나타난다.</b>";
    echo htmlspecialchars($entity);

    //ucfirst — Make a string's first character uppercase
    $foo = 'hello world!';
    $foo = ucfirst($foo);             // Hello world!

    //lcfirst - Make a string's first character lowercase
    $foo = 'HelloWorld';
    $foo = lcfirst($foo);             // helloWorld

    //strtolower() 소문자 변환
    //strtoupper() 대문자 변환

    
?>

부분문자열
<?php
$rest = substr("abcdef", -1);    // returns "f"
$rest = substr("abcdef", -2);    // returns "ef"

//두번쨰 인자가 양수면 갯수를 뜻함.
$rest = substr("abcdef", -3, 1); // returns "d"

//두번째 인자가 음수면 인덱스를 뜻함
$rest = substr("abcdef", 0, -1);  // returns "abcde"
$rest = substr("abcdef", 2, -1);  // returns "cde"
$rest = substr("abcdef", 4, -4);  // returns false
$rest = substr("abcdef", -3, -1); // returns "de"
?>