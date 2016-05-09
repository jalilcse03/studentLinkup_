package com.azharul.android.sids;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Azharul on 3/16/2016.
 */
public class CustomAdapter extends ArrayAdapter<Student> {
    ArrayList<Student> studentList;
    Context context;

///////////////////////// Design Resources Declare here  ///////////////////////////////////////////////


    ImageView ImageView;
    TextView textViewID;
    TextView textViewName;
    TextView textViewKey;

    int key;


/////////////////////////// extends implemented method //////////////////////////////////////////////////


    public CustomAdapter(Context context, ArrayList<Student> studentList) {
        super(context,R.layout.row_view,studentList);
        this.studentList=studentList;
        this.context=context;
    }
///////////////////////////// That generate A view For US /////////////////////////////////////////////////////
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView=layoutInflater.inflate(R.layout.row_view,null);

/////////////////////////// Design Resource is added Here /////////////////////////////////////////////


        textViewKey= (TextView) rowView.findViewById(R.id.textViewRowKey);//DataBase primary key is added here in hidden textView
        textViewID=(TextView)rowView.findViewById(R.id.textViewRowID);//Student-ID will Show Here
        textViewName=(TextView) rowView.findViewById(R.id.textViewRowName);//Student-Name will Show Here
        ImageView =(ImageView) rowView.findViewById(R.id.imageRow); //Student-Image will show Here

///////////////////////////////SET Data By Position Here////////////////////////////////////////////////////////////////////////
        textViewID.setText(studentList.get(position).getId());
        textViewKey.setText(""+studentList.get(position).getKey());
        textViewName.setText(studentList.get(position).getStudentName());

///////////////////////////////// BackGround-Color and Image will Added by the primary KeY//////////////////////////////////////
        key =studentList.get(position).getKey();
        if(key%2==0)
        {
            rowView.setBackgroundColor(Color.parseColor("#8AB8E0"));
            ImageView.setImageResource(R.drawable.image);
        }
        else
        {
            rowView.setBackgroundColor(Color.parseColor("#627583"));
            ImageView.setImageResource(R.drawable.three);
        }

        return rowView;
    }


}
