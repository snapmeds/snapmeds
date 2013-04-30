package com.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Frequency {
	// Units between each occurence (ex. every 3 days -> interval = 3)
	private int interval;

	// User selected times (from timepickers)
	private ArrayList<Long> times;

	// number of user selected times (ex. twice every day -> numTimes = 2)
	// We can't just use times.size() because the page uses this to determine
	// how many timepickers to show
	private int numTimes;

	// Unit of time (ex. every day -> unit = DAILY)
	private FrequencyUnit unit;

	// Units in duration (ex. for 3 weeks -> duration = 3)
	private int duration;

	// Duration units (ex. for 3 weeks -> durationUnit = WEEKLY)
	private FrequencyUnit durationUnit;

	public Frequency() {
		numTimes = 0;
		interval = 0;
		unit = FrequencyUnit.INVALID;
		times = new ArrayList<Long>();
	}

	public Frequency(int numTimes, FrequencyUnit unit, int interval) {
		this.times = new ArrayList<Long>();
		this.numTimes = numTimes;
		this.unit = unit;
		this.interval = interval;
		this.duration = 0;
		this.durationUnit = FrequencyUnit.INVALID;
	}

	public Frequency(ArrayList<Long> times, FrequencyUnit unit, int interval) {
		this.times = times;
		this.numTimes = times.size();
		this.unit = unit;
		this.interval = interval;
		this.duration = 0;
		this.durationUnit = FrequencyUnit.INVALID;
	}

	public void parseString(String unparsedFrequency) {
		parseNumTimes(unparsedFrequency);
		parseUnit(unparsedFrequency);
		parseInterval(unparsedFrequency);
	}

	private void parseNumTimes(String unparsedFrequency) {
		if (unparsedFrequency.matches("(?i).*(1 TIME|ONE TIME|ONCE).*"))
			setNumTimes(1);
		else if (unparsedFrequency.matches("(?i).*(2 TIMES|TWO TIMES|TWICE).*"))
			setNumTimes(2);
		else if (unparsedFrequency.matches("(?i).*(3 TIMES|THREE TIMES).*"))
			setNumTimes(3);
		else if (unparsedFrequency.matches("(?i).*(4 TIMES|FOUR TIMES).*"))
			setNumTimes(4);
		else if (unparsedFrequency.matches("(?i).*(5 TIMES|FIVE TIMES).*"))
			setNumTimes(5);
		else if (unparsedFrequency.matches("(?i).*(6 TIMES|SIX TIMES).*"))
			setNumTimes(6);
		else if (unparsedFrequency.matches("(?i).*(7 TIMES|SEVEN TIMES).*"))
			setNumTimes(7);
		else if (unparsedFrequency.matches("(?i).*(8 TIMES|EIGHT TIMES).*"))
			setNumTimes(8);
		else if (unparsedFrequency.matches("(?i).*(9 TIMES|NINE TIMES).*"))
			setNumTimes(9);
		else
			setNumTimes(1);
	}

	private void parseUnit(String unparsedFrequency) {
		FrequencyUnit unit = calcFreqUnit(unparsedFrequency);
		if (unit == FrequencyUnit.INVALID) {
			unit = FrequencyUnit.DAILY;
		}
		setUnit(unit);
	}

	private void parseInterval(String unparsedFrequency) {
		if (unparsedFrequency.matches("(?i).*(EVERY 1|EVERY ONE).*"))
			setInterval(1);
		else if (unparsedFrequency
				.matches("(?i).*(EVERY 2|EVERY TWO|EVERY OTHER).*"))
			setInterval(2);
		else if (unparsedFrequency.matches("(?i).*(EVERY 3|EVERY THREE).*"))
			setInterval(3);
		else if (unparsedFrequency.matches("(?i).*(EVERY 4|EVERY FOUR).*"))
			setInterval(4);
		else if (unparsedFrequency.matches("(?i).*(EVERY 5|EVERY FIVE).*"))
			setInterval(5);
		else if (unparsedFrequency.matches("(?i).*(EVERY 6|EVERY SIX).*"))
			setInterval(6);
		else if (unparsedFrequency.matches("(?i).*(EVERY 7|EVERY SEVEN).*"))
			setInterval(7);
		else if (unparsedFrequency.matches("(?i).*(EVERY 8|EVERY EIGHT).*"))
			setInterval(8);
		else if (unparsedFrequency.matches("(?i).*(EVERY 9|EVERY NINE).*"))
			setInterval(9);
		else
			setInterval(0);
	}

	public void parseDuration(String unparsedDuration) {
		this.durationUnit = calcFreqUnit(unparsedDuration);
		if (unparsedDuration.matches("(?i).*(1|ONE).*"))
			setDuration(1);
		else if (unparsedDuration.matches("(?i).*(2|TWO).*"))
			setDuration(2);
		else if (unparsedDuration.matches("(?i).*(3|THREE).*"))
			setDuration(3);
		else if (unparsedDuration.matches("(?i).*(4|FOUR).*"))
			setDuration(4);
		else if (unparsedDuration.matches("(?i).*(5|FIVE).*"))
			setDuration(5);
		else if (unparsedDuration.matches("(?i).*(6|SIX).*"))
			setDuration(6);
		else if (unparsedDuration.matches("(?i).*(7|SEVEN).*"))
			setDuration(7);
		else if (unparsedDuration.matches("(?i).*(8|EIGHT).*"))
			setDuration(8);
		else if (unparsedDuration.matches("(?i).*(9|NINE).*"))
			setDuration(9);
		else
			setDuration(0);
	}

	public void addTime(Long time) {
		this.times.add(time);
	}

	public List<Long> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Long> times) {
		this.times = times;
	}

	public int getNumTimes() {
		return numTimes;
	}

	public FrequencyUnit getUnit() {
		return unit;
	}

	public int getInterval() {
		return interval;
	}

	public int getDuration() {
		return duration;
	}

	public FrequencyUnit getDurationUnit() {
		return durationUnit;
	}

	public void setNumTimes(int numTimes) {
		this.numTimes = numTimes;
	}

	public void setUnit(FrequencyUnit unit) {
		this.unit = unit;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setDurationUnit(FrequencyUnit durationUnit) {
		this.durationUnit = durationUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + interval;
		result = prime * result + numTimes;
		result = prime * result + ((times == null) ? 0 : times.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Frequency)) {
			return false;
		}
		Frequency otherFrequency = (Frequency) other;
		if (interval != otherFrequency.interval) {
			return false;
		}
		if (numTimes != otherFrequency.numTimes) {
			return false;
		}
		if (times != null && !times.equals(otherFrequency.times)) {
			return false;
		}
		if (times == null && otherFrequency.times != null) {
			return false;
		}
		if (unit != otherFrequency.unit) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("h:mm");
		buf.append(numTimes);
		buf.append(" time");
		if (numTimes > 1)
			buf.append("s");
		if (times.size() > 0)
			buf.append(" (");
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < times.size(); i++) {
			cal.setTimeInMillis(times.get(i));
			buf.append(formatter.format(cal.getTime()));
			if (i != times.size() - 1)
				buf.append(", ");
		}
		if (times.size() > 0)
			buf.append(")");
		buf.append(" every ");
		if (interval > 1)
			buf.append(interval + " ");
		switch (unit) {
		case MINUTELY:
			buf.append("minute");
			break;

		case HOURLY:
			buf.append("hour");
			break;

		case DAILY:
			buf.append("day");
			break;

		case WEEKLY:
			buf.append("week");
			break;

		case MONTHLY:
			buf.append("month");
			break;
		}
		if (interval > 1)
			buf.append("s");
		if (duration > 0) {
			buf.append(" for " + duration + " ");
			switch (durationUnit) {
			case MINUTELY:
				buf.append("minute");
				break;

			case HOURLY:
				buf.append("hour");
				break;

			case DAILY:
				buf.append("day");
				break;

			case WEEKLY:
				buf.append("week");
				break;

			case MONTHLY:
				buf.append("month");
				break;
			}
			if (duration > 1)
				buf.append("s");
		}
		return buf.toString();
	}

	/**
	 * static function for converting a string into a frequencyunit.
	 * 
	 * @param unparsedFrequency
	 * @return
	 */
	public static FrequencyUnit calcFreqUnit(String unparsedFrequency) {
		if (unparsedFrequency.matches("(?i).*(HOURLY|HOUR|HOURS).*"))
			return FrequencyUnit.HOURLY;
		else if (unparsedFrequency.matches("(?i).*(DAILY|DAY|DAYS).*"))
			return FrequencyUnit.DAILY;
		else if (unparsedFrequency.matches("(?i).*(WEEKLY|WEEK|WEEKS).*"))
			return FrequencyUnit.WEEKLY;
		else if (unparsedFrequency.matches("(?i).*(MONTHLY|MONTH|MONTHS).*"))
			return FrequencyUnit.MONTHLY;
		else if (unparsedFrequency.matches("(?i).*(MINUTELY|MINUTE|MINUTES).*"))
			return FrequencyUnit.MINUTELY;
		else
			return FrequencyUnit.INVALID;
	}

}
