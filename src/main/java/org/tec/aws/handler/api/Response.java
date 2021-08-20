package org.tec.aws.handler.api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import lombok.Getter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
public class Response {

	private final String message;
	private final APIGatewayV2HTTPEvent event;

	public Response(String message, APIGatewayV2HTTPEvent event) {
		this.message = message;
		this.event = event;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
