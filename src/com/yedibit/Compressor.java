package com.yedibit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class Compressor {

	private int m_nBufferSize;
	private int m_nCompressionType;

	public Compressor(int p_nBufferSize, int p_nCompressionType) {
		m_nBufferSize = p_nBufferSize;
		m_nCompressionType = p_nCompressionType;
	}

	public Compressor() {
		this(2000000, Deflater.BEST_COMPRESSION);
	}

	public byte[] compress(byte[] p_arrData) {

		try {
			Deflater m_jCompresser = new Deflater();
			m_jCompresser.setLevel(m_nCompressionType);

			int nBuffer = Math.min(m_nBufferSize, p_arrData.length);

			ByteArrayOutputStream bos = new ByteArrayOutputStream(nBuffer);
			BufferedOutputStream bufferedOut = new BufferedOutputStream(bos, nBuffer);
			DeflaterOutputStream dos = new DeflaterOutputStream(bufferedOut, m_jCompresser, nBuffer);

			dos.write(p_arrData);
			dos.close();

			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return p_arrData;
		}

	}

	public byte[] decompress(byte[] p_arrData) {

		byte[] result = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(p_arrData);
			BufferedInputStream bis = new BufferedInputStream(bais);
			int fsize = bis.available();
			int BUFFERSIZE = Math.min(m_nBufferSize, fsize);

			byte[] buffer = new byte[BUFFERSIZE];
			result = new byte[BUFFERSIZE];
			Inflater inf = new Inflater();
			InflaterInputStream iis = new InflaterInputStream(bis, inf, BUFFERSIZE);
			int newBufferSize = 0;
			while (iis.available() == 1) {
				int bytesread = iis.read(buffer);
				if (bytesread != -1) {
					byte[] tmpres = new byte[newBufferSize];
					System.arraycopy(result, 0, tmpres, 0, newBufferSize);
					newBufferSize += bytesread;
					result = new byte[newBufferSize];
					System.arraycopy(tmpres, 0, result, 0, tmpres.length);
					tmpres = null;
					System.arraycopy(buffer, 0, result, newBufferSize - bytesread, bytesread);
				}
			}
			iis.close();
			inf.end();
		} catch (Exception ioe) {
			return p_arrData;
		}
		return result;
	}
}
