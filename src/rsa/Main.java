package rsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.net.*;

import javax.print.DocFlavor.URL;

import rsa.KeyGen;
import rsa.DigitalSignature;

public class Main { 
	
	public static void main(String[] args) {
		String filepath = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the file you wish to decrypt: ");
		filepath = in.next();

		try{
			KeyGen.main(args);
			DigitalSignature ds = new DigitalSignature();
			ds.sender(new File(filepath));
			ds.receiver(new File("message.txt.signed"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("What file do you want to change?");
		filepath = in.next();
		ChangeByte cb = new ChangeByte();
		try {
			cb.changebyte(new File(filepath));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
