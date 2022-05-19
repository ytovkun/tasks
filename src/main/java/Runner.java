import report.ReportLong;
import report.ReportShort;
import studentInfo.Student;

import java.time.LocalDateTime;

public class Runner {

    public static void main(String[] args) {

        ReportShort reportShort = new ReportShort();
        ReportLong reportLong = new ReportLong();
        LocalDateTime todayTime = LocalDateTime.now();
        Student[] students = Source.initialize(todayTime);

        reportShort.printReport(students, todayTime);
        reportLong.printReport(students, todayTime);
    }
}