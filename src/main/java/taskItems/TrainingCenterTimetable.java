package taskItems;

import lombok.Getter;

public class TrainingCenterTimetable {

    public static final int STARTING_AT = 10;
    public static final int ENDING_AT = 18;

    @Getter
    private final String nameTimetable;
    @Getter
    private final Course[] coursesInTimetable;
    @Getter
    private int sumHours;

    /**
     * @param nameTimetable - name of Timetable
     * @param courses       - some Courses which must contain in current Timetable
     */
    public TrainingCenterTimetable(String nameTimetable, Course... courses) {
        this.nameTimetable = nameTimetable;
        this.sumHours = 0;
        int i = courses.length;
        this.coursesInTimetable = new Course[i];
        for (int j = 0; j < i; j++) {
            this.coursesInTimetable[j] = new Course(courses[j].getCourseName(), courses[j].getCourseDuration());
            this.sumHours = this.sumHours + courses[j].getCourseDuration();
        }
    }
}