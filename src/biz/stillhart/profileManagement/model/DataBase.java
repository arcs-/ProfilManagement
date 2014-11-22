package biz.stillhart.profileManagement.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Patrick Stillhart on 11/1/14.
 */
public class DataBase implements Serializable {

    HashMap<String,Student> cache;

    public DataBase() {
        cache = new HashMap<String, Student>();

        // ToDo connect server

        cache.put("stillhartp", new Student("stillhartp", "Patrick", "Stillhart", "Kontroll", "patrick@stillhart.biz", "Feldstrasse 12", "Hirzel"));
        cache.put("leor", new Student("leor", "Leo", "Rudin", "123", "mail@leorundin.ch", "irgendwo 66", "Lachen"));
        cache.put("br", new Student("br", "br", "br", "123", "stillhart@hispeed.ch", "irgendwo 66", "Hirzel"));

    }

    /**
     * Check if credentials correspond to a user
     * @param credentials Credentials object with username and password
     * @return true if the match a user
     */
    public boolean isUser(Credentials credentials) {
        if(cache.containsKey(credentials.getUsername()) && cache.get(credentials.getUsername()).getPassword().equalsIgnoreCase(credentials.getPassword())){
            return true;
        } else {
            // ToDo: check database
            // and add to cache
        }
        return false;

    }

    public void save(Student student) {
        cache.put(student.getUserName(), student);
    }

    /**
     * Get a student by username
     * @param username the username
     * @return the student with this username
     */
    public Student getStudent(String username) {
        return cache.get(username);
    }

}
