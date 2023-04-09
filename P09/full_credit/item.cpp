#include "item.h"
#include <stdexcept>

Item::Item(const std::string& name, int price) {
    if (price < 0) {
        throw std::runtime_error("Error: Price can't be negative.");
    }
    this->name = name;
    this->price = price;
}

std::string Item::to_string() const {
    return name + " ($" + std::to_string(price / 100) + "." + std::to_string(price % 100) + ")";
}


