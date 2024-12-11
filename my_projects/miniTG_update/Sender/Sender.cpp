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

    //open named pipe to send
    HANDLE hPipe = CreateFile(
        L"\\\\.\\pipe\\MyPipe",
        GENERIC_WRITE,
        0,
        NULL, // default security
        OPEN_EXISTING,
        0, // default attributes
        NULL // without template file
    );

    if (hPipe != INVALID_HANDLE_VALUE) {
        while (true) {
            char message[256];
            std::cout << "message: ";
            std::cin.getline(message, 256);

            if (strcmp(message, "exit") == 0) break;

            EncryptDecrypt(message, 'R');

            DWORD bytesWritten;
            WriteFile(hPipe, message, strlen(message) + 1, &bytesWritten, NULL);
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