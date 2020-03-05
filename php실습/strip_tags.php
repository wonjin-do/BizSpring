<?php 
    //strip_tags는 문자열에서 HTML 태그와 PHP 태그 제거하는 함수입니다.

    echo strip_tags( '<p>Lorem <strong>Ipsum</strong></p>' );
    
    //<strong> 태그는 살려둠
    echo strip_tags( '<p>Lorem <strong>Ipsum</strong></p>', '<strong>' )

    
?>