package nl.benkhard.butterknifetest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import nl.benkhard.butterknifetest.R;
import nl.benkhard.butterknifetest.model.Contact;
import nl.benkhard.butterknifetest.model.PhoneDetail;

public class CreateContactActivity extends AppCompatActivity {
    @Bind(R.id.txt_contact_firstname) EditText firstname;
    @Bind(R.id.txt_contact_lastname) EditText lastname;
    @Bind(R.id.txt_contact_phone) EditText phoneNumber;
    @Bind(R.id.btn_save_contact) TextView btnCreateContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        ButterKnife.bind(this);
        String phone = getIntent().getStringExtra("NUMBER");
        phoneNumber.setText(phone);
    }

    public static Intent createIntent(Context context, String phonenumber) {
        Intent intent = new Intent(context, CreateContactActivity.class);
        intent.putExtra("NUMBER", phonenumber.replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
        return intent;
    }

    @OnClick(R.id.btn_save_contact)
    public void createContact() {
        Contact contact = new Contact(firstname.getText().toString(), lastname.getText().toString());
        contact.addPhoneNumber(new PhoneDetail("Test", phoneNumber.getText().toString()));
        contact.save();
        finish();
    }
}
