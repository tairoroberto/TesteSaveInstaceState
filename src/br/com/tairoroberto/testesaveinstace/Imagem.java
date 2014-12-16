package br.com.tairoroberto.testesaveinstace;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Imagem implements Serializable{
	public Bitmap bitmap;
	
	public Imagem(Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		this.bitmap =  bitmap;
	}
	
	private void writeObject(ObjectOutputStream out)throws IOException{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
		byte bitmapBytes[] = byteStream.toByteArray();
		out.write(bitmapBytes,0,bitmapBytes.length);
	}
	
	private void readObject(ObjectInputStream in)throws IOException{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		int b;
		while ((b = in.read()) != -1) {
			byteStream.write(b);
			byte bitmapBytes[] = byteStream.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);	
		}
	}
}
