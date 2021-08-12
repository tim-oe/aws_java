package org.tec.aws.handler.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.Map;

@Log4j2
public class Handler implements RequestHandler<Map<String, Object>, APIGatewayV2HTTPResponse> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public APIGatewayV2HTTPResponse handleRequest(Map<String, Object> input, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("received: " + input);

		Response responseBody = new Response("Go Serverless v1.x! Your function executed successfully!", input);

		try {

			//TODO post to sqs

			return APIGatewayV2HTTPResponse.builder()
					.withStatusCode(200)
					.withBody(objectMapper.writeValueAsString(responseBody))
					.withHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
					.build();
		} catch (JsonProcessingException e) {
			log.error("failed to serialize response " + responseBody, e);
			throw new RuntimeException(e);
		}
	}
}