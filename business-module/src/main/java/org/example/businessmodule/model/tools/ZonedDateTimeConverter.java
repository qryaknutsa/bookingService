package org.example.businessmodule.model.tools;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return zonedDateTime != null ? Timestamp.from(zonedDateTime.toInstant()) : null;
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.UTC);
    }
}

