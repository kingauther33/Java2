public class StudentModel {
    private String studentID;
    private String studentName;
    private String address;
    private String phone;

    public StudentModel() {
        studentID = "";
        studentName = "";
        address = "";
        phone = "";
    }

    public StudentModel(String studentID, String studentName, String address, String phone) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.address = address;
        this.phone = phone;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    @Override
    public String toString() {
        return String.format("Student[%s,%s,%s,%s]", studentID, studentName, address, phone);
    }
}
