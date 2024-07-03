#ifndef MYLIST_H
#define MYLIST_H
#include "vector"

class Node {
public:
    int data;
    Node* next;
    Node(int value) : data(value), next(nullptr) {}
};

class MyList {
private:
    Node* head;
public:
    MyList() : head(nullptr) {};
    ~MyList(){
        while(!this->isEmpty()) this->deleteEl();
    }
    void insertNode(int data);
    std::vector<int> listToVec();
    bool isEmpty();
    void deleteEl();
};


#endif // MYLIST_H
