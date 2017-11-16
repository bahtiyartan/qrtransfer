package com.yedibit.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MultiTest {

	private int inputNumber;
	private Boolean expectedResult;

	@Before
	public void initialize() {
		System.out.println("MultiTest.initialize()");
	}

	// Each parameter should be placed as an argument here
	// Every time runner triggers, it will pass the arguments
	// from parameters we defined in primeNumbers() method
	public MultiTest(Integer inputNumber, Boolean expectedResult) {
		this.inputNumber = inputNumber;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection loadParams() {
		return Arrays.asList(new Object[][] { { 2, true }, { 6, true }, { 18, true }, { 22, false }, { 23, true } });
	}

	// This test will run 4 times since we have 5 parameters defined
	@Test
	public void mytestmethod() {
		System.out.println("Parameterized Number is : " + inputNumber);
		assertEquals(expectedResult, inputNumber % 2 == 0);

	}

}
