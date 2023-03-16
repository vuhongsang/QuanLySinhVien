package model;

public class Student  implements Comparable<Student>{
    private String id;
    private String full_name;
    private long gender;
    private String birhdate;
    private String address;
    private String phone;
    private String email;
    private long GPA;

    public Student() {
    }

    public Student(String id, String full_name, long gender, String birhdate, String address, String phone, String email, long GPA) {
        this.id = id;
        this.full_name = full_name;
        this.gender = gender;
        this.birhdate = birhdate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.GPA = GPA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    public String getBirhdate() {
        return birhdate;
    }

    public void setBirhdate(String birhdate) {
        this.birhdate = birhdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getGPA() {
        return GPA;
    }

    public void setGPA(long GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student[" +
                "id='" + id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", gender=" + gender +
                ", birhdate='" + birhdate + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", GPA=" + GPA +
                ']';
    }

    @Override
    public int compareTo(Student o) {
        if (this.GPA > o.GPA) {
            return 1;
        } else if (this.GPA < o.GPA) {
            return -1;
        }
        return 0;
    }
}
