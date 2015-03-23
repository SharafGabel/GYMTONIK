package util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExclusionStrategy implements ExclusionStrategy{

    private final Class<?> typeToExclude;

    public GsonExclusionStrategy(Class<?> clazz){
        this.typeToExclude = clazz;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return ( this.typeToExclude != null && this.typeToExclude == clazz )
                || clazz.getAnnotation(GsonExclude.class) != null;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(GsonExclude.class) != null;
    }

    public static Gson createGsonFromBuilder( ExclusionStrategy exs ){
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.setExclusionStrategies(exs);
        return gsonbuilder.serializeNulls().create();
    }

    public static Gson createGsonFromBuilder(ExclusionStrategy gsonExclusionStrategy, ExclusionStrategy gsonExclusionStrategy1) {

        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.setExclusionStrategies(gsonExclusionStrategy);
        gsonbuilder.setExclusionStrategies(gsonExclusionStrategy1);
        return gsonbuilder.serializeNulls().create();
    }
}
