프록시의 익명성을 이용하면 인터넷에서 악의적인 리플이나 불법적인 행동을 하는 이를 원천 봉쇄가 어려울 수 있습니다. 사용자PC에서 프록시 서버를 경유해 특정 웹사이트로 접근하면 프록시 서버에 의해 사용자의 리얼ip를 숨길 수 있기 때문입니다.

그런데, 웹사이트에 접근할 때, 여러 가지 헤더정보를 넘겨 주게 되는데, 거기에 원 사용자 ip도 같이 넘겨 받게 됩니다. 그 메소드가 "X-Forwarded-For"이고, php에서는 "HTTP_X_FORWARDED_FOR" 변수에 담겨 지게 됩니다.

그러므로 HTTP_X_FORWARDED_FOR 변수로 비교 체크하여 불량 ip를 어느 정도 봉쇄할 수도 있지 않을까 싶습니다.
<?php 
 function getRealIpAddr(){ 
    if(!empty($_SERVER['HTTP_CLIENT_IP']) && getenv('HTTP_CLIENT_IP')){ 
        return $_SERVER['HTTP_CLIENT_IP']; 
    }
    elseif(!empty($_SERVER['HTTP_X_FORWARDED_FOR']) && getenv('HTTP_X_FORWARDED_FOR')){ 
        return $_SERVER['HTTP_X_FORWARDED_FOR']; 
    }
    elseif(!empty($_SERVER['REMOTE_HOST']) && getenv('REMOTE_HOST')){ 
        return $_SERVER['REMOTE_HOST']; 
    }
    elseif(!empty($_SERVER['REMOTE_ADDR']) && getenv('REMOTE_ADDR')){ 
        return $_SERVER['REMOTE_ADDR']; 
    } 
    return false; 
 }


 if($ip = getRealIpAddr()){
     echo $ip;
 }
 ?>