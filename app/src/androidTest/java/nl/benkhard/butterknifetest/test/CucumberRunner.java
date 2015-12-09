package nl.benkhard.butterknifetest.test;

import android.os.Bundle;
import android.support.test.runner.MonitoringInstrumentation;

import cucumber.api.android.CucumberInstrumentationCore;
import nl.benkhard.butterknifetest.model.Contact;

/**
 * Created by tcbenkhard on 08/12/15.
 */
public class CucumberRunner extends MonitoringInstrumentation {

    private final CucumberInstrumentationCore mInstrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        mInstrumentationCore.create(arguments);
        start();
    }

    public void onStart() {
        super.onStart();

        waitForIdleSync();
        mInstrumentationCore.start();
        Contact.deleteAll(Contact.class);
    }
}
