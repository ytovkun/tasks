package studentInfo;

import logic.CoursesStatus;
import lombok.*;
import taskItems.TrainingCenterTimetable;

import java.time.LocalDateTime;

@Getter
@ToString
public class Student {
    @Setter
    private String studentFullName;
    @Setter
    private TrainingCenterTimetable courseTimetable;
    @Setter
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * @param studentFullName
     *            - contain full student name
     * @param courseTimetable
     *            - student timetable
     * @param startDate
     *            - student started learning date
     */
    public Student(String studentFullName, TrainingCenterTimetable courseTimetable, LocalDateTime startDate) {
        this.studentFullName = studentFullName;
        this.courseTimetable= courseTimetable;
        this.startDate = CoursesStatus.getStartDate(startDate);
        this.endDate = CoursesStatus.getEndDate(startDate, courseTimetable);
    }
}