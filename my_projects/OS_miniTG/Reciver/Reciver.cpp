#include <windows.h>
#include <iostream>
#include <fstream>

typedef void (*EncryptDecriptFunc)(char*, char);

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

    // open named pipe to send
    HANDLE hPipe = CreateNamedPipe(
        L"\\\\.\\pipe\\MyPipe",
        PIPE_ACCESS_INBOUND, // access tp red
        PIPE_TYPE_MESSAGE | PIPE_READMODE_MESSAGE | PIPE_WAIT,
        1,
        0,
        0,
        0,
        NULL
    );

    if (hPipe != INVALID_HANDLE_VALUE) {
        if (ConnectNamedPipe(hPipe, NULL) != FALSE) { // vait connection
            char message[256];
            DWORD bytesRead;
            ReadFile(hPipe, message, sizeof(message) - 1, &bytesRead, NULL); 
            message[bytesRead] = '\0';

            EncryptDecrypt(message, 'R');

            std::cout << "get message: " << message << std::endl; 
        }
        CloseHandle(hPipe); 
    }
    
    if (hPipe == INVALID_HANDLE_VALUE) {
        DWORD dwError = GetLastError();
        std::cerr << "CreateFile failed with error: " << dwError << std::endl;
    }
    FreeLibrary(hDLL);
    return 0;
}
