package com.example.calculator;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

// 用于储存数据模型
public class MyViewModel extends ViewModel {
    // 主数值（用户正在操作的数）
    private MediatorLiveData<String> mainNum;

    // 参与计算的数值
    public String num[] = {"", ""};

    // 运算符号
    public String operator = "";
    public String operator2 = "";

    // 主数值中是否包含小数点
    public boolean havePoint = false;

    public MediatorLiveData<String> getMainNum() {
        if(mainNum == null) {
            mainNum = new MediatorLiveData<>();
            mainNum.setValue("0");
        }
        return mainNum;
    }
    public void setMainNum(String n) {
        if(mainNum.getValue().equals("0")) {
            mainNum.setValue(n);
        } else {
            mainNum.setValue(mainNum.getValue() + n);
        }
    }
    public String mainNumWithNum_0_Total() {
        String value = "0";
        if(mainNum.getValue().contains(".") || num[0].contains(".")) {
            // 如果两个数的其中一个有小数点
            switch (operator) {
                case "+":
                    value = String.valueOf(Double.valueOf(num[0]) + Double.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Double.valueOf(num[0]) - Double.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Double.valueOf(num[0]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0")) {
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
            }
        } else {
            // 如果两个数不都是小数
            switch (operator) {
                case "+":
                    value = String.valueOf(Integer.valueOf(num[0]) + Integer.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Integer.valueOf(num[0]) - Integer.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Integer.valueOf(num[0]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) {
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
            }
        }
        return value;
    }
    public String mainNumWithNum_1_Total() {
        String value = "0";
        if(mainNum.getValue().contains(".") || num[1].contains(".")) {
            // 如果两个数的其中一个有小数点
            switch (operator2) {
                case "*":
                    value = String.valueOf(Double.valueOf(num[1]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0")) {
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
            }
        } else {
            // 如果两个数不都是小数
            switch (operator2) {
                case "*":
                    value = String.valueOf(Integer.valueOf(num[1]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) {
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
            }
        }
        return value;
    }
}
