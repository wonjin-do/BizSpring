extract — Import variables into the current symbol table from an array
배열로부터 추출해서 배열의 key와 같은 이름으로 변수에 값을 할당하는 함수
<br>
<?php

/* Suppose that $var_array is an array returned from
   wddx_deserialize */

$size = "large";
$var_array = array("color" => "blue",
                   "size"  => "medium",
                   "shape" => "sphere");
extract($var_array, EXTR_PREFIX_SAME, "wddx");

echo "$color, $size, $shape, $wddx_size\n";

?>