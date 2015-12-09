package nl.benkhard.butterknifetest.confirmation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import nl.benkhard.butterknifetest.model.Contact;

/**
 * Created by tcbenkhard on 08/12/15.
 */
public class DeleteContactConfirmation {

    private DeleteContactConfirmation() {

    }

    public static AlertDialog buildDialog(Context context, final ConfirmedCallback callback, final Contact contact) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Delete contact?")
                .setMessage("Do you really want to delete the contact "+contact.getFirstname()+" "+contact.getLastname())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        contact.delete();
                        callback.confirmed();
                    }})
                .setNegativeButton(android.R.string.no, null).create();
        return dialog;
    }
}
