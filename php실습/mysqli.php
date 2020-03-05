
<?php

  //conn 얻기

  $db_conn = mysqli_connect("localhost", "root", "1234", "shopdb");
  if (!$db_conn) {
    $error = mysqli_connect_error();
    $errno = mysqli_connect_errno();
    print "$errno: $error\n";
    exit();
  }
  

  $query = "SELECT * FROM membertbl";
  $sql = "
  INSERT INTO membertbl
    (memberID, memberName)
    VALUES(
        'Wonjin1',
        '원진1'
    )
";
  $result = mysqli_query($db_conn, $query);

  if ( $result ) {
    echo "조회된 행의 수 : ".mysqli_num_rows($result)."<br />";
    
    //mysqli_fetch_assoc
    //mysqli_fetch_array 차이


    while ($row = mysqli_fetch_assoc($result)) {
        printf ("%s : %s <br />", $row["memberID"], $row["memberName"]);
    }

      // 결과 해제
      mysqli_free_result($result);

  } else {
      echo "Error : ".mysqli_error($db_conn);
  }
  $result = mysqli_query($db_conn, $sql);
  if($result === false){
    echo '저장하는 과정에서 문제가 생겼습니다. 관리자에게 문의해주세요';
    error_log(mysqli_error($db_conn));
  } else {
    echo '성공했습니다. <a href="index.php">돌아가기</a>';
  }

  mysqli_close($db_conn);
?>


