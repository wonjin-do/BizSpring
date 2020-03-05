ini_set — Sets the value of a configuration option

기본적인 설정속성은 아래 페이지에 정리되어 있음.

https://www.php.net/manual/en/ini.list.php

<?php
echo ini_get('display_errors');

if (!ini_get('display_errors')) {
    ini_set('display_errors', '1');
}

echo ini_get('display_errors');
?>