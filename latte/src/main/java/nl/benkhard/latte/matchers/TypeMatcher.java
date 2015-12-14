package nl.benkhard.latte.matchers;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by tcbenkhard on 10/12/15.
 */
public class TypeMatcher {
    public static Matcher<View> ofClass(final Class c) {
        return new Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                return c.isInstance(item);
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {
                mismatchDescription.appendText("Item is not of class "+c.getClass().getName());
            }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Match object to class");
            }
        };
    }
}
