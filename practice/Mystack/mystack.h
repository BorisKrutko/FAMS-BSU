#ifndef MYSTACK_H
#define MYSTACK_H

#include "mylist.h"


class MyStack: private MyList{
private:
    MyList* adapter_objekt;
public:
    MyStack(): adapter_objekt(new MyList()) {}
    void popFirst();
    void pushFistt(int data);
    bool isEmpty();
    std::vector<int> stackToVec();
};
#endif // MYSTACK_H
