package com.demo.task.taskmanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.demo.task.taskmanager.entities.Task;

import java.io.Serializable;

public class MainTaskActivity extends AppCompatActivity {

    public static final int REQUEST_NEW_TASK = 100;
    private TextView tvTitle;
    private TextView tvDescription;
    private View llMain;
    private View ivDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainTaskActivity.this, NewTaskActivity.class), REQUEST_NEW_TASK);
            }
        });
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        llMain = findViewById(R.id.llMain);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        ivDelete = findViewById(R.id.ivDelete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainTaskActivity.this);
                builder.setMessage(R.string.dialog_confirm_delete)
                        .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tvDescription.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                // Create the AlertDialog object and return it
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainTaskActivity.REQUEST_NEW_TASK){
            if (resultCode == Activity.RESULT_OK){
                llMain.setVisibility(View.VISIBLE);
                Task task = (Task) data.getSerializableExtra("Task");
                tvTitle.setText(task.getTitle());
                if (task.getDescription() != null && task.getDescription().length() > 0) {
                    tvDescription.setText(task.getDescription());
                    tvDescription.setVisibility(View.VISIBLE);
                }else{
                    tvDescription.setVisibility(View.GONE);
                }
            }
        }
    }
}
