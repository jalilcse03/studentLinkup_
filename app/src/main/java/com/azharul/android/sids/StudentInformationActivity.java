package com.azharul.android.sids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentInformationActivity extends AppCompatActivity {

    ListView listViewStudentInformationList;
    Spinner spinner;
    EditText editTextSearch;

    private DataSource dataSource;
    CustomAdapter adapter;
    ArrayList<Student> studentList;

    int key = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        key = getIntent().getIntExtra("deleteKey",0) ;

        listViewStudentInformationList = (ListView) findViewById(R.id.listViewStudentList);
        spinner =(Spinner)findViewById(R.id.spinItem);
        editTextSearch =(EditText)findViewById(R.id.editTextSearch);




        dataSource = new DataSource(this);

        adapter = new CustomAdapter(this, dataSource.GetAllStudent());
        listViewStudentInformationList.setAdapter(adapter);

        if(key == 1)
        {
            adapter.notifyDataSetChanged();
            key=0;
        }

        listViewStudentInformationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(StudentInformationActivity.this, StudentActivity.class);

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int key = Integer.parseInt(((TextView) view.findViewById(R.id.textViewRowKey)).getText().toString());
                intent.putExtra("key", key);
                startActivity(intent);

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String spinnerSelect = spinner.getSelectedItem().toString();
                if(spinnerSelect.equals("Select"))
                {
                    editTextSearch.setHint("Search Here");
                    editTextSearch.setInputType(InputType.TYPE_CLASS_TEXT);

                }
                else if(spinnerSelect.equals("By Student ID"))
                {
                    editTextSearch.setHint("Search-By Student-ID");
                    editTextSearch.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                }
                else if(spinnerSelect.equals("By Student Name"))
                {
                    editTextSearch.setHint("Search-By Student-Name");
                    editTextSearch.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);

                }
                else if(spinnerSelect.equals("By Dept"))
                {
                    editTextSearch.setHint("Search-By Department");
                    editTextSearch.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                }
                else if(spinnerSelect.equals("By Weaver"))
                {
                    editTextSearch.setHint("Low-Weaver,High-Weaver");
                }
                else if(spinnerSelect.equals("By Balance"))
                {
                    editTextSearch.setHint("Low-Balance,High-Balance");
                }
                else if(spinnerSelect.equals("By E-mail"))
                {
                    editTextSearch.setHint("Search-By Email");
                    editTextSearch.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                }
                else if(spinnerSelect.equals("By Cell-Phone"))
                {
                    editTextSearch.setHint("Search-By Cell-Phone");
                    editTextSearch.setInputType(InputType.TYPE_CLASS_PHONE);
                }

                if(spinnerSelect.equals("By Cell-Phone")||spinnerSelect.equals("By E-mail")||spinnerSelect.equals("By Balance")||spinnerSelect.equals("By Weaver")||spinnerSelect.equals("By Student Name")||spinnerSelect.equals("Select"))
                {
                    editTextSearch.append("");
                }
                else if(spinnerSelect.equals("By Student ID"))
                {
                    editTextSearch.addTextChangedListener(new TextWatcher() {//auto-written part for Student ID editText
                        int len = 0;

                        @Override
                        public void afterTextChanged(Editable s) {
                            String str = editTextSearch.getText().toString();
                            if (str.length() == 4 && len < str.length() || str.length() == 7 && len < str.length() || str.length() == 10 && len < str.length()) {//len check for backspace
                                editTextSearch.append("-");
                            }
                        }

                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                            String str = editTextSearch.getText().toString();
                            len = str.length();
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }


                    });
                }
                else if(spinnerSelect.equals("By Dept"))
                {
                    editTextSearch.addTextChangedListener(new TextWatcher() { //auto-written part for Department name editText
                        int len = 0;

                        @Override
                        public void afterTextChanged(Editable s) {
                            String str = editTextSearch.getText().toString();
                            if (str.length() == 1 && len < str.length() || str.length() == 3 && len < str.length()) {//len check for backspace
                                editTextSearch.append(".");
                            }
                        }

                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                            String str = editTextSearch.getText().toString();
                            len = str.length();
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }


                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




    }

    public void  AddStudent(View view)// activator of student form activity
    {
        final Intent intent = new Intent(this,StudentFormActivity.class);
        startActivity(intent);


    }

    public  void  ButtonSearch(View view)
    {
        String spinnerSelect = spinner.getSelectedItem().toString();
        if(spinnerSelect.equals("Select"))
        {
            adapter = new CustomAdapter(this, dataSource.GetAllStudent());
            listViewStudentInformationList.setAdapter(adapter);
        }
        else if(spinnerSelect.equals("By Student ID"))
        {
            adapter = new CustomAdapter(this, dataSource.SearchByID(editTextSearch.getText().toString()));
            listViewStudentInformationList.setAdapter(adapter);
        }
        else if(spinnerSelect.equals("By Student Name"))
        {
            adapter = new CustomAdapter(this, dataSource.SearchByName(editTextSearch.getText().toString()));
            listViewStudentInformationList.setAdapter(adapter);
        }
        else if(spinnerSelect.equals("By Dept"))
        {
            adapter = new CustomAdapter(this, dataSource.SearchByDepartment(editTextSearch.getText().toString()));
            listViewStudentInformationList.setAdapter(adapter);
        }
        else if(spinnerSelect.equals("By Weaver"))
        {

            String CurrentString = editTextSearch.getText().toString().trim();
            if(CurrentString==null || CurrentString.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Error Input", Toast.LENGTH_LONG).show();
            }
            else {
                int lowWeaver;
                int highWeaver;
                String[] separated = CurrentString.split(",");

                try {
                    lowWeaver = Integer.parseInt(separated[0].trim());
                    highWeaver = Integer.parseInt(separated[1].trim());
                    adapter = new CustomAdapter(this, dataSource.SearchByWeaver(lowWeaver, highWeaver));
                    listViewStudentInformationList.setAdapter(adapter);
                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(getApplicationContext(), "Error Input", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Input", Toast.LENGTH_LONG).show();
                }
            }

        }
        else if(spinnerSelect.equals("By Balance"))
        {
            String CurrentString = editTextSearch.getText().toString().trim();
            if(CurrentString==null || CurrentString.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Error Input", Toast.LENGTH_LONG).show();
            }
            else
            {
                String[] separated = CurrentString.split(",");
                long lowBalance;
                long highBalance;
                try
                {
                    lowBalance=Long.parseLong( separated[0].trim());
                    highBalance=Long.parseLong(separated[1].trim());
                    adapter = new CustomAdapter(this, dataSource.SearchByBalance(lowBalance, highBalance));
                    listViewStudentInformationList.setAdapter(adapter);
                }
                catch (IndexOutOfBoundsException e)
                {
                    Toast.makeText(getApplicationContext(), "Error Input", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error Input", Toast.LENGTH_LONG).show();
                }



            }

        }
        else if(spinnerSelect.equals("By E-mail"))
        {
            adapter = new CustomAdapter(this, dataSource.SearchByEmail(editTextSearch.getText().toString()));
            listViewStudentInformationList.setAdapter(adapter);
        }
        else if(spinnerSelect.equals("By Cell-Phone"))
        {
            adapter = new CustomAdapter(this, dataSource.SearchByCellPhone(editTextSearch.getText().toString()));
            listViewStudentInformationList.setAdapter(adapter);
        }

    }


}
