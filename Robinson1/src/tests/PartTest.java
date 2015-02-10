package tests;

import static org.junit.Assert.*;
import model.Part;

import org.junit.Test;

public class PartTest {
	Part p;

	@Test
	public void testSetters() {
		p = new Part("Wrench", "1234", "WrenchCo.", "5");
		assertEquals(p.getErrorCount(), 0);
	}

	@Test
	public void testSettersBlank() {
		p = new Part("", "", "", "");
		assertEquals(p.getErrorCount(), 3);
	}

	@Test
	public void testSettersTooLong() {
		String nam = "";
		String num = "";
		String ven = "";
		for (int i = 0; i < 256; i++)
			nam += "a";
		for (int i = 0; i < 21; i++)
			num += "1";
		for (int i = 0; i < 256; i++)
			ven += "v";
		p = new Part(nam, num, ven, "0");

		assertEquals(p.getErrorCount(), 4);
	}

	@Test
	public void testQuantityUpdateToZero() {
		p = new Part("Wrench", "1234", "WrenchCo.", "5");
		p.setQuantity("0");
		assertEquals(p.getErrorCount(), 0);
	}

}
