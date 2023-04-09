#include "vending_machine.h"
#include <iostream>

void Vending_machine::add(const std::string& name, int price) {
    items.push_back(Item(name, price));
}

void Vending_machine::buy(int index) {
    if (index >= 0 && index < items.size()) {
        std::cout << "#### Buying " << items[index].to_string() << std::endl;
    } else {
        std::cout << "Error." << std::endl;
    }
}

std::string Vending_machine::menu() const {
    std::string menuString;
    for (size_t i = 0; i < items.size(); i++) {
        menuString += std::to_string(i) + ") " + items[i].to_string() + "\n";
    }
    return menuString;
}
