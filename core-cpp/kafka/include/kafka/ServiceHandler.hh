#ifndef Kafka_ServiceHandler_HH
#define Kafka_ServiceHandler_HH

#include <nlohmann/json.hpp>
#include <functional>

namespace Kafka {

typedef std::function<void()> Callable;

class ServiceHandler {
public:
  virtual Callable getInvoker(std::vector<std::uint8_t> data) const {
    return Callable();
  }
  virtual ~ServiceHandler();
};

} // namespace Kafka

#endif
