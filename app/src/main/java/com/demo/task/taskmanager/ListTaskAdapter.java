package com.demo.task.taskmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.demo.task.taskmanager.entities.Task;

import java.util.List;

/**
 * Created by Quy Duc on 12/20/2017.
 */

public class ListTaskAdapter extends RecyclerView.Adapter<ListTaskAdapter.MyViewHolder> {
    private List<Task> listData;
    private Context context;

    public ListTaskAdapter(List<Task> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    public List<Task> getListData() {
        return listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_task, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = listData.get(position);
        holder.onBlind(task,position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cbStatus;
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final View ivMore;

        public MyViewHolder(View view) {
            super(view);
            cbStatus = view.findViewById(R.id.cbStatus);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvDescription = view.findViewById(R.id.tvDescription);
            ivMore = view.findViewById(R.id.ivMore);
        }

        public void onBlind(final Task task, final int position){
            cbStatus.setChecked(task.isDone());
            if (cbStatus.isChecked()){
                tvTitle.setPaintFlags(tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                tvTitle.setPaintFlags(0);
            }
            cbStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.setDone(cbStatus.isChecked());
                    notifyItemChanged(position);
                }
            });
            tvTitle.setText(task.getTitle());
            if (task.getDescription() == null || task.getDescription().length() == 0) {
                tvDescription.setVisibility(View.GONE);
            }else{
                tvDescription.setVisibility(View.VISIBLE);
                tvDescription.setText(task.getDescription());
            }
            ivMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Creating the instance of PopupMenu
                    PopupMenu popup = new PopupMenu(context, ivMore);
                    //Inflating the Popup using xml file
                    popup.getMenuInflater().inflate(R.menu.menu_item, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.mnDelete){
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage(R.string.dialog_confirm_delete)
                                        .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                MockData.removeTaskByID(task.getTaskID());
                                                listData.remove(task);
                                                notifyItemRemoved(position);
                                            }
                                        })
                                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });
                                // Create the AlertDialog object and return it
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            } else if (item.getItemId() == R.id.mnEdit){
                                NewTaskDialog newTaskDialog = new NewTaskDialog(context, task.getDeadline(), new NewTaskDialog.ICallback() {
                                    @Override
                                    public void onNewTask(Task task) {
                                        MockData.update(task);
//                                        mAdapter.getListData().add(task);
                                        notifyDataSetChanged();
                                    }
                                },task);
                                newTaskDialog.show();
                            }
                            return true;
                        }
                    });

                    popup.show();//showing popup menu
                }
            });
        }
    }

}
