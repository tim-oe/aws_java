package org.tec.aws.handler.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see <a href="https://medium.com/codex/aws-api-gateway-lambda-im-going-to-need-more-coffee-for-this-21a210d48ec0">...</a>
 */
@Log4j2
public class Handler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	// a years worth of seconds
	private static final long MAX_AGE = 60 * 60 * 24 * 365;

	@Override
	public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent input, Context context) {
		Response responseBody = new Response("Go Serverless v1.x! Your function executed successfully!", input);

		try {
			LambdaLogger logger = context.getLogger();
			logger.log("received: " + objectMapper.writeValueAsString(input));

			//TODO post to sqs

			Map<String, String> headers = new HashMap<>();
			headers.put("X-Java-Lambda-Hit", LocalDateTime.now().toString());

			List<String> cookies = new ArrayList<>();
			// https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Set-Cookie
			cookies.add("tec.yourit=true; Max-Age=" + MAX_AGE);

			return APIGatewayV2HTTPResponse.builder()
					.withStatusCode(200)
					.withBody(objectMapper.writeValueAsString(responseBody))
					.withHeaders(headers)
					.withCookies(cookies)
					.build();
		} catch (JsonProcessingException e) {
			log.error("failed to serialize response " + responseBody, e);
			throw new RuntimeException(e);
		}
	}
}