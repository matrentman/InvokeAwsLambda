package com.emptylogic.lambda;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import com.emptylogic.dto.HelloRequest;
import com.emptylogic.dto.HelloResponse;
import com.emptylogic.service.HelloService;
 
public class Lambda
{
    private static final Log logger = LogFactory.getLog(Lambda.class);

    public HelloResponse invokeLambda(AWSLambdaClient lambdaClient, String functionName, HelloRequest payload) 
    {
    	logger.info("Entered invokeLambda...");
    	HelloResponse response = null;
    	try 
    	{
            HelloService service = LambdaInvokerFactory.build(HelloService.class, lambdaClient);
            response = service.helloFunction(payload); 
    		
        } 
    	catch (Exception e) 
    	{
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    	logger.info("Exited invokeLambda...");
    	return response;
    }
}
