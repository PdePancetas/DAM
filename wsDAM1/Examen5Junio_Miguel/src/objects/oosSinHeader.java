package objects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class oosSinHeader extends ObjectOutputStream {

	public oosSinHeader(FileOutputStream fos) throws IOException {
		super(fos);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void writeStreamHeader() throws IOException {
	
	}

}
