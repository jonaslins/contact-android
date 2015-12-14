package nl.benkhard.latte.actions;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static nl.benkhard.latte.matchers.TypeMatcher.ofClass;

/**
 * Created by tcbenkhard on 10/12/15.
 */
public class CheckBoxAction {
    public static ViewAction setChecked(final boolean set) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ofClass(android.widget.CheckBox.class);
            }

            @Override
            public String getDescription() {
                return "Set checkbox to "+(set ? "checked" : "unchecked");
            }

            @Override
            public void perform(UiController uiController, View view) {
                android.widget.CheckBox box = (android.widget.CheckBox) view.findViewById(view.getId());
                if((box.isChecked() && !set) || (!box.isChecked() && set)){
                    view.performClick();
                }
            }
        };
    }
}
