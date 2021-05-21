package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable {
    private String name_of_task;
    private boolean task_done;
    private long creation_time;

    TodoItem(String name_of_task, boolean task_done){
        this.name_of_task = name_of_task;
        this.task_done = task_done;
        this.creation_time = new Date().getTime();
    }

    public String getName_of_task(){
        return this.name_of_task;
    }

    public Boolean getTask_done(){
        return this.task_done;
    }

    public long getCreation_time(){
        return this.creation_time;
    }

    public void setName_of_task(String new_name){
        this.name_of_task = new_name;
    }

    public void setTask_done(Boolean is_done){
        this.task_done = is_done;
    }
}
