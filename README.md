# affinipay-iso8501-timeutils

This project contains implementation and tests for time formatted according to ISO-8601 (short variant).

ISO8601TimeUtils class contains a single static method addMinutes(String timeStr, int minutes).

The first argument is a 12-hour time string formatted as "[H]H:MM {AM|PM}"; the second argument is a signed integer.
The second argument is the number of minutes to add to the time of day represented by the first argument.
The return value should be a string of the same format as the first argument.

The addMinutes() method should validate the input. Possible valid times for the first argument is:
* 12:33 AM
* 12:44 PM
* 1:00 AM

Possible invalid strings to represent time fo the day:
* 13:00 AM
* -2:13 PM
* 12:33AM
* any string that is not in "[H]H:MM {AM|PM}" format

addMinutes() method should throw IllegalArgumentException when time of the day string contains invalid string; exception 
should contain string explaining the reason for exception being thrown

After input is validated, method should calculate the shift and return new time of the day as a string; tests would be:
* addMinutes("12:33 AM", 0) -> should return "12:33 AM"
* addMinutes("12:33 AM", 60) -> should return "1:33 AM"
* addMinutes("12:33 AM", 100) -> should return "2:13 AM"
* addMinutes("11:33 AM", 60) -> should return "12:33PM"
* addMinutes("13:33 AM", 100) -> should throw IllegalArgumentException w/message "timeStr format is invalid: \"13:33 AM\""
