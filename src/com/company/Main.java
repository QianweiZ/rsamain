package com.company;
import java.math.BigInteger;
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //加密并编码处理，因为RSA算法依据的是大整数求解问题，所以需要将消息编码后计算	
	public static void decode(int n,int d,int EMSG[],char str[])
	{
		for(int i=0;i<EMSG.length;i++)
		{
			 str[i]=(char)rsamath(n,d,EMSG[i]);		
		}
	}
	//解密并解码处理
	public static void encode(int n,int e,char str[],int EMSG[])
	{
		int len=str.length;
		for(int i=0;i<len;i++)
		{
			//转换为asc码
			int ascii = (int)str[i]; 
			EMSG[i]=rsamath(n,e,ascii);
		}
	}
		
	//将数据处理为大整数类
	public static  int rsamath(int n,int e,int msg)
	{
		BigInteger a=BigInteger.valueOf(n);
		BigInteger c=BigInteger.valueOf(msg);
		return rsamath1(a,e,c);
	}
	//
	public static  int rsamath1(BigInteger n,int e,BigInteger msg)
	{
		
		BigInteger emsg,a;
		a=msg.pow(e);//指数	
		emsg=a.remainder(n);//取余
		return emsg.intValue();//将大数转换为整数
	}

	//定义哈希函数
	public static String sha512(String input)
	{
		try {
		MessageDigest messageDigest =MessageDigest.getInstance("SHA-512");   //（1）生成MessageDigest对象
		byte[] inputByteArray = input.getBytes();
		messageDigest.update(inputByteArray);								//（2）传入需要计算的字符串
		byte[] resultByteArray = messageDigest.digest();					//（3）计算消息摘要
		String str= new String(resultByteArray);
		System.out.print("消息摘要的二进制的十进制表示为：");
			System.out.print(Arrays.toString(resultByteArray));
		System.out.println();
			System.out.println();System.out.println();//（4）处理计算结果
		return  str;
		}catch(NoSuchAlgorithmException e)
		{
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input=new Scanner(System.in);
		System.out.print("请您输入明文:");
		String str1=input.next();

		char [] str=new char [1000];
		str=str1.toCharArray();
		System.out.print("输入的明文为:");
		for(int i=0;i<str.length;i++)
		System.out.print(str[i]);
		System.out.println();
		int [] EMSG=new int [1000];
		encode(3599,31,str,EMSG);
		System.out.print("输出的密文为:");
		for(int i=0;i<str.length;i++)
		System.out.print(EMSG[i]+" ");
		System.out.println();
		
		char [] ch1=new char [1000];
		decode(3599,3031,EMSG,ch1);
		System.out.print("解密后的明文为:");
		for(int i=0;i<ch1.length;i++)
		System.out.print(ch1[i]+" ");
		System.out.println();
		System.out.println();
		System.out.println();
		String msg,str2;
		str2=sha512(str1);
		System.out.println("消息摘要为："+str2);
		System.out.println();System.out.println();
		msg=str1+str2;
		int [] emsg=new int [1000];
		char [] cmsg=new char [1000];
		cmsg=msg.toCharArray();
		encode(3599,31,cmsg,emsg);
		System.out.print("消息+摘要 输出的密文为:");
		for(int i=0;i<emsg.length;i++)
		System.out.print(emsg[i]+" ");
		System.out.println();
		System.out.println();System.out.println();
		char [] ch2=new char [1000];
		decode(3599,3031,emsg,ch2);
		System.out.print("解密后的消息+摘要的明文为:");
		for(int i=0;i<emsg.length;i++)
		System.out.print(ch2[i]+" ");
		System.out.println();
		System.out.println();System.out.println();
		System.out.println("SHA-512消息摘要为：");
		for(int i=str1.length();i<str1.length()+512;i++)
			System.out.print(ch2[i]+" ");
		
		

	}

}
