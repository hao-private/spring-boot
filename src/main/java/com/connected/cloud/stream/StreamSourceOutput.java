package com.connected.cloud.stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class StreamSourceOutput {
	
	private MessageChannel output;
	
	public StreamSourceOutput(@Qualifier(ExampleOutputSink.OUTPUT) MessageChannel output) {
		this.output = output;
	}
	
	public void sayHello(String name) {
		output.send(MessageBuilder.withPayload(name).build());
	}
}
