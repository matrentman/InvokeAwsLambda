package com.emptylogic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.emptylogic.dto.HelloRequest;
import com.emptylogic.dto.HelloResponse;
 
public class Main
{
    private static final Log logger = LogFactory.getLog(Main.class);
    private static final String regionName = "us-east-2";
    private static final String functionName = "HelloFunction";
    private static final HelloRequest payload = new HelloRequest("World"); 

    public static void main(String... args) 
    {
    	logger.info("Entered main...");

        AWSCredentials credentials = null;
        try 
        {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } 
        catch (Exception e) 
        {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location and is in the proper format.",
                    e);
        }
        
        AWSLambdaClient lambdaClient = (credentials == null) ? new AWSLambdaClient()
                : new AWSLambdaClient(credentials);
        Region region = Region.getRegion(Regions.fromName(regionName));
        lambdaClient.setRegion(region);

        Lambda lambda = new Lambda();
        HelloResponse response = lambda.invokeLambda(lambdaClient, functionName, payload);
        System.out.println(response.getGreeting());
        logger.info("Exited main...");
    }
}
