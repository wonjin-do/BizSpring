import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarDTO {

	private int year;
	private int month;
	private List<List<String>> calendar;
	private List<String> dayList;
	private int lastDayOfTheMonth;
	private int dayOfTheWeek;
	private int weeksInTheMonth;
	private Date date;

	public CalendarDTO(int year, int month) {
		this.year = year;
		this.month = month;
		calendar = new ArrayList<List<String>>();
		dayList = new ArrayList<String>();
		LocalDate localDate = LocalDate.of(year, month, 1);
		lastDayOfTheMonth = localDate.lengthOfMonth();
		dayOfTheWeek = localDate.getDayOfWeek().getValue();

		if (dayOfTheWeek == 7) {
			dayOfTheWeek = 0;
		}

		if (lastDayOfTheMonth == 28 && dayOfTheWeek == 0) {
			weeksInTheMonth = 4;
		} else if (lastDayOfTheMonth == 30 && dayOfTheWeek == 6) {
			weeksInTheMonth = 6;
		} else if (lastDayOfTheMonth == 31 && dayOfTheWeek >= 5) {
			weeksInTheMonth = 6;
		} else {
			weeksInTheMonth = 5;
		}

		date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

}