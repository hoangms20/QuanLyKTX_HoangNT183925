package view.tes;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;

    public Person(String fName, String lName) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
    }

    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String fName) { firstName.set(fName); }
    public String getLastName() { return lastName.get(); }
    public void setLastName(String fName) { lastName.set(fName); }
}
