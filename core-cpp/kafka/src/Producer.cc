#include "kafka/Producer.hh"

#include "cppkafka/buffer.h"

#include "kafka/Kafka.hh"
#include "kafka/ProcessHandler.hh"

#include "swa/CommandLine.hh"

namespace Kafka {

Producer::Producer() {
  const std::string brokers =
      SWA::CommandLine::getInstance().getOption(BrokersOption);
  cppkafka::Configuration config = {{"metadata.broker.list", brokers}};
  prod = std::make_unique<cppkafka::Producer>(config);
}

void Producer::publish(int domainId, int serviceId, BufferedOutputStream &buf) {
  // find/create a message builder
  std::shared_ptr<cppkafka::MessageBuilder> msgBuilder;
  TopicLookup::iterator entry = topicLookup.find(std::make_pair(domainId, serviceId));
  if (entry == topicLookup.end()) {
    std::string topicName = ProcessHandler::getTopicName(domainId, serviceId);
    msgBuilder = std::make_shared<cppkafka::MessageBuilder>(topicName);
    topicLookup.insert(TopicLookup::value_type(
        std::make_pair(domainId, serviceId), msgBuilder));
  } else {
    msgBuilder = entry->second;
  }

  // Set the payload on this builder
  msgBuilder->payload(cppkafka::Buffer(buf.begin(), buf.end()));

  // Produce the message
  prod->produce(*msgBuilder);
}

Producer &Producer::getInstance() {
  static Producer instance;
  return instance;
}

} // namespace Kafka