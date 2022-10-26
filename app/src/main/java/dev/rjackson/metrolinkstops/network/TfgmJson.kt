package dev.rjackson.metrolinkstops.network

import com.squareup.moshi.*
import java.lang.reflect.Type


/**
 * TfGM API wraps responses in { "@odata.context": ..., "value": ... }.
 *
 * For API endpoints with this annotation, we will unwrap and return the contents of $.value
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)

internal annotation class TfgmJson

internal class TfgmJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        val delegateAnnotations = Types.nextAnnotations(
            annotations,
            TfgmJson::class.java
        ) ?: return null
        val delegate = moshi.nextAdapter<Any>(
            this,
            type,
            delegateAnnotations
        )
        return TfgmJsonAdapter(delegate)
    }

    private class TfgmJsonAdapter(val delegate: JsonAdapter<*>) : JsonAdapter<Any>() {

        override fun fromJson(reader: JsonReader): Any? {
            var value: Any? = null

            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.selectName(JsonReader.Options.of("value"))) {
                    0 -> value = delegate.fromJson(reader)
                    else -> {
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            }
            reader.endObject()

            return value
        }

        override fun toJson(writer: JsonWriter, value: Any?) = throw UnsupportedOperationException(
            "@TfgmJson is only used to deserialize objects."
        )
    }
}