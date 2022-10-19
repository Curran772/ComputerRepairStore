package Controllers;

public class Date {
	// declare all of our instance variables
	private int month;
	private int day;
	private int year;
	// 1 2 3 4 5 6 7 8 9 10 11 12
	private static int[] daysOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	private static String [] monthNames = { "", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }; 

	public Date() {
		//no argument constructor
		//default date is 01/01/2022
		this(1, 1, 2022); // this is the object's constructor that accepts three parameters.
	}
	
	public Date(int month) {
		// day = 1, year = 2022
		this(month, 1, 2022);
	}
	
	public Date(int month, int day) {
		// year = 2022
		this(month, day, 2022);
	}
	
	public Date(Date d) {
		this(d.month, d.day, d.year);
	}
	
	// create our constructor
	public Date(int month, int day, int year) {
		// if it does not validate properly, throw new IllegalArgumentExeption
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException(
					"Invalid Month. " + month + "is not a valid month. Must be between 1 and 12");
		}

		if (day < 1 || day > daysOfMonth[month]) {
			throw new IllegalArgumentException(
					"Invalid Day. " + day + "is not a valid day. Must be between 1 and " + daysOfMonth[month]);
		}

		if (year < 0) {
			throw new IllegalArgumentException("Invalid year." + year + "must be a positive number.");
		}

		// if it passes through all the validation checks, it means the month is between
		// 1 and 12,
		// day is valid, and year is positive

		this.month = month;
		this.day = day;
		this.year = year;

	}

	public int getMonth() {
		return this.month;

	}

	public int getDay() {
		return this.day;
	}

	public int getYear() {
		return this.year;
	}

	public void setMonth(int month) {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month. " + month + " must be between 1 and 12.");
		}
		this.month = month;
	}

	public void setDay(int day) {
		if (day < 1 || day > daysOfMonth[month]) {
			throw new IllegalArgumentException("Invalid month." + day + "must be between 1 and " + daysOfMonth[month]);
		}
		this.day = day;
	}

	public void setYear(int year) {
		if (year < 0) {
			throw new IllegalArgumentException("Invalid year. " + year + "must be a positive number.");
		}
		this.year = year;
	}

	public void setDate(int month, int day, int year) {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException(
					"Invalid Month. " + month + " is not a valid month. Must be between 1 and 12");
		}

		if (day < 1 || day > daysOfMonth[month]) {
			throw new IllegalArgumentException(
					"Invalid Day. " + day + " is not a valid day. Must be between 1 and " + daysOfMonth[month]);
		}

		if (year < 0) {
			throw new IllegalArgumentException("Invalid year. " + year + " must be a positive number.");
		}
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	// using the daysOfMonth array
//	public String toString() {
//		String date = String.format("%02d/%02d/%d%n", month, day, year);
//		return date;
//	}
	
	// using the monthNames array
	public String toString() {
		String date = String.format("%s %d, %d%n", monthNames[month], day, year);
		return date;
	}
}
