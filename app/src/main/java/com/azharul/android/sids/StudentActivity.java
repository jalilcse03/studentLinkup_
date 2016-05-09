package com.azharul.android.sids;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class StudentActivity extends AppCompatActivity {
    DataSource dataSource;

    TextView textViewID;
    TextView textViewName;
    TextView textViewDepartment;
    TextView textViewWeaver;
    TextView textViewBalance;
    TextView textViewCellPhone;
    TextView textViewEmail;
    TextView textViewCredit;
    ImageView imageView;
    Button dob_bt;
    Spinner bloodGroup_Com;
    RadioGroup radioGroup;
    String YY,MM,DD;



    int showKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        textViewID = (TextView) findViewById(R.id.textViewID);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDepartment = (TextView) findViewById(R.id.textViewDepartment);
        textViewWeaver = (TextView) findViewById(R.id.textViewWeaver);
        textViewBalance = (TextView) findViewById(R.id.textViewBalance);
        textViewCellPhone = (TextView) findViewById(R.id.textViewCellPhone);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewCredit = (TextView) findViewById(R.id.textViewCredits);
        LinearLayout li = (LinearLayout) findViewById(R.id.linearLayout);
        imageView = (ImageView) findViewById(R.id.imageStudent);
        dob_bt=(Button)findViewById(R.id.dob_bt);
        bloodGroup_Com=(Spinner)findViewById(R.id.bloodGroup_Com);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        dataSource = new DataSource(this);
        showKey = getIntent().getIntExtra("key", 0);

        if (showKey % 2 == 1) {
            li.setBackgroundColor(Color.parseColor("#627583"));
            imageView.setImageResource(R.drawable.three);

        } else {
            li.setBackgroundColor(Color.parseColor("#8AB8E0"));
            imageView.setImageResource(R.drawable.image);
        }

        textViewName.setText(":  " + dataSource.getStudent(showKey).getStudentName());
        textViewID.setText(":  " + dataSource.getStudent(showKey).getId());

        textViewDepartment.setText(":  " + dataSource.getStudent(showKey).getDepartment());
        textViewWeaver.setText(":  " + dataSource.getStudent(showKey).getWeaver() + " %");
        textViewBalance.setText(":  " + dataSource.getStudent(showKey).getBalance() + " TK");
        textViewCellPhone.setText(dataSource.getStudent(showKey).getCellPhone());
        textViewEmail.setText(dataSource.getStudent(showKey).getEmail());
        textViewCredit.setText(":  " + dataSource.getStudent(showKey).getCredit() + " Hours");
        String date=dataSource.getStudent(showKey).getDob();
        String year=date.substring(0, 4);
        String month=date.substring(4, 6);
        String day=date.substring(6, 8);
        YY=year;
        MM=month;
        DD=day;
        dob_bt.setText(day+"/"+month+"/"+year);

        if(dataSource.getStudent(showKey).getGender().equals("Male"))
        {
            radioGroup.check(((RadioButton)radioGroup.getChildAt(0)).getId());
        }
        else{
            radioGroup.check(((RadioButton)radioGroup.getChildAt(1)).getId());
        }
        // spinner value  set
        ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)bloodGroup_Com.getAdapter();
        bloodGroup_Com.setSelection(array_spinner.getPosition(dataSource.getStudent(showKey).getBloodGroup()));


    }

    public void buttonDelete(View view) {
        dataSource.deleteStudent(showKey);
        Intent intent = new Intent(this, StudentInformationActivity.class);
        intent.putExtra("deleteKey", 1);
        startActivity(intent);
    }

    public void buttonUpdate(View view) {
        Intent intent = new Intent(this, StudentFormActivity.class);
        intent.putExtra("updateKey", 1);
        intent.putExtra("showKey", showKey);
        startActivity(intent);
    }

    public void buttonCall(View view) {
        String number = dataSource.getStudent(showKey).getCellPhone();
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:" + number));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    public void buttonMail(View view)
    {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",dataSource.getStudent(showKey).getEmail(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Notice");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Student");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
    public void dob_select(View view) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date
        DatePickerDialog datePicker = new DatePickerDialog(this, datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        datePicker.setCancelable(false);
        datePicker.setTitle("Select the date");
        datePicker.show();

    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {

            String year1 = String.valueOf(selectedYear);
            String  month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);

            if(month1.length()==1)
                month1="0"+month1;
            if(day1.length()==1)
                day1="0"+day1;

            YY=year1;
            MM=month1;
            DD=day1;
            dob_bt.setText(DD+"/"+MM+"/"+YY);
            //Toast.makeText(getActivity(), day1+"/"+month1+"/"+year1, Toast.LENGTH_SHORT).show();

        }
    };
}
