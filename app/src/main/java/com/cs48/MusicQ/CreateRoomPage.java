package com.cs48.MusicQ;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CreateRoomPage extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button pButton1, pButton2, pButton3, pButton4;
    Button codegen;
    EditText Name, URI, Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room_page);

        findViewsById();
        setListenerForViews();

        btn1 = (Button) findViewById(R.id.btn_pass_obj);
        Name = (EditText) findViewById(R.id.et_name);
//        Code = (EditText) findViewById(R.id.et_code);
        //URI  = (EditText) findViewById(R.id.et_existing_playlist);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, QRoomPage.class);
        List<Integer> members = new ArrayList<>();
        members.add(CurrentUser.id);
        QRoom qroom = new QRoom(members, Name.getText().toString(), Integer.parseInt(Code.getText().toString()), false, new Song(), new QList(), CurrentUser.id);
        qroom.setName(Name.getText().toString());
        qroom.setCode(Integer.parseInt(Code.getText().toString()));

//        qroom.setExistingPlaylistURI(URI.getText().toString());

        intent.putExtra("qroom", qroom);
        startActivity(intent);

    }
    View.OnClickListener mylistner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            {
                Intent intent = new Intent(CreateRoomPage.this, QRoomPage.class);
                List<Integer> members = new ArrayList<>();
                members.add(CurrentUser.id);
                QRoom qroom = new QRoom(members, Name.getText().toString(),
                        Integer.parseInt(Code.getText().toString()), false, new Song(), new QList(), CurrentUser.id);
                qroom.setName(Name.getText().toString());
                qroom.setCode(Integer.parseInt(Code.getText().toString()));
                intent.putExtra("qroom", qroom);
                startActivity(intent);
            }
        }
    };

    private void findViewsById() {

        try {
            /* Get a reference for each button */
            pButton1 = (Button) findViewById(R.id.playlist1);
            pButton2 = (Button) findViewById(R.id.playli2);
            pButton3 = (Button) findViewById(R.id.playlist3);
            pButton4 = (Button) findViewById(R.id.playlist4);
        } catch (NullPointerException exc) {
            exc.printStackTrace();
        }

    }
    private void setListenerForViews() {

        pButton1.setOnClickListener(mylistner);
        pButton2.setOnClickListener(mylistner);
        pButton3.setOnClickListener(mylistner);
        pButton4.setOnClickListener(mylistner);


    }

}
