package org.isep.cleancode;

import java.time.LocalDate;

public class Todo {

    // this Todo class should be completed to achieve Step 1

    private String name;
    private String dueDate;

    public Todo(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isValid() {
        return name != null && name.length() < 64;
    }

    public boolean isDueDateValid() {
        if (dueDate == null || dueDate.isEmpty()) return true;
        try {
            LocalDate.parse(dueDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
