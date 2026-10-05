package com.openkairo.listnerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerClass implements IRetryAnalyzer {
	int count=0;
	int limit=5;
	@Override
	public boolean retry(ITestResult result) {
		if(count<=limit)
		{
			return true;
		}
		
		return false;
		
	}

}
