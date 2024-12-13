#include "pch.h"
#include "encryption.h"

extern "C" __declspec(dllexport) void EncryptDecrypt(char* message, char key) {
	while (*message) {
		*message ^= key;
		message++;
	}
}