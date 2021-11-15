#!/usr/bin/env node

var amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost', function(error0, connection) {
  if (error0) {
    throw error0;
  }
  connection.createChannel(function(error1, channel) {
    if (error1) {
      throw error1;
    }
    var exchange = 'messages-parsed';

    channel.assertExchange(exchange, 'fanout', {
      durable: false
    });

    channel.assertQueue('email-messages', {
      exclusive: false
    }, function(error2, q) {
      if (error2) {
        throw error2;
      }
      console.log(" [*] Waiting for messages in messages-parsed/email-messages. To exit press CTRL+C");
      channel.bindQueue(q.queue, exchange, 'email-messages');

      channel.consume(q.queue, function(msg) {
        if(msg.content) {
            message = msg.content.toString();
            console.log(" [x] Message received: %s",message);
            console.log(" [x] Sending email...");
            console.log(" [x] Done");
          }
      }, {
        noAck: true
      });
    });
  });
});