#ifndef VENDING_MACHINE_H
#define VENDING_MACHINE_H

#include "item.h"
#include <vector>
#include <string>

class Vending_machine {
public:
    void add(const std::string& name, int price);
    void buy(int index);
    std::string menu() const;

private:
    std::vector<Item> items;
};

#endif 
