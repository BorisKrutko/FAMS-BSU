#include <iostream>
#include "my_list.h"
#include "listIterator.h"

int main() {
	MyList l_ist = MyList();
	l_ist.insertNode(3);
	l_ist.insertNode(2);
	l_ist.insertNode(10);
	ListIterator temp = ListIterator(&l_ist);
	temp.Next();
	std::cout << temp.CurrentItem();
	temp.First();
	std::cout << temp.CurrentItem();
	l_ist.deleteEl();
	std::cout << temp.CurrentItem();
	return 0;
}