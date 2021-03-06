package com.nttdata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyDateProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.getName().equals(MyDate.class.getName())) {
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					Calendar calendar = Calendar.getInstance();
					if (value.equalsIgnoreCase("yesterday")) {
						calendar.add(Calendar.DATE, -1);

					}
					if (value.equalsIgnoreCase("tomorrow")) {
						calendar.add(Calendar.DATE, 1);

					}

					MyDate myDate = new MyDate();
					myDate.setDate(calendar.get(Calendar.DATE));
					myDate.setMonth(calendar.get(Calendar.MONTH));
					myDate.setYear(calendar.get(Calendar.YEAR));

					return rawType.cast(myDate);
				}

				@Override
				public String toString(T value) {
					if (value == null)
						return null;
					return value.toString();
				}
			};
		}
		return null;
	}

}
