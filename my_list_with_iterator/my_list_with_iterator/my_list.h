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
    ~MyList() {
        while (!this->isEmpty()) this->deleteEl();
    }
    void insertNode(int data);
    std::vector<int> listTovec();
    bool isEmpty();
    void deleteEl();
    int Count();
    int Get(int);
};

class Iterator
{
public:
    virtual void First() = 0;
    virtual void Next() = 0;
    virtual bool IsDone() = 0;
    virtual int CurrentItem() = 0;
protected:
    Iterator() = default;
};
#endif // MYLIST_H