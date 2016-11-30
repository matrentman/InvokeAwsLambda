import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.emptylogic.Lambda;
import com.emptylogic.dto.HelloRequest;

public class LambdaTest 
{
    private static final String regionName = "us-east-2";
    private static final String functionName = "HelloFunction";
    private static final HelloRequest payload = new HelloRequest("World"); 
    
    @Test
    public void testShouldReturncorrectText() 
	{
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
        String result = lambda.invokeLambda(lambdaClient, functionName, payload).getGreeting();

        assertEquals(result, "Hello, World!");
    }
}
