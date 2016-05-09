package com.azharul.android.sids;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Azharul on 3/16/2016.
 */
public class DataSource {

    private DataBaseHelper helper;
    private SQLiteDatabase database;
    private Student student;

    public DataSource(Context context) {
        helper = new DataBaseHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();
    }

    private void close() {
        helper.close();
    }
/////////////////////////////////Student Information Added Here (START POINT) ////////////////////////////////////////////////////

    public boolean addStudent(Student student) {

        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_STUDENT_ID,student.getId());
        contentValues.put(DataBaseHelper.COL_NAME, student.getStudentName());
        contentValues.put(DataBaseHelper.COL_DEPT,student.getDepartment());
        contentValues.put(DataBaseHelper.COL_CREDITS,student.getCredit());
        contentValues.put(DataBaseHelper.COL_WEAVER,student.getWeaver());
        contentValues.put(DataBaseHelper.COL_BALANCE,student.getBalance());
        contentValues.put(DataBaseHelper.COL_CELLPHONE,student.getCellPhone());
        contentValues.put(DataBaseHelper.COL_EMAIL, student.getEmail());
        contentValues.put(DataBaseHelper.COL_DOB, student.getDob());
        contentValues.put(DataBaseHelper.COL_BLOODGROUP, student.getBloodGroup());
        contentValues.put(DataBaseHelper.COL_GENDER, student.getGender());

        long inserted = database.insert(DataBaseHelper.TABLE_STUDENT_INFO,null,contentValues);

        database.close();
        this.close();

        if (inserted > 0) {
            return true;
        } else
            return false;

    }
/////////////////////////////////Student Information Added Here (END POINT) ////////////////////////////////////////////////////

/////////////////////////////////Student LIST Generated  Here (START POINT) ////////////////////////////////////////////////////

    public ArrayList<Student> GetAllStudent() {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {


                String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                student = new Student(studentId, name,id);
                studentList.add(student);
                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }
/////////////////////////////////Student LIST Generated  Here (END POINT) ////////////////////////////////////////////////////


/////////////////////////////////Student Delete  Here (START POINT) ////////////////////////////////////////////////////
    public boolean deleteStudent(int id) {
        this.open();

        int deleted = database.delete(DataBaseHelper.TABLE_STUDENT_INFO, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();

        if (deleted > 0) {
            return true;
        } else return false;

    }
/////////////////////////////////Student Delete  Here (END POINT) ////////////////////////////////////////////////////
    public Student getStudent(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, new String[]{DataBaseHelper.COL_NAME,
                        DataBaseHelper.COL_STUDENT_ID, DataBaseHelper.COL_DEPT, DataBaseHelper.COL_BALANCE, DataBaseHelper.COL_CELLPHONE, DataBaseHelper.COL_CREDITS
                        , DataBaseHelper.COL_EMAIL, DataBaseHelper.COL_WEAVER, DataBaseHelper.COL_DOB,DataBaseHelper.COL_BLOODGROUP,DataBaseHelper.COL_GENDER},
                DataBaseHelper.COL_ID + " =" + id, null, null, null, null);

        cursor.moveToFirst();

        String studentID = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
        String dept = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_DEPT));
        long balance =Long.parseLong(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_BALANCE)));
        String cellPhone = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CELLPHONE));
        int credits =Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CREDITS)));
        String email = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_EMAIL));
        int weaver = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_WEAVER)));
        String dob = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_DOB));
        String bloodGroup = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_BLOODGROUP));
        String gender = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_GENDER));


        cursor.close();
        this.close();
        student = new Student(name,studentID, dept, credits, weaver, balance, cellPhone, email,dob,bloodGroup,gender);
        return student;

    }
    public boolean updateStudent(int id, Student student) {
        this.open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COL_STUDENT_ID,student.getId());
        contentValues.put(DataBaseHelper.COL_NAME, student.getStudentName());
        contentValues.put(DataBaseHelper.COL_DEPT,student.getDepartment());
        contentValues.put(DataBaseHelper.COL_CREDITS,student.getCredit());
        contentValues.put(DataBaseHelper.COL_WEAVER,student.getWeaver());
        contentValues.put(DataBaseHelper.COL_BALANCE, student.getBalance());
        contentValues.put(DataBaseHelper.COL_CELLPHONE,student.getCellPhone());
        contentValues.put(DataBaseHelper.COL_EMAIL, student.getEmail());

        contentValues.put(DataBaseHelper.COL_DOB, student.getDob());
        contentValues.put(DataBaseHelper.COL_BLOODGROUP, student.getBloodGroup());
        contentValues.put(DataBaseHelper.COL_GENDER, student.getGender());

        int updated = database.update(DataBaseHelper.TABLE_STUDENT_INFO, contentValues, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();

        if (updated > 0) {
            return true;
        } else return false;
    }


   /* public ArrayList<Student> getAllStudent() {

        ArrayList<Student> contactLIst = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {


                String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                String dept = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_DEPT));
                String balance = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_BALANCE));
                String cellPhone = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CELLPHONE));
                String credits = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CREDITS));
                String email = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_EMAIL));
                String weaver = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_WEAVER));

                student = new Student(name,studentId,id, dept, credits, weaver, balance, cellPhone, email );
                contactLIst.add(student);
                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return contactLIst;
    }*/
/////////////////////////////////// Already Taken Student-ID will Check HERE /////////////////////////////////////////

    public ArrayList<String> GetIDCheck() {

        ArrayList<String> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {


                String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));

                studentList.add(studentId);
                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }
////////////////////////////// This Method Will work FOR Search Student By his/her Name ////////////////////////////////////

    public ArrayList<Student> SearchByName(String sName) {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();
        String n;

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {

            cursor.moveToFirst();
            n=sName+" cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_ID))";

            for (int i = 0; i < cursor.getCount(); i++) {
                if(sName.equals(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID))))
                {

                    String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    student = new Student(studentId, name,id);
                    studentList.add(student);

                }



                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }
////////////////////// This Method Will Work for Search Student By his/her Student-ID /////////////////////////////////


    public ArrayList<Student> SearchByID(String sID) {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {



                if(sID.equals(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME))))
                {

                    String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    student = new Student(studentId, name,id);
                    studentList.add(student);

                }



                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }
////////////////////// This Method Will Work for Search Student By his/her Email-Address /////////////////////////////////
    public ArrayList<Student> SearchByEmail(String email) {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                if(email.equals(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_EMAIL))))
                {

                    String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    student = new Student(studentId, name,id);
                    studentList.add(student);

                }



                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }

////////////////////// This Method Will Work for Search Student By his/her Department /////////////////////////////////

    public ArrayList<Student> SearchByDepartment(String dept) {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                if(dept.equals(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_DEPT))))
                {

                    String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    student = new Student(studentId, name,id);
                    studentList.add(student);

                }



                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }
////////////////////// This Method Will Work for Search Student By his/her Student Cell-Phone Number /////////////////////////////////

    public ArrayList<Student> SearchByCellPhone(String cellPhone) {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                if(cellPhone.equals(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CELLPHONE))))
                {

                    String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    student = new Student(studentId, name,id);
                    studentList.add(student);

                }



                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }
////////////////////// This Method Will Work for Search Student By his/her Weaver Range /////////////////////////////////
    public ArrayList<Student> SearchByWeaver(int lowWeaver , int highWeaver) {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int weaver = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_WEAVER)));

                if((weaver<=highWeaver)&&(lowWeaver<=weaver))
                {

                    String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    student = new Student(studentId, name,id);
                    studentList.add(student);

                }



                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }
////////////////////// This Method Will Work for Search Student By his/her Balance Range /////////////////////////////////

    public ArrayList<Student> SearchByBalance(long lowBalance , long highBalance) {

        ArrayList<Student> studentList = new ArrayList<>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_STUDENT_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {

            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                long balance = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_BALANCE)));

                if((balance<=highBalance)&&(lowBalance<=balance))
                {

                    String studentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    int id= cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    student = new Student(studentId, name,id);
                    studentList.add(student);

                }



                cursor.moveToNext();

            }
            cursor.close();
            this.close();
        }
        return studentList;
    }



}
