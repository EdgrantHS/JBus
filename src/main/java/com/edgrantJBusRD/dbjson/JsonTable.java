package com.edgrantJBusRD.dbjson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

/**
 * JsonTable is a class that facilitates reading and writing JSON data in various classes and methods.
 * It extends the Vector class and can hold objects of a generic type.
 *
 * @param <T> The generic type of objects that this JsonTable can hold.
 * @author Rafie Amandio
 */
public class JsonTable<T> extends Vector<T> {
    private static final Gson gson = new Gson();
    /**
     * file path untuk file
     */
    public final String filepath;

    /**
     * Constructs a JsonTable with the specified class type and file path.
     *
     * @param clazz    The class type of objects to be stored in the JsonTable.
     * @param filepath The file path where the JSON data will be read from or written to.
     * @throws IOException If an I/O error occurs during file creation or reading.
     */
    @SuppressWarnings("unchecked")
    public JsonTable(Class<T> clazz, String filepath) throws IOException {
        this.filepath = filepath;
        try {
            Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] loaded = readJson(arrayType, filepath);
            if (loaded != null) {
                Collections.addAll(this, loaded);

                int lastId = 0;
                for (T item : this) {
                    if (item instanceof Serializable) {

                        Serializable serializableItem = (Serializable) item;
                        lastId = Math.max(lastId, serializableItem.id);
                    }
                }

                Serializable.setLastAssignedId(clazz, lastId);
            }
        } catch (FileNotFoundException e) {
            File file = new File(filepath);
            File parent = file.getParentFile();
            if (parent != null)
                parent.mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Writes the content of this JsonTable to the specified file in JSON format.
     *
     * @throws IOException If an I/O error occurs during the write operation.
     */
    public void writeJson() throws IOException {
        writeJson(this, this.filepath);
    }

    /**
     * Writes the given object to the specified file in JSON format.
     *
     * @param object   The object to be written to the file.
     * @param filepath The file path where the JSON data will be written to.
     * @throws IOException If an I/O error occurs during the write operation.
     */
    public static void writeJson(Object object, String filepath) throws IOException {
        final FileWriter writer = new FileWriter(filepath);
        writer.write(gson.toJson(object));
        writer.close();
    }

    /**
     * Reads and deserializes JSON data from the specified file and returns an object of the specified class type.
     *
     * @param <T>      The generic type of the object to be returned.
     * @param clazz    The class type of the object to be created from the JSON data.
     * @param filepath The file path from which JSON data will be read.
     * @return An object of the specified class type created from the JSON data.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static <T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException {
        final JsonReader reader = new JsonReader(new FileReader(filepath));
        return gson.fromJson(reader, clazz);
    }
}
