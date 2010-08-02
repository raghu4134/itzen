package javaitzen.esb.objects;

import java.util.ArrayList;
import java.util.List;

public class Header {

    private List<String> history = new ArrayList<String>();
    private String currentUser;
    private String currentLocation;
    
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

}
