package com.mansourahmed.algorithms.stablemarriage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mansour on 05/12/15.
 */
public class Proposer {

    private Integer id;
    private List<Integer> preferences = new ArrayList<>();
    private boolean engaged = false;

    public Proposer(Integer id) {
        this.id = id;
    }

    public List<Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Integer> preferences) {
        this.preferences = preferences;
    }

    public boolean isEngaged() {
        return engaged;
    }

    public void setEngaged(boolean engaged) {
        this.engaged = engaged;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Proposer{" +
                "id=" + id +
                ", preferences=" + preferences +
                ", engaged=" + engaged +
                '}';
    }
}