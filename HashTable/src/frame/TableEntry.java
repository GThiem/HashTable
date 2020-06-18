package frame;

public class TableEntry implements Comparable<TableEntry> {

    private String key;
    private String data;

    /** Creates a new instance of Entry */
    public TableEntry() {
            this.key = null;
            this.data = null;
    }

    public TableEntry(String key, String data) {
            this.key = key;
            this.data = data;
    }

    public void setKey(String newKey) {
            this.key = newKey;
    }

    public void setData(String newData) {
            this.data = newData;
    }

    public String getKey() {
            return key;
    }

    public String getData() {
            return data;
    }

    public String toString() {
            if( data == null || key == null ) {
            	return "";
            }
            return key+";"+data;
    }

    public int compareTo(TableEntry e) {
            return this.key.compareTo(e.getKey());
    }
}