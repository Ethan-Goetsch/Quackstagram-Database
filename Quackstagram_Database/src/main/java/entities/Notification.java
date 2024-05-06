package entities;

import java.util.Objects;

public class Notification
{
    private final int id;
    private final String message;

    private User target;

    public Notification(int id, User target, String message)
    {
        this.id = id;
        this.target = target;
        this.message = message;
    }

    public int id()
    {
        return id;
    }

    public User target()
    {
        return target;
    }

    public String message()
    {
        return message;
    }

    public void setTarget(User target)
    {
        this.target = target;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Notification) obj;
        return this.id == that.id &&
                Objects.equals(this.target, that.target) &&
                Objects.equals(this.message, that.message);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, target, message);
    }

    @Override
    public String toString()
    {
        return "Notification[" +
                "id=" + id + ", " +
                "target=" + target + ", " +
                "message=" + message + ']';
    }

}