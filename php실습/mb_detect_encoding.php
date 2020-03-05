인코딩 자동 감지
<?php
 $str = "테스트";
 $encode = array('ASCII','UTF-8','EUC-KR');
 $str_encode = mb_detect_encoding($str, 'auto');
  echo $str_encode;
?>