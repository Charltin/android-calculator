package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  public ActivityMainBinding binding; // 组件存储类
  private MyViewModel myViewModel; // 数据模型

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 获取所有控件
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    // 获取数据模型
    myViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

    // 事件监听
    myViewModel.getMainNum().observe(this, new Observer<String>() {
      @Override
      public void onChanged(String s) {
        // 监听数据发生改变 让 myTextView 显示 mainNum 的数据
        binding.myTextView.setText(myViewModel.getMainNum().getValue());

        // 让 textView 显示式子
        if(myViewModel.operator2.equals("")) {
          // 第二个符号位为空
          if(myViewModel.operator.equals("")) {
            // 第一个符号位为空
            binding.textView.setText(myViewModel.getMainNum().getValue());
          } else {
            // 第一个符号位不为空
            binding.textView.setText(myViewModel.num[0] + myViewModel.operator + myViewModel.getMainNum().getValue());
          }
        } else {
          // 第二个符号位不为空 类似 a + b * c 的式子
          binding.textView.setText(myViewModel.num[0] + myViewModel.operator + myViewModel.num[1] + myViewModel.operator2 + myViewModel.getMainNum().getValue());
        }
      }
    });
    binding.button0.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("0");
      }
    });
    binding.button1.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("1");
      }
    });
    binding.button2.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("2");
      }
    });
    binding.button3.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("3");
      }
    });
    binding.button4.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("4");
      }
    });
    binding.button5.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("5");
      }
    });
    binding.button6.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("6");
      }
    });
    binding.button7.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("7");
      }
    });
    binding.button8.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("8");
      }
    });
    binding.button9.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myViewModel.setMainNum("9");
      }
    });
    binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(!myViewModel.havePoint) {
          myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue() + ".");
          myViewModel.havePoint = true;
        }
      }
    });
    binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(myViewModel.operator.equals("")) {
          myViewModel.operator = "+";
          myViewModel.num[0] = myViewModel.getMainNum().getValue();
        } else if(myViewModel.operator2.equals("")) {
          // 第二个操作符为空
          myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
          myViewModel.operator = "+";
        } else {
          myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Total());
          myViewModel.operator2 = "";
          myViewModel.num[1] = "";
          myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
          myViewModel.operator = "+";
        }
        myViewModel.getMainNum().setValue("0");
        myViewModel.havePoint = false;
      }
    });
    binding.buttonSub.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(myViewModel.operator.equals("")) {
          myViewModel.operator = "-";
          myViewModel.num[0] = myViewModel.getMainNum().getValue();
        } else if(myViewModel.operator2.equals("")) {
          // 第二个操作符为空
          myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
          myViewModel.operator = "-";
        } else {
          myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Total());
          myViewModel.operator2 = "";
          myViewModel.num[1] = "";
          myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
          myViewModel.operator = "-";
        }
        myViewModel.getMainNum().setValue("0");
        myViewModel.havePoint = false;
      }
    });
    binding.buttonMulti.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(myViewModel.operator.equals("")) {
          myViewModel.operator = "*";
          myViewModel.num[0] = myViewModel.getMainNum().getValue();
          myViewModel.getMainNum().setValue("0");
          myViewModel.havePoint = false;
        } else if(myViewModel.operator2.equals("")) {
          if(myViewModel.operator.equals("*") || myViewModel.operator.equals("/")) {
            // 按顺序进行计算
            myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
            myViewModel.operator = "*";
          } else {
            // operator 是加号或者减号
            myViewModel.num[1] = myViewModel.getMainNum().getValue();
            myViewModel.operator2 = "*";
          }
          myViewModel.getMainNum().setValue("0");
          myViewModel.havePoint = false;
        } else {
          // 类似 a + b * c
          myViewModel.num[1] = myViewModel.mainNumWithNum_1_Total();
          myViewModel.operator2 = "*";
          myViewModel.getMainNum().setValue("0");
          myViewModel.havePoint = false;
        }
      }
    });
    binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(myViewModel.operator.equals("")) {
          myViewModel.operator = "/";
          myViewModel.num[0] = myViewModel.getMainNum().getValue();
          myViewModel.getMainNum().setValue("0");
          myViewModel.havePoint = false;
        } else if(myViewModel.operator2.equals("")) {
          if(myViewModel.operator.equals("*") || myViewModel.operator.equals("/")) {
            // 按顺序进行计算
            myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
            myViewModel.operator = "/";
          } else {
            // operator 是加号或者减号
            myViewModel.num[1] = myViewModel.getMainNum().getValue();
            myViewModel.operator2 = "/";
          }
          myViewModel.getMainNum().setValue("0");
          myViewModel.havePoint = false;
        } else {
          // 类似 a + b * c
          myViewModel.num[1] = myViewModel.mainNumWithNum_1_Total();
          myViewModel.operator2 = "/";
          myViewModel.getMainNum().setValue("0");
          myViewModel.havePoint = false;
        }
      }
    });
    binding.buttonClear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        myViewModel.operator2 = "";
        myViewModel.num[1] = "";
        myViewModel.operator = "";
        myViewModel.num[0] = "";
        myViewModel.getMainNum().setValue("0");
        myViewModel.havePoint = false;
      }
    });
    binding.buttonRes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(myViewModel.operator2.equals("")) {
          if(!myViewModel.operator.equals("")) {
            myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Total());
            if(myViewModel.getMainNum().getValue().contains(".")) {
              myViewModel.havePoint = true;
            } else {
              myViewModel.havePoint = false;
            }
            myViewModel.num[0] = "";
            myViewModel.operator = "";
          }
        } else {
          // 像 a + b * c 这种式子
          myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Total());
          myViewModel.num[1] = "";
          myViewModel.operator2 = "";
          myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Total());
          if(myViewModel.getMainNum().getValue().contains(".")) {
            myViewModel.havePoint = true;
          } else {
            myViewModel.havePoint = false;
          }
          myViewModel.num[0] = "";
          myViewModel.operator = "";
        }
        binding.textView.setText(myViewModel.getMainNum().getValue());
      }
    });
    binding.imageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(!myViewModel.getMainNum().getValue().equals("0")) {
          myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
          if(myViewModel.getMainNum().getValue().equals("")) {
            myViewModel.getMainNum().setValue("0");
          }
        }
      }
    });
  }
}