<?php
//while(ob_end_flush()); // Stop all ob_start()

ob_start();
for ($i = 0; $i < 10; $i++) {
	echo $i;
	echo str_pad("", 4096);
    sleep(1);
	ob_flush();
    flush();
    
}
ob_end_flush();
?>




ob_start — Turn on output buffering

<?php

function callback($buffer)
{
  // replace all the apples with oranges
  return (str_replace("apples", "oranges", $buffer));
}

ob_start("callback");

?>
<html>
<body>
<p>It's like comparing apples to oranges.</p>
</body>
</html>
<?php

ob_end_flush();

?>