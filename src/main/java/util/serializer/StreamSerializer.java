package util.serializer;

import exception.SerializerException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Provides functionality for serialization in {@link OutputStream}
 * and deserialization from {@link InputStream}
 */
public interface StreamSerializer {

    /**
     * Returns a content type of the serialized object
     *
     * @return a content type of the serialized object
     */
    String getContentType();

    /**
     * Serializes objects and writes it to the {@code stream}
     *
     * @param stream stream where serialized object is written to
     * @param o      object to serialize
     * @throws SerializerException if cannot serialize
     */
    void serialize(OutputStream stream, Object o);

    /**
     * Serializes the list of objects and writes them to the {@code stream}
     *
     * @param stream stream where serialized objects are written to
     * @param list   list of objects to serialize
     * @param c      Class of the objects' type
     * @param <T>    type of the objects
     * @throws SerializerException if cannot serialize
     */
    <T> void serializeList(OutputStream stream, List<T> list, Class<T> c);

    /**
     * Deserializes object from {@code stream}
     *
     * @param stream stream to read from
     * @param c      class of the object's type to deserialize
     * @param <T>    type of the object to deserialize
     * @return deserialized object
     * @throws SerializerException if cannot deserialize
     */
    <T> T deserialize(InputStream stream, Class<T> c);

    /**
     * Deserializes list of objects from {@code stream}
     *
     * @param stream stream to read from
     * @param c      class of the objects' type to deserialize
     * @param <T>    type of the objects to deserialize
     * @return list of deserialized objects
     * @throws SerializerException if cannot deserialize
     */
    <T> List<T> deserializeList(InputStream stream, Class<T> c);

}
