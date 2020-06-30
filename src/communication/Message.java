package communication;

import java.io.Serializable;

public class Message implements Serializable {

    private Object obj;
    private String action;

    /**
     *
     * @param obj
     * @param action
     */
    public Message(Object obj, String action) {
        this.obj = obj;
        this.action = action;
    }

    /**
     *
     * @return
     */
    public Object getObject() {
        return obj;
    }

    /**
     *
     * @return
     */
    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Message{" + "obj=" + obj + ", action=" + action + '}';
    }

}
