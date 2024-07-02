#include "my_list.h"
#include <iostream>
#include <vector>

void MyList::insertNode(int data) {
    if (head == nullptr) {
        head = new Node(data);
    }
    else {
        Node* temp = head;
        head = new Node(data);
        head->next = temp;
    }
}

std::vector<int> MyList::printList() {
    std::vector<int> res_vec;
    Node* temp = head;
    std::cout << "print: ";
    while (temp != nullptr) {
        res_vec.push_back(temp->data);
        temp = temp->next;
    }
    return res_vec;
}

bool MyList::isEmpty() {
    if (head == nullptr) return true;
    return false;
}

void MyList::deleteEl() {
    if (!this->isEmpty()) {
        if (head->next == nullptr) head = nullptr;
        else {
            Node* temp = head;
            head = head->next;
            delete temp;
        }
    }
}

int MyList::Count() {
    if (isEmpty())
        return 0; 

    int count = 1;
    Node* temp = head;
    ;
    while (temp->next != nullptr) {
        count++;
        temp = temp->next;
    }

    return count;
}

int MyList::Get(int n) {
    Node* temp = head;
    for (int i = 1; i <= n; i++)
        temp = temp->next;
    return temp->data;
}