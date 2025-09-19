#include <windows.h>
#include <iostream>
#include <fstream>
#include <vector>
#include <thread>

typedef void (*EncryptDecriptFunc)(char*, char);

void handle_client(HANDLE hPipe, EncryptDecriptFunc EncryptDecrypt) {
    char message[256];
    DWORD bytesRead;
    while (ReadFile(hPipe, message, sizeof(message) - 1, &bytesRead, NULL)) {
        message[bytesRead] = '\0';

        EncryptDecrypt(message, 'R');

        std::cout << "get message: " << message << std::endl;
    }
    
}

int main()
{
    std::cout << "Hello World!\n";
    HINSTANCE hDLL = LoadLibrary(L"DLL.dll");
    if (!hDLL) {
        std::cout << "can't load";
        return 1;
    }

    EncryptDecriptFunc EncryptDecrypt = (EncryptDecriptFunc)GetProcAddress(hDLL, "EncryptDecrypt");
    if (!EncryptDecrypt) {
        std::cout << "problems with func\n";
        return 1;
    }

    std::vector<std::thread> client_threads; 

    while (true) {
        HANDLE hPipe = CreateNamedPipe(L"\\\\.\\pipe\\MyPipe", PIPE_ACCESS_INBOUND, 
            PIPE_TYPE_MESSAGE | PIPE_READMODE_MESSAGE | PIPE_WAIT,
            PIPE_UNLIMITED_INSTANCES,
            0,
            0,
            0,
            NULL
        );

        if (hPipe == INVALID_HANDLE_VALUE) {
            DWORD dwError = GetLastError();
            std::cerr << "CreateFile failed with error: " << dwError << std::endl;
        }

        if (ConnectNamedPipe(hPipe, NULL) != FALSE) {
            client_threads.emplace_back(handle_client, hPipe, EncryptDecrypt);
        } else CloseHandle(hPipe);
    }

    for (auto& thread : client_threads) {
        if (thread.joinable()) {
            thread.join();
        }
    }

    FreeLibrary(hDLL);
    return 0;
}
