/*
package com.example.itext_pdf.Aspose;

import java.io.IOException;

public class OutputStreamAdapter extends com.aspose.pdf.internal.ms.System.IO.Stream
{

	private final java.io.OutputStream outputStream;
	public OutputStreamAdapter(java.io.OutputStream os) {
		outputStream = os;
	}

	@Override
	public boolean canRead() {
		return false;
	}

	@Override
	public boolean canSeek() {
		return false;
	}

	@Override
	public boolean canWrite() {
		return true;
	}

	@Override
	public void flush() {
		try {
			outputStream.flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public long getLength() {
		return 0;
	}

	@Override
	public long getPosition() {
		return 0;
	}

	@Override
	public int read(@InAttribute @OutAttribute byte[] arg0, int arg1, int arg2) {
		return 0;
	}

	@Override
	public long seek(long arg0, int arg1) {
		return 0;
	}

	@Override
	public void setLength(long arg0) {
		
	}

	@Override
	public void setPosition(long arg0) {
	}

	@Override
	public void write(byte[] b, int off, int len) {
		try {
			outputStream.write(b, off, len);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}


}
*/
