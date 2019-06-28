package NetWork;

import logic.APlayLists;

import java.io.Serializable;

public class SharedPlayList extends APlayLists implements Serializable {
    public SharedPlayList() {
        super("Shared");
    }


}
