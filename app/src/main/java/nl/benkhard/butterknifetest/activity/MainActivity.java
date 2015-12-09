package nl.benkhard.butterknifetest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.benkhard.butterknifetest.R;
import nl.benkhard.butterknifetest.confirmation.ConfirmedCallback;
import nl.benkhard.butterknifetest.confirmation.DeleteContactConfirmation;
import nl.benkhard.butterknifetest.database.ContactHelper;
import nl.benkhard.butterknifetest.listadapter.ContactAdapter;
import nl.benkhard.butterknifetest.model.Contact;

public class MainActivity extends Activity implements ConfirmedCallback{
    private static final String TAG = "MainActivity";
    private static final String CONTEXT_DELETE = "Delete contact";
    private List<Contact> contacts;
    private Contact selectedContact;

    @Bind(R.id.edit_search_phone)
    EditText number;

    @Bind(R.id.contact_list)
    ListView list;

    @Bind(R.id.btn_create_contact)
    TextView createContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Layout and mask on phone field
        number.setGravity(Gravity.CENTER_HORIZONTAL);
        number.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        number.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                createContact.setVisibility(number.getText().length() > 0 ? View.VISIBLE : View.GONE);
                return false;
            }
        });

        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.contact_list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            selectedContact = contacts.get(info.position);
            menu.setHeaderTitle(selectedContact.getFullName());
            menu.add(Menu.NONE, 0, 0, CONTEXT_DELETE);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String action = item.getTitle().toString();
        switch (action) {
            case CONTEXT_DELETE:
                DeleteContactConfirmation.buildDialog(this, this, selectedContact).show();
                break;
            default:
                break;

        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateContactList();
        resetFields();
    }

    @OnClick(R.id.btn_create_contact)
    public void createContact() {
        Log.i(TAG, "Starting CreateContactActivity");
        Intent intent = CreateContactActivity.createIntent(this, number.getText().toString());
        startActivityForResult(intent, 0);
    }

    private void resetFields() {
        number.setText("");
        createContact.setVisibility(View.GONE);
    }

    private void updateContactList() {
        contacts = Contact.listAll(Contact.class);
        ContactAdapter adapter = new ContactAdapter(this, contacts.toArray(new Contact[contacts.size()]));
        this.list.setAdapter(adapter);
    }

    @Override
    public void confirmed() {
        updateContactList();
    }
}
