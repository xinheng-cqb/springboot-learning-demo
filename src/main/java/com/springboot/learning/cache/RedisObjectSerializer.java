package com.springboot.learning.cache;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author caiqibin
 * @date 2017年7月16日
 * @introduce:实现对象的序列化接口
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();

	static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

	@Override
	public Object deserialize(byte[] byteArray) throws SerializationException {
		if (isEmpty(byteArray)) {
			return null;
		}
		return deserializer.convert(byteArray);
	}

	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		if (obj == null) {
			return EMPTY_BYTE_ARRAY;
		}
		return serializer.convert(obj);
	}

	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

}
