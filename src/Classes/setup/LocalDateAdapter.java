package Classes.setup;

import com.google.gson.*;
import java.time.LocalDate;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public JsonElement serialize(LocalDate src, java.lang.reflect.Type typeOfSrc,
                                JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
                                JsonDeserializationContext context) {
        return LocalDate.parse(json.getAsString());
    }
}

