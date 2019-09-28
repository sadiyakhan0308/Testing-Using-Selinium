package com.wp;
import org.apache.log4j.Priority;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Show{
	@ Test(priority=1,enabled=false)
	public void a()
	{
		System.out.println("Welcome A");
	}
	@ Test(priority=2,enabled=false)
	public void b()
	{
		System.out.println("Welcome B");
	}
	@ Test(priority=4,enabled=true)
	public void c()
	{  SoftAssert sf=new SoftAssert();
	sf.assertAll();
		a();
		b();
		System.out.println("Welcome C");
		sf.assertEquals(false, true);
		//sf.assertAll();
	}
	@ Test(priority=3,enabled=true)
	public void d()
	{  
		a();
		b();
		System.out.println("Welcome D");
		Assert.assertEquals(true, false);
	}
	@ Test(priority=5,enabled=true)
	public void e()
	{  
		//a();
		//b();
		System.out.println("Welcome E");
		//Assert.assertEquals(true, false);
	}


}
