package com.example.todocomponent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TodoListAdapter adapter;
    private TodoModel todoModel;



    public TodoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodoListFragment newInstance(String param1, String param2) {
        TodoListFragment fragment = new TodoListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_todo_list, container, false);
        recyclerView = view.findViewById(R.id.todo_list_rv);

        return view;


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.todo_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new_todo:

                Todo todo = new Todo();
                todo.setDetail("");
                TodoModel.getInstance().addTodo(todo);

                Intent intent = TodoPagerActivity.makeIntent(getActivity(), todo.getId());
                //              Intent intent = TodoActivity.newIntent(getActivity(), todo.getId());
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoModel = TodoModel.getInstance();
        adapter = new TodoListAdapter(todoModel.getTodosList(), getActivity());
        recyclerView.setAdapter(adapter);
    }
}