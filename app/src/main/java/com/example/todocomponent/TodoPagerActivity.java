package com.example.todocomponent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.UUID;

public class TodoPagerActivity extends AppCompatActivity {

    private static final String EXTRA_TODO_ID = "todo_id";

    private ViewPager2 viewPager2;

    public static Intent makeIntent(Context context, UUID todoId){
        Intent intent = new Intent(context, TodoPagerActivity.class);
        intent.putExtra(EXTRA_TODO_ID, todoId);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_pager);
        viewPager2 = findViewById(R.id.view_pager);
        List<Todo> todoList = TodoModel.getInstance().getTodosList();

        Intent intent = getIntent();
        UUID todoId = (UUID) intent.getSerializableExtra(EXTRA_TODO_ID);



        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Todo todo = todoList.get(position);
                return TodoFragment.newInstance(todo.getId());
            }

            @Override
            public int getItemCount() {
                return todoList.size();
            }
        });


    }
}