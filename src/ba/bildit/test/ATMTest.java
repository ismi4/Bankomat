package ba.bildit.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import ba.bildit.code.ATM;

public class ATMTest {

	
	@Test
	public void load_succesfulLoad_returnTrue() throws ClassNotFoundException, IOException {
		assertTrue(ATM.load());
	}
	
	@Test
	public void save_succesfulSave_returnTrue() throws ClassNotFoundException, IOException {
		assertTrue(ATM.save());
	}
	
	

}
