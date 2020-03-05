PHP $_GET / $_POST
PHP $_GET / $_POST is a PHP super global variable which is used to collect form data after submitting an HTML form with method="get/ post".

<html>
<body>

<?php
echo "Study " . $_GET['subject'] . " at " . $_GET['web'];
?>

</body>
</html>