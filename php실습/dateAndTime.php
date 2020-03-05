date — Format a local time/date<br>
<?php
// set the default timezone to use. Available since PHP 5.1
date_default_timezone_set('UTC');


// Prints something like: Monday
echo date("l")."<br>";

//Monday 8th of August 2005 03:12:46 PM
echo date('l jS \of F Y h:i:s A')."<br>";

//July 1, 2000 is on a Saturday
echo "July 1, 2000 is on a " . date("l", mktime(0, 0, 0, 7, 1, 2000))."<br>";;

/* use the constants in the format parameter */

echo date(DATE_RFC2822)."<br>";;

// prints something like: 2000-07-01T00:00:00+00:00
echo date(DATE_ATOM, mktime(0, 0, 0, 7, 1, 2000))."<br>";;
?>


유닉스 타임스탬프<br>
<?php
// Set the default timezone to use. Available as of PHP 5.1
date_default_timezone_set('UTC');

// Prints: July 1, 2000 is on a Saturday
echo "March 5, 2020 is on a " . date("l", mktime(0, 0, 0, 3, 5, 2020))."<br>";

// Prints something like: 2006-04-05T01:02:03+00:00
echo date('c', mktime(1, 2, 3, 3, 5, 2020))."<br>";
?>

String to Time
<?php
echo strtotime("now"), "\n";
echo strtotime("10 September 2000"), "\n";
echo strtotime("+1 day"), "\n";
echo strtotime("+1 week"), "\n";
echo strtotime("+1 week 2 days 4 hours 2 seconds"), "\n";
echo strtotime("next Thursday"), "\n";
echo strtotime("last Monday"), "\n";
?>


현재시간
<?php
$nextWeek = time() + (7 * 24 * 60 * 60);
                   // 7 days; 24 hours; 60 mins; 60 secs
echo 'Now:       '. date('Y-m-d') ."\n";
echo 'Next Week: '. date('Y-m-d', $nextWeek) ."\n";
// or using strtotime():
echo 'Next Week: '. date('Y-m-d', strtotime('+1 week')) ."\n";
?>

