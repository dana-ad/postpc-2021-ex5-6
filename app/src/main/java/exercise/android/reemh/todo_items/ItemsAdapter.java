package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAdapter extends RecyclerView.Adapter<TodoItemHolder> {

    TodoItemsHolder itemsHolder;
    Context context;

    ItemsAdapter(TodoItemsHolder holder){
        this.itemsHolder = holder;
    }

    @NonNull
    @Override
    public TodoItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent,
                false);
        return new TodoItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TodoItemHolder holder, int position) {
        TodoItem item = itemsHolder.getCurrentItems().get(position);
        holder.task_description.setText(item.getName_of_task());
        holder.checkBox.setChecked(item.getTask_done());

        if (item.getTask_done()) {
            holder.task_description.setPaintFlags(holder.task_description.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else holder.task_description.setPaintFlags(0);

        holder.checkBox.setOnClickListener(view -> {
            if (holder.checkBox.isChecked())
                this.itemsHolder.markItemDone(item);
            else
                this.itemsHolder.markItemInProgress(item);
            notifyDataSetChanged();
        });

//        holder.editTask.setOnClickListener(view -> {
//            Intent edit_flag = new Intent(context, EditTaskActivity.class);
//            edit_flag.putExtra("cur_item", item);
//            this.context.startActivity(edit_flag);
//        });

        holder.removeTask.setOnClickListener(view -> {
            itemsHolder.deleteItem(item);
            notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return itemsHolder.getCurrentItems().size();
    }
}
