package communication;

import java.io.Serializable;

public class Message implements Serializable {
    private Object obj;
    private String action;

    public Message(Object obj, String action) {
        this.obj = obj;
        this.action = action;
    }

    public Object getObject() {
        return obj;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Message { " + "object= " + obj + ", action= " + action + " }";
    }
}
