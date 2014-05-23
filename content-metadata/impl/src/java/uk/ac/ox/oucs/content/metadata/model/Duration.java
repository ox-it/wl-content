package uk.ac.ox.oucs.content.metadata.model;

/**
 * @author Matthew Buckett
 */
public class Duration {

	public enum Unit {
		YEAR, SEMESTER, STUDY_PERIOD, TERM, MONTH, WEEK, DAY, CLASS, HOUR, MINUTE, SECOND;

		/**
		 * @return The lowercase version of the name.
		 */
		public String toString() {
			return this.name().toLowerCase();
		}

		public static Unit parse(Object value) {
			try {
				if (value != null) {
					return Unit.valueOf(value.toString().toUpperCase());
				}
			} catch (IllegalArgumentException iae) {
				// Ignore
			}
			return null;
		}
	}

	private Integer firstCount;
	private Unit firstUnit;
	private Integer secondCount;
	private Unit secondUnit;

	public Integer getFirstCount() {
		return firstCount;
	}

	public void setFirstCount(Integer firstCount) {
		this.firstCount = firstCount;
	}

	public Unit getFirstUnit() {
		return firstUnit;
	}

	public void setFirstUnit(Unit firstUnit) {
		this.firstUnit = firstUnit;
	}

	public Integer getSecondCount() {
		return secondCount;
	}

	public void setSecondCount(Integer secondCount) {
		this.secondCount = secondCount;
	}

	public Unit getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(Unit secondUnit) {
		this.secondUnit = secondUnit;
	}

	boolean isEmpty() {
		return firstCount == null && firstUnit == null && secondCount == null && secondUnit == null;
	}
}
