package report;

import studentInfo.Student;

import java.time.LocalDateTime;

public interface ITestReport {

    void printReport(Student[] students, LocalDateTime currentDate);
}
