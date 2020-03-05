<?php
$dsn = "mysql:host=localhost;port=3306;dbname=shopdb;charset=utf8";
try {
    $db = new PDO($dsn, "root", "1234");
    $db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "데이터베이스 연결 성공!!<br/>";
    $keyword = "%테스트%";
    $no = 1;

    $query = "SELECT * FROM membertbl WHERE memberName = ?";

    $stmt = $db->prepare($query);
    $stmt->execute(array("원진"));
    //$stmt = $pdo->prepare("UPDATE myTable SET name = ? WHERE id = ?")->execute([$_POST['name'], $_SESSION['id']]);
    $result = $stmt->fetchAll(PDO::FETCH_NUM);

    for($i = 0; $i < count($result); $i++) {
        printf ("%s : %s <br />", $result[$i][0], $result[$i][1]);
    }


} catch(PDOException $e) {
    echo $e->getMessage();
}
?>