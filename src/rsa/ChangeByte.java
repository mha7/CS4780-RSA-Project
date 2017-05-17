package rsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import rsa.DigitalSignature;
import java.util.Scanner;

public class ChangeByte {
	byte[] digest, msg1, msg2;

	public byte[] appendArray(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}


	public void changebyte(File SignedTxt) throws NoSuchAlgorithmException{
		Scanner line = new Scanner(System.in);
		try(InputStream in = new FileInputStream(SignedTxt);
				ObjectInputStream ois = new ObjectInputStream(in);){

			int readByte;
			BigInteger sig;
			byte[] msg = new byte[0];


			sig = (BigInteger) ois.readObject();
			while((readByte = in.read()) != -1){
				byte[] msgByte = {(byte) readByte};
				//System.out.println((byte) readByte);
				msg = appendArray(msg, msgByte);
			}
			String strMsg = new String(msg);
			msg2 = strMsg.getBytes();
			int index = 0;
			do {
				System.out.println("what byte do you want to change? between 0 and " + msg.length);
				index = line.nextInt();
			}
			while(index < 0 || index >= msg.length);
			msg[index] = 0;

				OutputStream os = new FileOutputStream("message.txt.signed");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(sig);
			os.write(msg);
			os.flush();
			os.close();
			in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		DigitalSignature ds = new DigitalSignature();
		ds.receiver(new File("message.txt.signed"));


	}

	public static void main(String[] args){


	}
}