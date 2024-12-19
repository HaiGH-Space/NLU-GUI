package org.haigh.tool;

public interface SubjectTool {
    void addObserverTool(ObserverTool observer);
    void removeObserverTool(ObserverTool observer);
    /*
        status = 0 : Add
               = 1 : Remove
               = 2 : Edit
    */
    void notifyObserversTool(Object[] message, int status);
}
