DWORD WINAPI InstanceThread(LPVOID lpvParam) {
    HANDLE hPipe = (HANDLE)lpvParam;
    char message[256];
    DWORD bytesRead;

    if (ReadFile(hPipe, message, sizeof(message) - 1, &bytesRead, NULL)) {
        message[bytesRead] = '\0';
        EncryptDecrypt(message, 'K'); // Дешифрование сообщения
        std::cout << "Received message: " << message << std::endl;
    }

    CloseHandle(hPipe);
    return 0;
}

int main() {
    while (true) {
        HANDLE hPipe = CreateNamedPipe(
            "\\\\.\\pipe\\MyPipe",
            PIPE_ACCESS_INBOUND,
            PIPE_TYPE_MESSAGE | PIPE_READMODE_MESSAGE | PIPE_WAIT,
            PIPE_UNLIMITED_INSTANCES,
            0,
            0,
            0,
            NULL
        );

        if (hPipe != INVALID_HANDLE_VALUE) {
            if (ConnectNamedPipe(hPipe, NULL) != FALSE) {
                CreateThread(NULL, 0, InstanceThread, (LPVOID)hPipe, 0, NULL);
            } else {
                CloseHandle(hPipe);
            }
        }
    }
    return 0;
}
