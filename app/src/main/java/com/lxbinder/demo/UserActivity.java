package com.lxbinder.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by leixun on 17/11/15.
 */

public class UserActivity extends Activity implements ServiceConnection,View.OnClickListener{

    private android.widget.Button startBind;
    private android.widget.Button unBind;
    private android.widget.Button addUserBtn;
    private android.widget.Button printUsers;
    private android.widget.TextView userShowTv;
    private long userIdIndex = 0;
    private IUserAIDL mBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.userShowTv = (TextView) findViewById(R.id.userShowTv);
        this.printUsers = (Button) findViewById(R.id.printUsers);
        this.addUserBtn = (Button) findViewById(R.id.addUserBtn);
        this.unBind = (Button) findViewById(R.id.unBind);
        this.startBind = (Button) findViewById(R.id.startBind);

        printUsers.setOnClickListener(this);
        addUserBtn.setOnClickListener(this);
        startBind.setOnClickListener(this);
        unBind.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.printUsers:
                printUsers();
                break;
            case R.id.addUserBtn:
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
        mBinder = UserStub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        unbindService(this);
        mBinder = null;
    }

    private void startBind(){
        Intent intent = new Intent(this,UserService.class);
        this.bindService(intent,this, Context.BIND_AUTO_CREATE);
    }

    private void printUsers(){
        if(mBinder!=null){
            try{
                List<User> users = mBinder.getUserList();
                StringBuilder sb = new StringBuilder();
                for(int i = 0 ;i< users.size();i++){
                    sb.append("索引："+i+" userId: "+users.get(i).getUserId()+" 用户名 "+users.get(i).getUserName()+"\n");
                }
                userShowTv.setText(sb.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void addBook(){
        if(mBinder!=null){
            try{
                mBinder.addUser(userIdIndex++,"用户"+userIdIndex);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void toast(String txt){
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }
}
