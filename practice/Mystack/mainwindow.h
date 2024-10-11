#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include "mystack.h"
#include <QMainWindow>
#include <QPen>


QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

protected:
    void paintEvent(QPaintEvent*);

private slots:
    void on_pushButton_add_clicked();

    void on_pushButton_pop_clicked();

    void on_pushButton_print_clicked();

private:
    std::vector<int> vec_stack_el;
    MyStack* objekt = new MyStack();
    Ui::MainWindow *ui;
    QPen* my_pen;
    QColor color;
    int delay{40};
};
#endif // MAINWINDOW_H
