#include "mainwindow.h"
#include "ui_mainwindow.h"

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
    nlohmann_json[ui->lineEdit_key->text().toStdString()] = ui->lineEdit_value->text().toInt();
    dict.push_back(ui->lineEdit_value->text().toInt());
    updatetMaxElInDict();
    this->update();
}


void MainWindow::on_pushButton_save_clicked()
{
    QString file = QFileDialog::getOpenFileName(0, "Open dialog", "", "*.json");
    std::ofstream file_out(file.toStdString());
    file_out << nlohmann_json.dump(4);
}


void MainWindow::on_pushButton_open_clicked()
{
    QString file_to_open = QFileDialog::getOpenFileName(0, "Open dialog", "", "*.json");
    std::fstream file_in(file_to_open.toStdString());
    if (!file_in.is_open())
        return;
    nlohmann_json = nlohmann::json::parse(file_in);
    file_in.close();
    dict.clear();
    for(auto el: nlohmann_json){
        dict.push_back(el);
    }
    updatetMaxElInDict();
    this->update();
}

void MainWindow::updatetMaxElInDict()
{
    max_el = *std::max_element(begin(dict), end(dict));
}

void MainWindow::paintEvent(QPaintEvent* event) {
    QPainter painter(this);
    painter.begin(this);

    // castomize my_pen
    my_pen->setColor(color);
    my_pen->setWidth(3);
    painter.setPen(*my_pen);

    // render
    painter.drawLine(20, this->height() - 20, this->width() - 20, this->height() - 20);
    painter.drawLine(20, this->height() - 20, 20, 88);

    my_pen->setColor(Qt::red);
    painter.setPen(*my_pen);

    int shift_x = 40;
    double hight{};
    for(auto temp_2: dict){
        hight = (temp_2 * (this->width() - 20)) / max_el * 0.9;
        painter.drawRect(20, (this->height() - 20) - shift_x, hight, 40);
        shift_x += 40;
    }
    painter.end();
}
