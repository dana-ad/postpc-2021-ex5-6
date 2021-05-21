package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  public TodoItemsHolder holder = null;
  private ItemsAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (holder == null) {
      holder = new TodoItemsHolderImpl();
    }
    adapter = new ItemsAdapter(this.holder);
    FloatingActionButton create_button = findViewById(R.id.buttonCreateTodoItem);
    EditText edit_task_text = findViewById(R.id.editTextInsertTask);
    edit_task_text.setText("");
    RecyclerView recycler = findViewById(R.id.recyclerTodoItemsList);
    recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recycler.setAdapter(adapter);


    create_button.setOnClickListener(v -> {
      String task_description = edit_task_text.getText().toString();
      if (!task_description.equals("")) {
        this.holder.addNewInProgressItem(task_description);
        edit_task_text.setText("");
        this.adapter.notifyDataSetChanged();
      }
    });
  }
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    EditText editText = findViewById(R.id.editTextInsertTask);
    outState.putString("edit_text", editText.getText().toString());
  }
}



