package nl.benkhard.butterknifetest.model;

import android.provider.ContactsContract;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcbenkhard on 04/12/15.
 */
public class Contact extends SugarRecord<Contact> implements Serializable{
    private String firstname;
    private String lastname;
    private List<PhoneDetail> phonenumbers;

    public Contact() {
        phonenumbers = new ArrayList<PhoneDetail>();
    }

    public Contact(String firstname, String lastname) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public List<PhoneDetail> getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(List<PhoneDetail> phonenumbers) {
        this.phonenumbers = phonenumbers;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullName() {
        return lastname + ", " + firstname;
    }

    public void addPhoneNumber(PhoneDetail detail) {
        phonenumbers.add(detail);
    }
}
