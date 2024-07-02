#include "listIterator.h"
#include "my_list.h"

ListIterator::ListIterator(MyList* List) : _list(List), _current(0) {}

void ListIterator::First()
{
    _current = 0;
}

void ListIterator::Next()
{
    _current++;
}

bool ListIterator::IsDone() {
    return _current <= _list->Count();
}

int ListIterator::CurrentItem() {
    return _list->Get(_current);
}