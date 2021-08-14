package org.tec.aws.handler.s3;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

public class Handler implements RequestHandler<S3Event, String> {
    @Override
    public String handleRequest(S3Event input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("received: " + input);
        return null;
    }
}
