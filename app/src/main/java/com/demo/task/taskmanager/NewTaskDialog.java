package com.demo.task.taskmanager;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.task.taskmanager.entities.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Quy Duc on 12/20/2017.
 */

public class NewTaskDialog extends Dialog {

    private Task task;
    private ICallback callback;

    private final TextView tvDate;
    private final EditText etTitle;
    private final EditText etDescription;
    private final View btnAccept;
    private Date date;


    public NewTaskDialog(@NonNull Context context, final Date date, ICallback callback, Task task) {
        super(context, android.R.style.Theme_DeviceDefault_Light);
        this.date = date;
        this.callback = callback;
        setTitle(R.string.new_task);
        setContentView(R.layout.dialog_new_task);
        tvDate = findViewById(R.id.tvDate);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnAccept = findViewById(R.id.btnAccept);
        tvDate.setText(Utils.convertDateToStringNoTime(date));
        if (task == null) {
            task = new Task();
            task.setTaskID(UUID.randomUUID().toString());
        } else {
            etTitle.setText(task.getTitle());
            etDescription.setText(task.getDescription());
        }
        final Task finalTask = task;
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalTask.setDeadline(date);
                finalTask.setTitle(etTitle.getText().toString());
                finalTask.setDescription(etDescription.getText().toString());
                NewTaskDialog.this.callback.onNewTask(finalTask);
                dismiss();
            }
        });
    }

    public interface ICallback {
        public void onNewTask(Task task);
    }

}
