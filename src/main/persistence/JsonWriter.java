// NOTE: This class is based on the JsonWriter class in the CPSC 210 JsonSerializationDemo project
// SOURCE: UBC CPSC 210 course staff (2026)

package persistence;
import model.RouteManager;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of RouteManager to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of RouteManager to file
    public void write(RouteManager rm) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
