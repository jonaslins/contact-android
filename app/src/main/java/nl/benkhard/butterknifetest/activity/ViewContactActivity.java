package nl.benkhard.butterknifetest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.benkhard.butterknifetest.R;
import nl.benkhard.butterknifetest.model.Contact;

/**
 * Created by tcbenkhard on 09/12/15.
 */
public class ViewContactActivity extends AppCompatActivity {
    public static final String CONTACT_ID = "id";
    private Contact contact;

    @Bind(R.id.txt_contact_name)
    TextView contactname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        ButterKnife.bind(this);

        contact = (Contact) getIntent().getSerializableExtra(CONTACT_ID);

        contactname.setText(contact.getFullName());
    }

    public static Intent createIntent(Context context, Contact contact) {
        Intent intent = new Intent(context, ViewContactActivity.class);
        intent.putExtra(CONTACT_ID, contact);
        return intent;
    }
}
