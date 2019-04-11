package com.example.t2_spinnerincustomdialog;

import android.content.DialogInterface;
import android.print.PrinterId;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button ShowDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ShowDialog = (Button) findViewById(R.id.btnShowAlertDialog);

        ShowDialog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                // create an AlertDialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                // create a View to  inflate the layout we just created -  (resource file, we dont have a view group so we will leave it null - root= null
                View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                //set a title for the AlertDialog
                mBuilder.setTitle(R.string.alert_title);
                final Spinner mySpinner=(Spinner) mView.findViewById(R.id.spinner);
                //by default the user can dismiss the alertDialog by tuching the app screen
                //but, if we wont the user to exit the alert Dialog only from the dismiss button,
                // we will write this line
                mBuilder.setCancelable(false);

                ArrayAdapter<String> mAdapter= new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,//- the title on the spinner on the toolbar will be the first item of the spinner
                        // android.R.layout- this will color the text of the spinner on the toolbar in defult color- black
                        // R.layout.custom_spinner_item, // this layout we just created is for the color white
                        getResources().getStringArray(R.array.countries));

                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter(mAdapter);


                // set a buttons for the AlertDialog
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  dialog.dismiss(); //  dismiss is to hide or get rid of dialog
                        if(!mySpinner.getSelectedItem().toString().equalsIgnoreCase("Please select a value")) {
                            //create a Toast message for the user
                            Toast.makeText(MainActivity.this,
                                    mySpinner.getSelectedItem().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });



                //set the dialog view (mView) for the mBulider
                mBuilder.setView(mView);
                AlertDialog dialog= mBuilder.create();
                dialog.show();


            }
        });
    }
}
