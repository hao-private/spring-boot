package com.connected.cloud.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ExampleOutputSink {
	String OUTPUT = "example.sink";
	
	@Output(ExampleOutputSink.OUTPUT)
	MessageChannel output();
}
