package kta999.entertainment.panamamonk;

/**
 * Created by Acer on 30/03/2018.
 */

public class UserInfo {

    private String firstName, lastName, email, gender, url;

    UserInfo(String firstName, String lastName, String email, String gender, String url) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.url = url;

    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUrl() {
        return url;
    }

}
