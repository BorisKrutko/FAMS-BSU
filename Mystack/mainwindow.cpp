#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "mystack.h"
#include <QPainter>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    my_pen = new QPen();
    color = Qt::black;
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_pushButton_add_clicked()
{
    objekt->pushFistt((ui->lineEdit_add->text()).toInt());
}


void MainWindow::on_pushButton_pop_clicked()
{
    objekt->popFirst();
}


void MainWindow::on_pushButton_print_clicked()
{
    vec_stack_el = objekt->stackToVec();
    update();
}

void MainWindow::paintEvent(QPaintEvent* event) {

    // find max value
    int max_value{};
    for(auto temp_1: vec_stack_el) {
        max_value = std::max(temp_1, max_value);
    }

    QPainter painter(this);
    painter.begin(this);

    // castomize my_pen
    my_pen->setColor(color);
    my_pen->setWidth(3);
    painter.setPen(*my_pen);

    // render
    painter.drawLine(20, this->height() - 20, this->width() - 20, this->height() - 20);
    painter.drawLine(20, this->height() - 20, 20, 20);

    my_pen->setColor(Qt::red);
    painter.setPen(*my_pen);

    int shift_x = 40;
    double hight{};
    for(auto temp_2: vec_stack_el){
        hight = (temp_2 * (this->width() - 20)) / max_value;
        painter.drawRect(20, (this->height() - 20) - shift_x, hight - 40, 40);
        shift_x += 40;
    }
    painter.end();
}
