
#include <iostream>
#include <conio.h>
#include <windows.h>
#include <ctime>
#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <chrono>
#include <thread>
#include <locale>
#include <codecvt>
#include <string>
#ifdef _WIN32
#include <windows.h> // Для функции Sleep() на Windows
#define SLEEP(ms) Sleep(ms)
#else
#include <unistd.h> // Для функции usleep() на Unix-подобных системах
#define SLEEP(ms) usleep((ms) * 1000)
#endif

void gotoxy(int x, int y) {
    std::cout << "\033[" << y << ";" << x << "H";
}

void setcolor(int color) {
    std::cout << "\033[" << color << "m";
}

void round1() {
    std::locale::global(std::locale("ru_RU.UTF-8"));
    std::wcout.imbue(std::locale());
    std::wcout << L"\n \n \n \n \n";
    std::wcout << L"    Привет дорогой друг! Давай сыграем в игру)) Нажми Enter!" << std::endl;
    std::cin.get();
    std::wcout << L"    Тебе не победить))) Ахахаха" << std::endl << std::endl;
    std::wcout << L"    Задача №1" << std::endl;
    std::wstring str = L"Ваша задача вывести код из 4 цифр, за каждый пройденный раунд вы получаете одну цифру.\n"
    L"Правила игры:\n"
        L"В коробе у стенки возьмите 4 ракетки и поделитесь на 2 группы (каждой группе по 2 ракетки). Играем в воздушный теннис!!\n"
        L"Стратегия: ударил - поменялся.\n"
        L"Т.е. вам нужно набить теннисным мячом столько раз, сколько у вас игроков.\n"
        L"Расстояние между выбивающими 2 метра.\n"
        L"Чтобы узнать цифру, вы должны ввести номер попытки и попросить аниматора подтвердить, что вы выполнили правильно.\n\n";
    for (auto el : str) {
        std::wcout << el;
        std::this_thread::sleep_for(std::chrono::milliseconds(20));  // Пауза на 0.05 секунды
    }
    int code{ 1647 };
    int codeArr[4]{ 1, 6, 4, 7 };
    int numb, codeAnima;
    for (int i = 0; i < 4; i++) {
        std::wcout << L"введите номер цифры:  ";
        std::cin >> numb;
        std::wcout << L"подтверждение:  ";
        std::cin >> codeAnima;
        if (codeAnima == 1111) {
            int number = numb;
            if (numb <= 0 and numb >= 5) std::wcout << "eror:(\n";
            else {
                std::wcout << "number: " << codeArr[numb - 1] << L"  Кол-во попыток: " << 3 - i << "\n";
            }
        }
        else {
            std::wcout << L"грабиииитель!!!\n";
        }
    }
    do {
        std::wcout << L"введите код доступа: ";
        std::cin >> numb;
    } while (numb != 1647);
    std::cout << "\n------------------------------------------------------------------------------------------------------\n------------------------------------------------------------------------------------------------------\n";
    str = L"мы будем ишрать в 2 капитана \n Правила игры:\n"
        L"1. У каждой команды есть по 3 капитана : 1 главный и 2 дополнительных(это люди, выбранные командой).\n"
        L"2. Чтобы победить, необходимо выбить всех противников и в конце капитанов.\n"
        L"3. На начало игры команда становится в свою площадку, а капитаны занимают места по периметру противоположной команды.\n"
        L"4. Необходимо выбить сначала всех, кто находится в площадке, а потом вышедших 3 капитанов.\n"
        L"5. Как только одного из команды выбивают, он занимает положение рядом с главным капитаном своей команды.\n";
    for (auto el : str) {
        std::wcout << el;
        std::this_thread::sleep_for(std::chrono::milliseconds(10));  // Пауза на 0.05 секунды
    }
}

void round2() {
    std::locale::global(std::locale("ru_RU.UTF-8"));
    std::wcout.imbue(std::locale());
    std::wcout << L"хотите продолжить!? (enter)";
    std::cin.get();
    std::wcout << L"ППППППППравила Игры:";
    SLEEP(400);
    std::srand(static_cast<unsigned int>(std::time(nullptr)));
    const int width = 250; // Ширина консоли
    const int height = 20; // Высота консоли
    std::vector<int> drops(width, 0);

    std::system("clear"); // Очистка экрана на старте (для Unix-подобных систем)
#ifdef _WIN32
    std::system("cls"); // Очистка экрана на старте (для Windows)
#endif
    int ind = 0;
    while (ind < 22) {
        // Проходим по каждому столбцу
        for (int i = 0; i < width; ++i) {
            // Определяем случайный символ и высоту падения
            char ch = '!' + std::rand() % 94;
            int color = 32 + std::rand() % 8; // Зеленый цвет и его оттенки

            // Рисуем символ в случайной позиции
            gotoxy(i + 1, drops[i]);
            setcolor(color);
            std::cout << ch;
            drops[i] = (drops[i] + 1) % height;

            // Очищаем следы на краю экрана
            setcolor(30);
            if (drops[i] == 0) {
                for (int j = 0; j < height; ++j) {
                    gotoxy(i + 1, j);
                    std::cout << " ";
                }
            }
        }
        ind++;

        SLEEP(1); // Задержка для создания эффекта анимации
        std::cout.flush(); // Принудительное обновление буфера вывода
    }
    std::wstring str = L"\n\n\nваша задача расшифровать исходный код:\n"
        L"ЧТО... НЕ УСПЕЛИ ЗАПИСАТЬ\n"
        L"так как времени шутить остаётся всё меньше, препереходим к настоящим ПРАВИЛАМ\n\n"
        L"конкус №2 вышка\n"
        L"по центру стоит ведро и аниматор с канатом, задача команды положить сначала все мячи в ведро, вернуться обратно и заново забрать.\n"
        L"Если игрока касается канат, то он погибает и его мяч должен доставить другой игрок.\n"
        L" - команда побеждает, если выполняет задание\n"
        L"ЕСЛИ УСПЕШНО ВЫПОЛНИТЕ ЗАДАНИЕ, ВАМ НУЖНО БУДЕТ НАЖАТЬ НА СЕКРЕТНУЮ КЛАВИШУ (У ВАС ВСЕГО ОДНА ПОПЫТКА), ПОСЛЕ ЧЕГО ВАМ НУЖНО БУДЕТ В ТЕЧЕНИИ .. РЕШИТЬ ЗАДАЧКУ\n";
    int color = 32 + std::rand() % 8;
    for (auto el : str) {
        setcolor(color);
        std::wcout << el;
        std::this_thread::sleep_for(std::chrono::milliseconds(10));  // Пауза на 0.05 секунды
    }
    char code;
    std::wcout << L"\n БУКВА:";
    std::cin >> code;
    int color1 = 32 + std::rand() % 8;
    if (code == 'b') {
        str = L"\n\n\nУСЛОВИЕ ЗАГАДКИ (у вас есть .. с)\n"
            L"условия\n";
        for (auto el : str) {
            setcolor(color1);
            std::wcout << el;
            std::this_thread::sleep_for(std::chrono::milliseconds(10));  // Пауза на 0.05 секунды
        }
        SLEEP(500);
        system("CLS");
        int number;
        ;       std::wcout << L"ОТВЕТ:  ";
        std::cin >> number;
        if (number == 123) {

        }
        else {

        }
    }
    else {
        color = 32 + std::rand() % 8;
        std::wcout << L"ВИДИМО без условий";
        str = L"\nахахахахахаххахахахахаххахахаххахахаххахахахаххахахахахаххаххахахаххахахаххах\n";
        for (auto el : str) {
            int color = 32 + std::rand() % 8;
            setcolor(color);
            std::wcout << el;
            std::this_thread::sleep_for(std::chrono::milliseconds(10));  // Пауза на 0.05 секунды
        }
    }
}
void ClearScreen() {
    COORD topLeft = { 0, 0 };
    HANDLE console = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_SCREEN_BUFFER_INFO screen;
    DWORD written;

    GetConsoleScreenBufferInfo(console, &screen);
    FillConsoleOutputCharacterA(console, ' ', screen.dwSize.X * screen.dwSize.Y, topLeft, &written);
    FillConsoleOutputAttribute(console, FOREGROUND_GREEN | FOREGROUND_RED | FOREGROUND_BLUE, screen.dwSize.X * screen.dwSize.Y, topLeft, &written);
    SetConsoleCursorPosition(console, topLeft);
}

void SetCursorPosition1(int x, int y) {
    COORD coord;
    coord.X = x;
    coord.Y = y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void DrawLongKey() {
    const char* key[] = {
        "    ____",
        "   /    \\",
        "  | ||  | | ?   ?????????????? ? ???  ?????? ?????? ???",
        "  | ||  | | ????  ?? ? ? ?? ? ? ??????????????? ? ? ? ?",
        "  | ||  | |",
        "   \\____/",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||",
        "     ||-",
        "     |--|",
        "     |--|",
        "     ||-" };

    int startX = 10;
    int startY = 5;
    int color = 32 + std::rand() % 8;
    setcolor(color);
    for (int i = 0; i < 20; ++i) { 
        SetCursorPosition1(startX, startY + i);
        std::wcout << key[i];
    }
}

void SetCursorPosition(int x, int y) {
    COORD coord;
    coord.X = x;
    coord.Y = y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void PrintRaund3() {
    const char* raund3[] = {
        "  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  ",
        " /  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\",
        "/    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\",
        "\\    /    /    /    /    /    /    /    /    /    /    /    /    /",
        " \\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /",
        "  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/",
        "  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  ",
        " /  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\",
        "/    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\",
        "\\    /    /    /    /    /    /    /    /    /    /    /    /    /",
        " \\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /",
        "  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/",
        "  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  ",
        " /  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\",
        "/    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\    \\",
        "\\    /    /    /    /    /    /    /    /    /    /    /    /    /",
        " \\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /\\  /",
        "  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/  \\/\n\n"
    };

    int startX = 10;
    int startY = 5;

    int color = 32 + std::rand() % 8;
    setcolor(color);
    for (int i = 0; i < 18; ++i) {
        SetCursorPosition(startX, startY + i);
        std::cout << raund3[i];
    }
}

void round3() {
    // Установка локали для вывода русских символов
    SetConsoleOutputCP(CP_UTF8);
    std::wcout.imbue(std::locale(std::locale(), new std::codecvt_utf8<wchar_t>));
    std::wcout << L"чтобы перейти к следующему раунду просьба нажать enter";
    std::wcin.get();
    PrintRaund3();
    std::wstring str = L"правда красивая картина, это я сам нарисовал!\n ладненько, давайте к теме, в зале по моему распоряжению приклеили несколько картин\n ладно шучу, просто квадратиков. Ваша задача:\n пара игроков становится напротив друг друга не заходя в квадрат, игрокам даётся по две плотные майки,\n каждый берётся за край, задача затянуть игрока в квадрат хотя бы одной ногой (любое касание квадрата считается)\n т.е. вы играете против своих соперников, находите пару из противоположной команды и соревнуйтесь, чья команда соберет\n больше очков, та и получает ключ";
    for (auto el : str) {
        std::wcout << el;
        std::this_thread::sleep_for(std::chrono::milliseconds(12));
    }
    std::wcout << L"\nнажмите enter, чтобы узнать что за ключ..";
    std::cin.get();
    ClearScreen();
    DrawLongKey();
    std::wstring str1 = L"    ключём в данном случае является комбинация ? с пробелами, листик с ключом вы получите в случае победы, удачи";
    for (auto el : str1) {
        std::wcout << el;
        std::this_thread::sleep_for(std::chrono::milliseconds(12));
    }
    std::wcout << L"\nKey: ";
    std::wcin >> str1;
    if (str1 == L"?    ?") {
        std::wcout << L"\nПоздравляю, вы ввели правильный ключ!";
    }
}

void printStars(int count) {
    for (int i = 0; i < count; ++i) {
        std::cout << "*";
    }
    std::cout << std::endl;
}

void clearScreen() {
    std::cout << "\033[2J\033[1;1H"; // ANSI escape code to clear the screen
}

void round4() {
    SetConsoleOutputCP(CP_UTF8);
    std::wcout.imbue(std::locale(std::locale(), new std::codecvt_utf8<wchar_t>));
    std::wcout << L"round 4! вы готовы!? если да, то скорее жми enter";
    std::wcin.get();
    
    int starCount = 1;
    while (starCount <= 500) {
        clearScreen();
        printStars(starCount);
        std::this_thread::sleep_for(std::chrono::milliseconds(5)); // Pause for 500 milliseconds
        starCount++;
    }

    clearScreen();
    std::cout << "round4" << std::endl;
    std::wstring str = L"Ну и сколько звёзд насчитал!? ...... а их было 500)) \n вот не умеют теперь подростки считать..\n"
        L"ну теперь считать и не придётся. Првила:\n"
        L"конкус №4 уловка (команда на команду)\n"
        L"каждая команда делится по 4 человека, команды соревнуются по четвекам(т.е.играют 8 игроков).\n"
        L"Они становятся за стол и кладут две руки на край.Игрокам даётся теннисный мяч.\n"
        L"Цель игры : выбить всех проивников. Как же это сделать ?!\n"
        L" - когда у тебя мячик, ты можешь его катать 2-мя руками по столу и в какой-то момент передать его другому.\n"
        L"Если кто-то подымает руки со стола, но ему не давали пас и наоборот, игрок не успел его словить, этот человек выбывает.\n"
        L"за каждый выигрышный стол команда получает + 1 очко, у кого больше тот и победил)\n"
        L"\nЕсли команда побеждает она вытягивает возможность поучавствовать в конкурсе, чтобы получить часть правил!";
    int color = 32 + std::rand() % 8;
    setcolor(color);
    for (auto el : str) {
        std::wcout << el;
        std::this_thread::sleep_for(std::chrono::milliseconds(20));
    }
    
}

int main() {
    //round1();
    //round2();
    //round3();
    round4();
    return 0;
}
