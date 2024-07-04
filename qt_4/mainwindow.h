#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <fstream>
#include <QMainWindow>
#include <QFileDialog>
#include "json.hpp"
#include <QPen>
#include <QPainter>

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

    void on_pushButton_save_clicked();

    void on_pushButton_open_clicked();

    void updatetMaxElInDict();

private:
    Ui::MainWindow *ui;
    QPen* my_pen;
    QColor color;
    int delay{40};
    std::vector<int> dict;
    nlohmann::json nlohmann_json;
    int max_el;

};
#endif // MAINWINDOW_H
