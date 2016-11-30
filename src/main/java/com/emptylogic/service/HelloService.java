package com.emptylogic.service;

import com.amazonaws.services.lambda.invoke.LambdaFunction;
import com.emptylogic.dto.HelloRequest;
import com.emptylogic.dto.HelloResponse;

public interface HelloService 
{
	@LambdaFunction(functionName="HelloFunction")
	HelloResponse helloFunction(HelloRequest input);
}
