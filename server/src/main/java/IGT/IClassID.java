package IGT;


import org.codehaus.jettison.json.JSONObject;

public interface IClassID {
    String getClassId();

    Object getId();
    JSONObject toJSON();
}
