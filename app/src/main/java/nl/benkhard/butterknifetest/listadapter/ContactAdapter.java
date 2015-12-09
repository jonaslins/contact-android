package nl.benkhard.butterknifetest.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import butterknife.Bind;
import nl.benkhard.butterknifetest.R;
import nl.benkhard.butterknifetest.model.Contact;

/**
 * Created by tcbenkhard on 04/12/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    private final Contact[] values;
    @Bind(R.id.lb_contact_name) TextView contactName;

    public ContactAdapter(Context context, Contact[] objects) {
        super(context, 0, objects);
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact user = values[position];
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.lb_contact_name);
        name.setText(user.getFullName());
        return convertView;
    }
}
