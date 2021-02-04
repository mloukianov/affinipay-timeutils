package com.foobar.timeutils;

/**
 * Utility method(s) for manipulating time of day formatted loosely according to ISO-8601
 *
 * Time of the day is represented by 12-hour time string formatted as "[H]H:MM {AM|PM}"
 */
public class ISO8601TimeUtils {

	/**
	 * Adds given number of minutes to time of the day represented by 12-hour time string formatted as "[H]H:MM {AM|PM}"
	 *
	 * @param timeStr  time of the day represented by 12-hour time string formatted as "[H]H:MM {AM|PM}"
	 * @param minutes  number of minutes to add to the time of day (may also be negative)
	 *
	 * @return  new time of the day represented by 12-hour time string formatted as "[H]H:MM {AM|PM}"
	 *
	 * @throws IllegalArgumentException  when timeStr argument has invalid format
	 */
	public static String addMinutes(String timeStr, int minutes) {

		if (timeStr == null || timeStr.isEmpty()) {
			throw new IllegalArgumentException("timeStr can not be null or empty");
		}

		// regexp for time string formatted as "[H]H:MM {AM|PM}" would be ^(1[0-2]|0?[1-9]):[0-5][0-9] (AM|PM)$
		if (!timeStr.matches("^(1[0-2]|0?[1-9]):[0-5][0-9] (AM|PM)$")) {
			throw new IllegalArgumentException("timeStr has invalid format: " + timeStr);
		}

		// extract time of day parts
		String[] split = timeStr.split("\\s");
		String[] hhmm = split[0].split(":");

		int hh = Integer.parseInt(hhmm[0]);
		int mm = Integer.parseInt(hhmm[1]);
		String ampm = split[1];

		// nothing to do here, return original string
		if (minutes == 0) {
			return timeStr;
		}

		int newMM = (minutes + mm) % 60;

		if (newMM < 0) {
			newMM = 60 + newMM;
		}

		int hours = (minutes + mm) >= 0?(minutes + mm) / 60 : (minutes + mm - 60) / 60;
		int newHH = (hours + hh) % 12;

		if (newHH < 0) {
			newHH = 12 + newHH;
		}

		int halfdays = (hours + hh) / 12;

		if (halfdays < 0) {
			halfdays = 1 + halfdays;
		}

		int flip = (hh == 12) ? (halfdays+1) % 2 : halfdays % 2;

		if (newHH == 0) {
			newHH = 12;
		}

		String newAMPM = ampm;

		if (flip == 1) {
			newAMPM = ampm.equals("AM")?"PM":"AM";
		}

		return String.format("%d:%02d %s", newHH, newMM, newAMPM);
	}
}
