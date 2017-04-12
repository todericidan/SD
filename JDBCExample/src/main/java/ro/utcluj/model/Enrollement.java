package ro.utcluj.model;

/**
 * Created by Admin on 3/12/2017.
 */
public class Enrollement {

    private int courseId;
    private int studentId;

    public Enrollement() {
    }

    public Enrollement(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enrollement that = (Enrollement) o;

        if (courseId != that.courseId) return false;
        return studentId == that.studentId;

    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + studentId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enrollement{");
        sb.append("courseId=").append(courseId);
        sb.append(", studentId=").append(studentId);
        sb.append("}\n");

        return sb.toString();
    }
}
