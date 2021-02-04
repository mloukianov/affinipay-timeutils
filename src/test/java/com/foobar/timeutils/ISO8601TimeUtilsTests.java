package com.foobar.timeutils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ISO8601TimeUtilsTests {

	@Test
	@DisplayName("1:33 AM + 0 minutes = 1:33 AM")
	void parseAndValidateOneDigitHourAM() {

		String result = ISO8601TimeUtils.addMinutes("1:33 AM", 0);
		assertEquals("1:33 AM", result);
	}


	@Test
	@DisplayName("1:33 PM + 0 minutes = 1:33 PM")
	void parseAndValidateOneDigitHourPM() {

		String result = ISO8601TimeUtils.addMinutes("1:33 PM", 0);
		assertEquals("1:33 PM", result);
	}


	@Test
	@DisplayName("12:33 AM + 0 minutes = 12:33 AM")
	void parseAndValidateTwoDigitHourAM() {

		String result = ISO8601TimeUtils.addMinutes("12:33 AM", 0);
		assertEquals("12:33 AM", result);
	}

	@Test
	@DisplayName("12:33 PM + 0 minutes = 12:33 PM")
	void parseAndValidateTwoDigitHourPM() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", 0);
		assertEquals("12:33 PM", result);
	}

	@Test
	@DisplayName("12:33 PM + 0 minutes = 12:33 PM")
	void addZeroMinutes() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", 0);
		assertEquals("12:33 PM", result);
	}

	@Test
	@DisplayName("12:33 PM + 10 minutes = 12:43 PM")
	void addUnder60Minutes() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", 10);
		assertEquals("12:43 PM", result);
	}

	@Test
	@DisplayName("12:33 PM + 60 minutes = 1:33 PM")
	void addToCrossHourBoundary() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", 60);
		assertEquals("1:33 PM", result);
	}

	@Test
	@DisplayName("11:33 AM + 60 minutes = 12:33 PM")
	void addToCrossAMPMBoundary() {

		String result = ISO8601TimeUtils.addMinutes("11:33 AM", 60);
		assertEquals("12:33 PM", result);
	}

	@Test
	@DisplayName("11:33 AM + 24 hours = 12:33 PM")
	void addMoreThanOneDay() {

		String result = ISO8601TimeUtils.addMinutes("11:33 AM", 1440);
		assertEquals("11:33 AM", result);
	}

	@Test
	@DisplayName("12:33 PM - 10 minutes = 12:23 PM")
	void subtractUnder60Minutes() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", -10);
		assertEquals("12:23 PM", result);
	}

	@Test
	@DisplayName("12:33 PM - 60 minutes = 11:33 AM")
	void subtractToCrossHourBoundary() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", -60);
		assertEquals("11:33 AM", result);
	}

	@Test
	@DisplayName("12:33 PM + 60 minutes = 12:33 PM")
	void subtractToCrossAMPMBoundary() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", -1200);
		assertEquals("4:33 AM", result);
	}

	@Test
	@DisplayName("12:33 PM + 60 minutes = 12:33 PM")
	void subtractMoreThanOneDay() {

		String result = ISO8601TimeUtils.addMinutes("12:33 PM", -1800);
		assertEquals("6:33 AM", result);
	}

	@Test
	@DisplayName("13:33 PM -> IllegalArgumentException")
	void parseAndValidateWrongNumberOfHours() {

		Assertions.assertThrows(IllegalArgumentException.class, () ->
				ISO8601TimeUtils.addMinutes("13:33 AM", 0)
		);
	}

	@Test
	@DisplayName("10:93 PM -> IllegalArgumentException")
	void parseAndValidateWrongNumberOfMinutes() {

		Assertions.assertThrows(IllegalArgumentException.class, () ->
			ISO8601TimeUtils.addMinutes("10:93AM", 0)
		);
	}

	@Test
	@DisplayName("10:33AM -> IllegalArgumentException")
	void parseAndValidateWrongStringFormat() {

		Assertions.assertThrows(IllegalArgumentException.class, () ->
				ISO8601TimeUtils.addMinutes("10:33AM", 0)
		);
	}


	@Test
	@DisplayName("null -> IllegalArgumentException")
	void parseNullTimeOfDayString() {

		Assertions.assertThrows(IllegalArgumentException.class, () ->
				ISO8601TimeUtils.addMinutes(null, 0)
		);
	}
}
