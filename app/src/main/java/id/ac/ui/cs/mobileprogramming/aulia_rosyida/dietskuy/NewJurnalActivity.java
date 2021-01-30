package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class NewJurnalActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_jurnal);

        b1 = (Button) findViewById(R.id.button_add_category);
        b1.setOnClickListener(view -> {
            Intent intent = new Intent(NewJurnalActivity.this, RoomDatabaseCategory.class);
            startActivity(intent);
        });

        b2 = (Button) findViewById(R.id.button_quote);
        editText = (EditText) findViewById(R.id.edit_quote);
        textView = (TextView) findViewById(R.id.textView_quote);
        b2.setOnClickListener(this);
    }

    public native int getTxtLen(String txt);

    private void enterText(){
        String txt2 = editText.getText().toString();
        int txtLen;
        txtLen = getTxtLen(txt2);
        textView.setText("text len: " + txtLen +" - " +txt2);
    }
    @Override
    public void onClick(View view) {
        if(view == b2){
            enterText();
        }
    }
}

