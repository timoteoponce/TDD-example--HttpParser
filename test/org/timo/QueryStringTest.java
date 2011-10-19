package org.timo;

import junit.framework.Assert;

import org.junit.Test;

public class QueryStringTest {

	@Test
	public void testEmpty(){
		QueryString queryString = new QueryString("");
		Assert.assertEquals(0, queryString.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull(){
		new QueryString(null);
	}
	
	@Test
	public void testSingleParam(){
		QueryString queryString = new QueryString("param1=value1");
		Assert.assertEquals(1, queryString.size());
		Assert.assertEquals("value1", queryString.getValue("param1"));
	}
	
	@Test
	public void testInvalidValue(){
		QueryString queryString = new QueryString("param1&param2=value2");
		Assert.assertEquals(2, queryString.size());
		Assert.assertEquals(null, queryString.getValue("param1"));
		Assert.assertEquals("value2", queryString.getValue("param2"));
	}
	
	@Test
	public void testMultipleParams(){
		QueryString queryString = new QueryString("param1=value1&param2=value2&param3=value3&param1=value4");
		Assert.assertEquals(3, queryString.size());
		Assert.assertEquals("value1", queryString.getValue("param1"));
		Assert.assertEquals("value2", queryString.getValue("param2"));
		Assert.assertEquals("value3", queryString.getValue("param3"));
	}
	
	@Test
	public void testInvalidValues(){
		QueryString queryString = new QueryString("param1=&param2=value2&param3&param1=value4");
		Assert.assertEquals(3, queryString.size());
		Assert.assertEquals(null, queryString.getValue("param1"));
		Assert.assertEquals("value2", queryString.getValue("param2"));
		Assert.assertEquals(null, queryString.getValue("param3"));
	}
	
}
