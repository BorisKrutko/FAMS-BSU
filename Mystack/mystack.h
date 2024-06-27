#ifndef MYSTACK_H
#define MYSTACK_H

#include "mylist.h"


class MyStack: public MyList{
private:
    MyList* adapter_objekt;
public:
    MyStack(): adapter_objekt(new MyList()) {}
    void popFirst();
    void pushFistt(int data);
    bool isEmpty();
    std::vector<int> printStack();
};
#endif // MYSTACK_H
