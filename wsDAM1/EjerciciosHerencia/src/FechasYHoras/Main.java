package FechasYHoras;

import java.time.LocalDate; // import the LocalDate class
import java.time.LocalDateTime; // import the LocalDateTime class

public class Main {
	public static void main(String[] args) {
		LocalDate date1 = LocalDate.of(2023, 1, 30);
		LocalDate date2 = LocalDate.of(2023, 2, 3);
		LocalDateTime datetime1 = date1.atTime(1, 50, 9);
		LocalDateTime datetime2 = date1.atTime(2, 35, 59);
		System.out.println(datetime2);
		System.out.println("a." + date1);
		System.out.println("b." + date2);
		System.out.println("c." + date1.compareTo(date2));
		System.out.println("d." + date1.compareTo(date1));
		System.out.println("e." + date2.compareTo(date1));
		System.out.println("f." + date1.minusDays(1));
		System.out.println("g." + date1.plusDays(1));
		System.out.println("h." + date1.compareTo(date2.plusDays(1)));
		System.out.println("i." + datetime1);
		System.out.println("i." + datetime2);
		System.out.println("h." + datetime1.compareTo(datetime2.plusDays(1)));
	}
}
