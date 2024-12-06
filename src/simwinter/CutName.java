package simwinter;

public abstract class CutName {

    public abstract int cutNum();

    public String isCutName (String name) {
        if (name.length() > cutNum()) {
            return (name.substring(0, cutNum() - 3) + "...");
        }else {
            return name;
        }
    }

}
