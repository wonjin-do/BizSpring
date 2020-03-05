<?php
  $sitename = '안녕하세요';
  echo mb_substr($sitename, 0, 3, 'utf-8')."<br>";
  // UTF-8 인코딩인 경우
  echo substr("안녕하세요", 0, 2)."<br>";
  echo mb_substr($sitename, 0, 3, 'euc-kr');
  // EUC-KR 인코딩인 경우
?>