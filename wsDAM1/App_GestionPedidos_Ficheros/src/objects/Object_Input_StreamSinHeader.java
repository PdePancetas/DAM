package objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Object_Input_StreamSinHeader extends ObjectInputStream {

	public Object_Input_StreamSinHeader(FileInputStream fis) throws IOException, SecurityException {
		super(fis);

	}

	@Override
	protected void readStreamHeader() throws IOException {

	}

}
