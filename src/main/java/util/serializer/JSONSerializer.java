package util.serializer;

import annotation.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.SerializerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Provides functionality for serialization to JSON
 * and deserialization from JSON
 */
@Serializer("json")
public final class JSONSerializer implements StreamSerializer {

    private static final String CONTENT_TYPE = "application/json";
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONSerializer.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public void serialize(OutputStream stream, Object o) {
        try {
            mapper.writeValue(stream, o);
        } catch (Exception e) {
            LOGGER.warn("Cannot serialize object.", e);
            throw new SerializerException("Cannot serialize object", e);
        }
    }

    @Override
    public <T> void serializeList(OutputStream stream, List<T> o, Class<T> c) {
        try {
            mapper.writeValue(stream, o);
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize list of objects.", e);
            throw new SerializerException("Cannot deserialize list of objects", e);
        }
    }

    @Override
    public <T> T deserialize(InputStream stream, Class<T> c) {
        try {
            return mapper.readValue(stream, c);
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize object", e);
            throw new SerializerException("Cannot deserialize object", e);
        }
    }

    @Override
    public <T> List<T> deserializeList(InputStream stream, Class<T> c) {
        try {
            return mapper.readValue(stream, mapper.getTypeFactory().constructCollectionType(List.class, c));
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize list of object.", e);
            throw new SerializerException("Cannot deserialize list of objects", e);
        }
    }
}