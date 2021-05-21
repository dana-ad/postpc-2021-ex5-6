package exercise.android.reemh.todo_items;

import android.content.Context;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)

public class TodoItemsHolderImplTest extends TestCase {

  @Before
  public void initialize_all(){
    ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class);
    activityController.create().visible();
    Context context = activityController.get();
  }
  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_addingTodoItem_then_its_status_isProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem item = holderUnderTest.getCurrentItems().get(0);

    // verify
    assertFalse(item.getTask_done());
  }

  @Test
  public void when_Check_inProgress_TodoItem_then_its_status_isDone(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem item = holderUnderTest.getCurrentItems().get(0);
    item.setTask_done(true);
    // verify
    assertTrue(item.getTask_done());
  }

  @Test
  public void when_Check_done_TodoItem_then_its_status_isDone(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem item = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.markItemDone(item);
    holderUnderTest.markItemInProgress(item);

    // verify
    assertFalse(item.getTask_done());
  }

  @Test
  public void when_deleting_allItems_array_isEmpty(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    TodoItem item = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.deleteItem(item);
    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_deleting_allItems_array_isEmpty_multipleItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("buy groceries");
    holderUnderTest.addNewInProgressItem("read a book");
    holderUnderTest.addNewInProgressItem("call grandpa");
    assertEquals(4, holderUnderTest.getCurrentItems().size());
    TodoItem item1 = holderUnderTest.getCurrentItems().get(0);
    TodoItem item2 = holderUnderTest.getCurrentItems().get(1);
    TodoItem item3 = holderUnderTest.getCurrentItems().get(2);
    TodoItem item4 = holderUnderTest.getCurrentItems().get(3);
    holderUnderTest.deleteItem(item2);
    holderUnderTest.deleteItem(item3);
    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
    holderUnderTest.deleteItem(item1);
    holderUnderTest.deleteItem(item4);
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void deleteItem_That_DoesNot_Exist(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    TodoItem item = new TodoItem("eat", false);

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    holderUnderTest.deleteItem(item);
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }




}