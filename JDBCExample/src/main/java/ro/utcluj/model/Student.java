package ro.utcluj.model;

import java.util.Date;

/**
 * Created by Admin on 3/12/2017.
 */
public class Student {

    private int studentId;
    private String name;
    private Date birthdate;
    private String address;

    public Student() {
    }

    public Student(int studentid, String name, Date birthdate, String address) {
        this.studentId = studentid;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentid) {
        this.studentId = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (birthdate != null ? !birthdate.equals(student.birthdate) : student.birthdate != null) return false;
        return address != null ? address.equals(student.address) : student.address == null;

    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("studentId=").append(studentId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthdate=").append(birthdate);
        sb.append(", address='").append(address).append('\'');
        sb.append("}\n");

        return sb.toString();
    }
}
