#!/usr/bin/env ruby
require 'bunny'

def send_message (message)
  connection = Bunny.new
  connection.start

  channel = connection.create_channel
  exchange = channel.fanout('messages-parsed')

  exchange.publish(message)
  puts " [x] Sent message: #{message}"

  connection.close
end

connection = Bunny.new(automatically_recover: false)
connection.start

channel = connection.create_channel
queue = channel.queue('messages-received', durable: true)

channel.prefetch(1)
puts ' [*] Waiting for messages in messages-received. To exit press CTRL+C'

begin
  queue.subscribe(manual_ack: true, block: true) do |delivery_info, _properties, body|
    puts " [x] Received message: '#{body}'"
    puts " [x] Working..."
    sleep 2
    send_message(body.upcase)
    puts ' [x] Done'
    channel.ack(delivery_info.delivery_tag)
  end
rescue Interrupt => _
  connection.close
end