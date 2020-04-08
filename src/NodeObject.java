public class NodeObject
{
    protected String id;

    //Constructors
    public NodeObject()
    {
        id = "id";
    }
    public NodeObject(String id)
    {
        this.id = id;
    }

    public NodeObject deepCopy()
    {
        NodeObject clone = new NodeObject(id);
        return clone;
    }

    public int compareTo(String targetKey)
    {
        return (id.compareTo(targetKey));
    }

    public void setId(String id)
    {
        this.id = id;
    }
    public String getId()
    {
        return id;
    }

    public String toString()
    {
        return this.id;
    }
}