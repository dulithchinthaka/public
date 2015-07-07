/**
  IAmContent Public Libraries.
  Copyright (C) 2015 Greg Elderfield
  @author Greg Elderfield, support@jarchitect.co.uk
 
  This program is free software; you can redistribute it and/or modify it under the terms of the
  GNU General Public License as published by the Free Software Foundation; either version 2 of 
  the License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this program;
  if not, write to the Free Software Foundation, Inc., 
  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.iamcontent.device.io.analog.calibrate.gson;

import static com.iamcontent.io.gson.GsonUtils.getMemberAsObject;
import static com.iamcontent.io.gson.InterRangeDoubleConverterDeserializer.interRangeDoubleConverterGsonBuilder;

import java.lang.reflect.Type;

import com.google.common.base.Converter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.iamcontent.core.math.InterRangeDoubleConverter;
import com.iamcontent.device.io.analog.calibrate.ProportionalAnalogIOCalibrator;

/**
 * A Gson {@link JsonDeserializer} for {@link ProportionalAnalogIOCalibrator} objects.
 * @author Greg Elderfield
 */
public class ProportionalAnalogIOCalibratorDeserializer implements JsonDeserializer<ProportionalAnalogIOCalibrator> {

	@Override
	public ProportionalAnalogIOCalibrator deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		final Converter<Double, Double> valueConverter = getMemberAsObject(json, "valueConverter", context, InterRangeDoubleConverter.class);
		return new ProportionalAnalogIOCalibrator(valueConverter);
	}

	public static GsonBuilder register(GsonBuilder builder) {
		return builder.registerTypeAdapter(ProportionalAnalogIOCalibrator.class, INSTANCE);
	}
	
	public static GsonBuilder proportionalAnalogIOCalibratorGsonBuilder() {
		return register(interRangeDoubleConverterGsonBuilder());
	}
	
	private static final ProportionalAnalogIOCalibratorDeserializer INSTANCE = new ProportionalAnalogIOCalibratorDeserializer();
}
