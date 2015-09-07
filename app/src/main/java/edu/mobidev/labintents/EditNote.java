package edu.mobidev.labintents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNote extends AppCompatActivity {

    EditText etNote;
    Button buttonSubmit;
    Button buttonCancel;
    static final String INTENT_EXTRA_NEW_NOTE = "new_note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        etNote = (EditText) findViewById(R.id.et_note);
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        buttonCancel = (Button) findViewById(R.id.button_cancel);

        buttonSubmit.setOnClickListener(submitNote);
        buttonCancel.setOnClickListener(cancel);
    }

    View.OnClickListener submitNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            String newNote = etNote.getText().toString();
            returnIntent.putExtra(INTENT_EXTRA_NEW_NOTE, newNote);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    };

    View.OnClickListener cancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        String currentNote = getIntent().getExtras().getString(MainActivity.INTENT_EXTRA_CURRENT_NOTE);
        etNote.setText(currentNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_post_it, menu);
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
