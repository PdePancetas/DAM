package help_Tema_1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamSinHeader extends ObjectOutputStream {

	@Override
	protected void writeStreamHeader() {}
	
	public ObjectOutputStreamSinHeader(FileOutputStream out) throws IOException {
		super(out);
		// TODO Auto-generated constructor stub
	}


	public ObjectOutputStreamSinHeader() throws IOException, SecurityException {
		super();
		// TODO Auto-generated constructor stub
	}

}
