#include "vending_machine.h"
#include <ostream>
#include <iostream>

int main() {
    Vending_machine vendingMachine;
    vendingMachine.add("Peanut butter crackers", 169);
    vendingMachine.add("Oreos", 189);

    std::cout << "======================" << std::endl;
    std::cout << "Welcome to UTA Vending" << std::endl;
    std::cout << "======================" << std::endl;
    std::cout << vendingMachine.menu() << std::endl;

    vendingMachine.buy(0);

    return 0;
}
