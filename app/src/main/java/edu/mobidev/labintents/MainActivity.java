package edu.mobidev.labintents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonEdit;
    Button buttonShare;
    TextView tvNote;
    final int REQUEST_EDIT_NOTE = 0;
    static final String INTENT_EXTRA_CURRENT_NOTE = "current_note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonEdit = (Button) findViewById(R.id.button_edit);
        buttonShare = (Button) findViewById(R.id.button_share);

        tvNote = (TextView) findViewById(R.id.tv_note);
        buttonEdit.setOnClickListener(goToEditNote);
        buttonShare.setOnClickListener(shareNote);
    }

    View.OnClickListener goToEditNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            i.setClass(getBaseContext(), EditNote.class);
            String currentNote = tvNote.getText().toString();
            i.putExtra(INTENT_EXTRA_CURRENT_NOTE, currentNote);
            startActivityForResult(i, REQUEST_EDIT_NOTE);
        }
    };

    View.OnClickListener shareNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            String note = tvNote.getText().toString();
            i.putExtra(Intent.EXTRA_TEXT, note);
            startActivity(Intent.createChooser(i, "Where do you want to share this text?"));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_EDIT_NOTE && resultCode == RESULT_OK){
            String newNote = data.getExtras().getString(EditNote.INTENT_EXTRA_NEW_NOTE);
            tvNote.setText(newNote);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
