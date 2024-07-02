#include "my_list.h"

#pragma once
class ListIterator : public Iterator
{
public:
    ListIterator(MyList* aList);
    virtual void First();
    virtual void Next();
    virtual bool IsDone();
    virtual int CurrentItem();

private:
    MyList* _list;
    int _current;
};


