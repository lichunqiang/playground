package lzuer.net.playground.models;

import java.util.List;

/**
 * Created by chunqiang on 2016/5/11.
 */
public class User {
    private int id;
    private String username;
    private Profile profile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    private class Profile {
        private String avatar;
        private String location;
    }

    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public static final class Course {
        private String name;
        private int id;
    }
}
