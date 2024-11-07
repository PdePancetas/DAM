package objects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Object_Output_StreamSinHeader extends ObjectOutputStream{

	protected Object_Output_StreamSinHeader(FileOutputStream fos) throws IOException, SecurityException {
		super(fos);
		
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		
	}
	
	

}
