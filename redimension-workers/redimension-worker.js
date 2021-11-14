#!/usr/bin/env node

var amqp = require('amqplib/callback_api');

function sendMessage(message) {
  var amqp2 = require('amqplib/callback_api');
  amqp2.connect('amqp://localhost', function(error3, connection2) {
        if (error3) {
          throw error3;
        }
        connection2.createChannel(function(error4, channel2) {
          if (error4) {
            throw error4;
          }
          var exchange = 'messages-parsed';
      
          channel2.assertExchange(exchange, 'fanout', {
            durable: false
          });
          channel2.publish(exchange, '', Buffer.from(message));
          console.log(" [x] Sent message: %s", message);
        });
      });
}

amqp.connect('amqp://localhost', function(error0, connection) {
  if (error0) {
    throw error0;
  }
  connection.createChannel(function(error1, channel) {
    if (error1) {
      throw error1;
    }
    var queue = 'messages-received';

    channel.assertQueue(queue, {
      durable: true
    });
    channel.prefetch(1);
    console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", queue);
    channel.consume(queue, function(msg) {
      var secs = msg.content.toString().split('.').length - 1;
    
      console.log(" [x] Received message: %s", msg.content.toString());
      sendMessage(msg.content.toString().toUpperCase())
      setTimeout(function() {
        console.log(" [x] Done acknowledge");
        channel.ack(msg);
      }, secs * 1000);


      }, {
        // manual acknowledgment mode,
        // see ../confirms.html for details
        noAck: false
      });
  });
});