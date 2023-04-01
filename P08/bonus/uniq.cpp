#include <iostream>
#include <string>

int main(int argc, char* argv[]) {
    if (argc == 1) {
        return 0;
    }

    std::string prev = argv[1];
    std::cout << prev;

    for (int i = 2; i < argc; ++i) {
        std::string curr = argv[i];
        if (curr != prev) {
            std::cout << " " << curr;
            prev = curr;
        }
    }

    std::cout << std::endl;
    return 0;
}
