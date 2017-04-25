package com.github.lisilisenok.chimejavaexample;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;


public class Main {

	// Scheduling with Chime
	static void scheduling(Vertx vertx) {
		EventBus eventBus = vertx.eventBus();
		// Consumer of the timer events
		MessageConsumer<JsonObject> consumer = eventBus.consumer("scheduler:timer");
		// Listens and prints timer events. When timer completes stops the Vertx 
		consumer.handler (
			message -> {
				JsonObject event = message.body();
				if (event.getString("event").equals("complete")) {
					System.out.println("completed");
					vertx.close();
				}
				else {
					System.out.println(event);
				}
		  	}
		);
		// Create new timer
		eventBus.send (
			"chime",
			(new JsonObject()).put("operation", "create").put("name", "scheduler:timer")
				.put("publish", false).put("max count", 3)
				.put("description", (new JsonObject()).put("type", "interval").put("delay", 1)),
			ar -> {
				if (ar.succeeded()) {
					System.out.println("Scheduling started: " + ar.result().body());
				}
				else {
					System.out.println("Message failed: " + ar.cause());
					vertx.close();
				}
			}
		);
	}
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		// deploying Chime
		vertx.deployVerticle("ceylon:herd.schedule.chime/0.2.0", res -> {
			  if (res.succeeded()) {
				  // Chime has been successfully deployed - start scheduling
				  scheduling(vertx);
			  } else {
				  System.out.println("Deployment failed! " + res.cause());
				  vertx.close();
			  }
		});
	}

}
