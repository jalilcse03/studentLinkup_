package com.azharul.android.sids;

/**
 * Created by Azharul on 3/16/2016.
 */
public class Student {
    private int key; ///////////database primary-Key will store here

/////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String id;
    private String studentName;
    private String department;
    private int credit;
    private int weaver;
    private long balance;
    private String cellPhone;
    private String email;
    private String dob;
    private String bloodGroup;
    private String gender;

//////////////////////////////////////////  CONSTRUCTORS  ////////////////////////////////////////////////

    public Student(String id, String studentName, int key, String department, int credit, int weaver, long balance, String cellPhone, String email) {

        this.id = id;
        this.studentName = studentName;
        this.key = key;
        this.department = department;
        this.credit = credit;
        this.weaver = weaver;
        this.balance = balance;
        this.cellPhone = cellPhone;
        this.email = email;
    }
    public Student(String id, String studentName, String department, int credit, int weaver, long balance, String cellPhone, String email,String dob,String bloodGroup,String gender) {
        this.id = id;
        this.studentName = studentName;
        this.department = department;
        this.credit = credit;
        this.weaver = weaver;
        this.balance = balance;
        this.cellPhone = cellPhone;
        this.email = email;
        this.dob=dob;
        this.bloodGroup=bloodGroup;
        this.gender=gender;
    }

    public Student(String id, String studentName,int key) {
        this.id = id;
        this.studentName = studentName;
        this.key = key;
    }

    public Student() {
    }
//////////////////////// Getter And Setter /////////////////////////////////////////////////////////////////

    public int getKey() {

        return key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getWeaver() {
        return weaver;
    }

    public void setWeaver(int weaver) {
        this.weaver = weaver;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
