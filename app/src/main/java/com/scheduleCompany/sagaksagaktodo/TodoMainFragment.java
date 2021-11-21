package com.scheduleCompany.sagaksagaktodo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class TodoMainFragment extends Fragment{
    private static final String TAG = "TodoMainFragment";

    Fragment todoFragment;
    EditText inputToDo;
    Context context;

    public static TodoDatabase todoDatabase = null;

    public static TodoMainFragment newInstance(){
        return new TodoMainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TodoFragment()).commit();

        View view = inflater.inflate(R.layout.fragment_todo_main, container, false);

        todoFragment = new TodoFragment();

        Button saveButton = (Button)view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveToDo();
                Toast.makeText(getActivity().getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        openDatabase();

        return view;
    }

    private void setChildTodoFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.container, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }

    private void saveToDo(){
        inputToDo = getActivity().findViewById(R.id.inputToDo);

        //EditText에 적힌 글을 가져오기
        String todo = inputToDo.getText().toString();

        //테이블에 값을 추가하는 sql구문 insert...
        String sqlSave = "insert into " + TodoDatabase.TABLE_Todo + " (TODO) values (" + "'" + todo + "')";

        //sql문 실행
        TodoDatabase database = TodoDatabase.getInstance(context);
        database.execSQL(sqlSave);

        //저장과 동시에 EditText 안의 글 초기화
        inputToDo.setText("");
    }


    public void openDatabase() {
        // open database
        if (todoDatabase != null) {
            todoDatabase.close();
            todoDatabase = null;
        }

        todoDatabase = TodoDatabase.getInstance(getActivity());
        boolean isOpen = todoDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Todo database is open.");
        } else {
            Log.d(TAG, "Todo database is not open.");
        }
    }

    //앱 강제 종료(Android Activity 생명주기)
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (todoDatabase != null) {
            todoDatabase.close();
            todoDatabase = null;
        }
    }



}
