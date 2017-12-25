package com.demo.task.taskmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.task.taskmanager.entities.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListTaskFragment extends Fragment {


    private RecyclerView rcvListTask;
    private ListTaskAdapter mAdapter;
    private int position;
    private Date currentDate;
    private List<Task> listData;

    public ListTaskFragment() {
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_task, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListTask = view.findViewById(R.id.rcvListTask);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rcvListTask.setLayoutManager(mLayoutManager);
        listData = new ArrayList<>();
        //Lấy dữ liệu theo ngày
        listData.addAll(MockData.getListTask(currentDate));
        mAdapter = new ListTaskAdapter(listData, getActivity());
        rcvListTask.setAdapter(mAdapter);
        view.findViewById(R.id.ivNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTaskDialog newTaskDialog = new NewTaskDialog(getActivity(), currentDate, new NewTaskDialog.ICallback() {
                    @Override
                    public void onNewTask(Task task) {
                        MockData.getTasks().add(task);
                        mAdapter.getListData().add(task);
                        mAdapter.notifyDataSetChanged();
                    }
                }, null);
                newTaskDialog.show();
            }
        });
    }
}
