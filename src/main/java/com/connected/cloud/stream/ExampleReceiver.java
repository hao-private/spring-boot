package com.connected.cloud.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(ExampleInputSink.class)
public class ExampleReceiver {
	
	@StreamListener(ExampleInputSink.INPUT)
	public void onReceiveMessage(String name) {
		System.out.println("Received message with payload " + name);
	}
}
