package services;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class ResourceSerializer {
	private static Serializer SERIALIZER = new Persister();
	
	public static ResourceSerializer instance() {
		return new ResourceSerializer();
	}
	
	public <T> T read(Class<T> clazz, String xml) {
		try {
			return SERIALIZER.read(clazz, xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
