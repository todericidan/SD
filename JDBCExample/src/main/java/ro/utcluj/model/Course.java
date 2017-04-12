package ro.utcluj.model;

/**
 * Created by Admin on 3/12/2017.
 */
public class Course {

    private int courseId;
    private String name;
    private String teacher;
    private int studyYear;

    public Course() {
    }

    public Course(int courseId, String name, String teacher, int studyYear) {
        this.courseId = courseId;
        this.name = name;
        this.teacher = teacher;
        this.studyYear = studyYear;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (studyYear != course.studyYear) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        return teacher != null ? teacher.equals(course.teacher) : course.teacher == null;

    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + studyYear;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("courseId=").append(courseId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", teacher='").append(teacher).append('\'');
        sb.append(", studyYear=").append(studyYear);
        sb.append("}\n");
        return sb.toString();
    }
}
