package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TodoItemsHolderImpl implements TodoItemsHolder {

  private static TodoItemsHolderImpl instance;
  private ArrayList<TodoItem> all_items;

  TodoItemsHolderImpl(){
    all_items = new ArrayList<>();
  }

  @Override
  public void sort_by_creation_time(){
    Collections.sort(this.all_items, ((o1, o2) -> {
      if (o1.getTask_done() == o2.getTask_done()) return
              Long.compare(o1.getCreation_time(), o2.getCreation_time());
      else return o1.getTask_done() ? 1 : -1;
    }));
  }


  public static TodoItemsHolderImpl getInstance(){
    if (instance == null) instance = new TodoItemsHolderImpl();
    return instance;
  }
  @Override
  public List<TodoItem> getCurrentItems() { return this.all_items; }

  @Override
  public void addNewInProgressItem(String description) {
    this.all_items.add(new TodoItem(description, false));
    this.sort_by_creation_time();
  }

  @Override
  public void markItemDone(TodoItem item) {
    for (TodoItem i: this.all_items){
      if (i.equals(item)){
        i.setTask_done(true);
        break;
      }
    }
    this.sort_by_creation_time();
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for (TodoItem i: this.all_items){
      if (i.equals(item)){
        i.setTask_done(false);
        break;
      }
    }
    this.sort_by_creation_time();
  }

  @Override
  public void deleteItem(TodoItem item) {
    all_items.remove(item);
  }


  @Override
  public void setItems(ArrayList<TodoItem> all_items){
    this.all_items = all_items;
  }


}

