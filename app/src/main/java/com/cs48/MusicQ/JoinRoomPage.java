package com.cs48.MusicQ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinRoomPage extends AppCompatActivity {

    EditText code;
    Button joinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room_page);

        joinButton = (Button) findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinRoomPage.this, QRoomPage.class);

                QRoom qRoom = QRoomStatic.getInstance();

                intent.putExtra("qroom", qRoom) ;
                qRoom.setQLeader(100);
                startActivity(intent);
            }
        });
    }
}
