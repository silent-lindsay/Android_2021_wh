package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract.TodoNote;
import com.byted.camp.todolist.db.TodoDbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;


public class NoteActivity extends AppCompatActivity {

    private final String TAG = "Tag in Note" ;
    private EditText editText;
    private Button addBtn;
    private RadioGroup radioGroup;
    private AppCompatRadioButton lowRadio;

    private TodoDbHelper dbHelper;
    private SQLiteDatabase database;

    private String filename ;// = getFilesDir().getAbsolutePath()+File.separator+"draft.txt" ;
//    private File draft = new File(getFilesDir().getAbsolutePath()+File.separator+"draft.txt") ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);
        filename = getFilesDir().getAbsolutePath()+File.separator+"draft.txt" ;
        dbHelper = new TodoDbHelper(this);
        database = dbHelper.getWritableDatabase();

        editText = findViewById(R.id.edit_text);
//        OpenDraft( );
        editText.setFocusable(true);
        editText.requestFocus();
        SharedPreferences sp = getPreferences(Activity.MODE_PRIVATE);
        String draftext = sp.getString("Draft","") ;
        editText.setText(draftext);
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }
        radioGroup = findViewById(R.id.radio_group);
        lowRadio = findViewById(R.id.btn_low);
        lowRadio.setChecked(true);

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim(),
                        getSelectedPriority());
                if (succeed) {
//                    ClearDraft();
                    editText.setText(null);
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
//                overridePendingTransition(0,0);
            }
        });
    }
    private void ClearDraft( ){
        new Thread(new Runnable() {
            @Override
            public void run() {
                File draft = new File(filename) ;
                if ( draft.exists() ) {
                    if ( draft.delete() ){
                        Toast.makeText(NoteActivity.this,"Draft clear!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(NoteActivity.this,"Clear failure!",Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(NoteActivity.this,"Clear failure!",Toast.LENGTH_SHORT).show();
            }
        }).start();
    }
    private void OpenDraft(  ){
        new Thread(new Runnable(){
            public void run(){
                File draft = new File(filename) ;
                if (!draft.exists()){
                    try{
                        if ( draft.createNewFile() == false ) throw new Exception();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(NoteActivity.this,"Draft create error",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    FileInputStream in = null ;
                    try{
                        in = new FileInputStream( draft );
                        byte[] input = new byte[1024] ;
                        StringBuffer sb = new StringBuffer();
                        while (in.read(input) != -1) {
                            sb.append(new String(input));
                        }
//                        in.read(input) ;
                        String past = sb.toString();
                        editText.setText(sb);
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(NoteActivity.this,"Draft load error",Toast.LENGTH_SHORT).show();
                    }finally {
                        try{
                            in.close();
                        }catch (Exception e ){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private void SaveDraft(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                File draft = new File(filename) ;
                if (!draft.exists() ) {
                    try{
                        draft.createNewFile();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                FileOutputStream out = null ;
                try{
                    out = new FileOutputStream(draft) ;
                    String res = editText.getText().toString().trim() ;
                    byte[] to = res.getBytes("UTF-8") ;
                    out.write(to);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        out.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sharedPreferences = getPreferences(Activity.MODE_PRIVATE) ;
        sharedPreferences.edit().putString("Draft",editText.getText().toString()).commit();
        super.onDestroy();
//        SaveDraft();
        Log.i(TAG, "onDestroy: ");
        database.close();
        database = null;
        dbHelper.close();
        dbHelper = null;
    }

    private boolean saveNote2Database(String content, Priority priority) {
        // TODO: 2021/7/19 8. 这里插入数据库
        if (database==null || TextUtils.isEmpty(content)) return false;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(TodoNote.COLUMN_STATE,State.TODO.intValue);
        contentValues.put(TodoNote.COLUMN_CONTENT,content);
        contentValues.put(TodoNote.COLUMN_DATE, System.currentTimeMillis());
        contentValues.put(TodoNote.COLUMN_PRIORITY,priority.intValue);
        long rrow = database.insert(TodoNote.TABLE_NAME,null,contentValues) ;
//        Log.i("TAG", "saveNote2Database: insert success"+rrow);
        return rrow != -1 ;
    }

    private Priority getSelectedPriority() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.btn_high:
                return Priority.High;
            case R.id.btn_medium:
                return Priority.Medium;
            default:
                return Priority.Low;
        }
    }
}
