#!/usr/bin/env python

import pika

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.exchange_declare(exchange='messages-parsed', exchange_type='fanout')

result = channel.queue_declare(queue='', exclusive=True)
queue_name = result.method.queue

channel.queue_bind(exchange='messages-parsed', queue=queue_name)

print(' [*] Waiting for messages. To exit press CTRL+C')

total_messages_received = 0 # global variable

def callback(ch, method, properties, body):
    print(" [x] %r" % body)
    global total_messages_received
    total_messages_received = total_messages_received + 1
    print(" [x] Total messages received = %r" % total_messages_received)

channel.basic_consume(
    queue=queue_name, on_message_callback=callback, auto_ack=True)

channel.start_consuming()