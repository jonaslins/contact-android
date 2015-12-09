package nl.benkhard.butterknifetest.test;

import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.BeforeClass;
import org.junit.Rule;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.benkhard.butterknifetest.R;
import nl.benkhard.butterknifetest.activity.MainActivity;
import nl.benkhard.butterknifetest.model.Contact;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by tcbenkhard on 08/12/15.
 */
@CucumberOptions(features = "features")
public class MainActivitySteps extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivitySteps(MainActivity activityClass) {
        super(MainActivity.class);
    }

    @Before
    public void setUp() {
        // Reset contacts database
        Contact.deleteAll(Contact.class);

        // Create a test contact
        Contact tester = new Contact("Tester", "Test");
        tester.save();
    }

    @Given("^I have a MainActivity$")
    public void I_have_a_MainActivity() throws Throwable {
        assertNotNull(getActivity());
    }

    @When("^the search bar is empty$")
    public void the_search_bar_is_empty() throws Throwable {
        onView(withId(R.id.edit_search_phone)).check(matches(withText("")));
    }

    @Then("^the create contact button is invisible$")
    public void the_create_contact_button_is_invisible() throws Throwable {
        onView(withId(R.id.btn_create_contact)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @When("^I search for \"([^\"]*)\"$")
    public void I_search_for(String searchTerm) throws Throwable {
        onView(withId(R.id.edit_search_phone)).perform(typeText(searchTerm));
    }

    @Then("^the create contact button is visible$")
    public void the_create_contact_button_is_visible() throws Throwable {
        onView(withId(R.id.btn_create_contact)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @When("^I create a new contact$")
    public void I_create_a_new_contact() throws Throwable {
        onView(withId(R.id.btn_create_contact)).perform(click());
    }

    @And("^I enter the firstname \"([^\"]*)\"$")
    public void I_enter_the_firstname(String firstname) throws Throwable {
        onView(withId(R.id.txt_contact_firstname)).perform(typeText(firstname));
    }

    @And("^I enter the lastname \"([^\"]*)\"$")
    public void I_enter_the_lastname(String lastname) throws Throwable {
        onView(withId(R.id.txt_contact_lastname)).perform(typeText(lastname));
    }

    @And("^I save the new contact$")
    public void I_save_the_new_contact() throws Throwable {
        onView(withId(R.id.btn_save_contact)).perform(click());
    }

    @Then("^the contact \"([^\"]*)\" is displayed in the list$")
    public void the_contact_is_displayed_in_the_list(String contactName) throws Throwable {
        onView(withText(contactName)).check(matches(isDisplayed()));
    }

    @When("^I longpress contact \"([^\"]*)\"$")
    public void I_longpress_contact(String contactName) throws Throwable {
        onView(withText(contactName)).perform(longClick());
    }

    @Then("^the actions context menu is displayed$")
    public void the_actions_context_menu_is_displayed() throws Throwable {
        onView(withText("Delete contact")).check(matches(isDisplayed()));
    }

    @Then("^the actions context menu is displayed for \"([^\"]*)\"$")
    public void the_actions_context_menu_is_displayed_for(String contactName) throws Throwable {
        onView(withText(contactName)).check(matches(isDisplayed()));
        the_actions_context_menu_is_displayed();
    }

    @And("^I delete the contact$")
    public void I_delete_the_contact() throws Throwable {
        onView(withText("Delete contact")).perform(click());
    }

    @Then("^the contact \"([^\"]*)\" is not displayed in the list$")
    public void the_contact_is_not_displayed_in_the_list(String contactName) throws Throwable {
        onView(withText(contactName)).check(doesNotExist());
    }

    @Then("^a confirmation window is displayed$")
    public void a_confirmation_window_is_displayed() throws Throwable {
        onView(withText("Delete contact?")).check(matches(isDisplayed()));
    }

    @And("^I confirm the delete action$")
    public void I_confirm_the_delete_action() throws Throwable {
        onView(withText("Delete")).perform(click());
    }
}
