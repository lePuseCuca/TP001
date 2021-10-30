package dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc.ConnectionProvider;

public class TestGenerico {
	
	private static Connection conn;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn.setAutoCommit(true);
	}
	
	@After
	public static void tearDown() throws Exception{
		conn.rollback();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
