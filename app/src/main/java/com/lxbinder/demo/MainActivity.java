package com.lxbinder.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ServiceConnection{

    private android.widget.Button startBind;
    private android.widget.Button unBind;
    private android.widget.Button addBookBtn;
    private android.widget.Button printBooks;
    private android.widget.TextView bookShowTv;
    private IBookInterface mBinder;
    private int bookIdIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bookShowTv = (TextView) findViewById(R.id.bookShowTv);
        this.printBooks = (Button) findViewById(R.id.printBooks);
        this.addBookBtn = (Button) findViewById(R.id.addBookBtn);
        this.unBind = (Button) findViewById(R.id.unBind);
        this.startBind = (Button) findViewById(R.id.startBind);

        printBooks.setOnClickListener(this);
        addBookBtn.setOnClickListener(this);
        unBind.setOnClickListener(this);
        startBind.setOnClickListener(this);
        findViewById(R.id.jumpBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jumpBtn:
                startActivity(new Intent(this,UserActivity.class));
                break;
            case R.id.printBooks:
                printbooks();
                break;
            case R.id.addBookBtn:
                addBook();
                break;
            case R.id.unBind:
                toast("解绑成功");
                break;
            case R.id.startBind:
                startBind();
                toast("绑定成功");
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mBinder = IBookInterface.Stub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        unbindService(this);
        mBinder = null;
    }

    private void startBind(){
        Intent intent = new Intent(this,BookService.class);
        this.bindService(intent,this, Context.BIND_AUTO_CREATE);
    }

    private void printbooks(){
        if(mBinder!=null){
            try{
                List<Book> books = mBinder.getBook();
                StringBuilder sb = new StringBuilder();
                for(int i = 0 ;i< books.size();i++){
                    sb.append("索引："+i+" bookId: "+books.get(i).getBookId()+" 书名 "+books.get(i).getBookName()+"\n");
                }
                bookShowTv.setText(sb.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void addBook(){
        if(mBinder!=null){
            try{
                mBinder.addBook(bookIdIndex++,"图书"+bookIdIndex);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void toast(String txt){
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }
}
