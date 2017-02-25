package com.connected.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ExampleInputSink {
	String INPUT = "example.sink";

	@Input(ExampleInputSink.INPUT)
	MessageChannel input();
}
