package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post
{
    private final int id;
    private final String title;

    private User author;
    private Picture content;
    private List<User> likes;

    public Post(int id, User author, String title, Picture content, List<User> likes)
    {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public Post(int id, User author, String title, Picture content)
    {
        this(id, author, title, content, new ArrayList<>());
    }

    public int id()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public User author()
    {
        return author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public Picture content()
    {
        return content;
    }

    public void setContent(Picture content)
    {
        this.content = content;
    }

    public List<User> likes()
    {
        return likes;
    }

    public void setLikes(List<User> likes)
    {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Post) obj;
        return this.id == that.id &&
                Objects.equals(this.author, that.author) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.content, that.content) &&
                Objects.equals(this.likes, that.likes);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, author, title, content, likes);
    }
}