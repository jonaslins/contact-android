package nl.benkhard.butterknifetest.model;

import com.orm.SugarRecord;

/**
 * Created by tcbenkhard on 07/12/15.
 */
public class PhoneDetail extends SugarRecord<PhoneDetail> {
    private String phonenumber;
    private String name;

    public PhoneDetail() {}

    public PhoneDetail(String name, String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
