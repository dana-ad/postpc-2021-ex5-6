package exercise.android.reemh.todo_items;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemHolder extends RecyclerView.ViewHolder {

    CheckBox checkBox;
    TextView task_description;
    ImageButton removeTask;
    //ImageButton editTask;

    public TodoItemHolder(@NonNull View itemView) {
        super(itemView);
        this.checkBox = itemView.findViewById(R.id.check_box_todo);
        this.task_description = itemView.findViewById(R.id.description);
        this.removeTask = itemView.findViewById(R.id.remove_item_btn);
        //this.editTask = itemView.findViewById(R.id.edit_item_btn);
    }
}
