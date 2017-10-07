package com.example.abc.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button ansButton1;
    private Button ansButton2;
    private Button ansButton3;
    private Button ansButton4;
    private String rightAnswer;
    private int rightAnswerCount=0;
    private int quizCount=1;
    private final int QUIZ_COUNT=5;
    ArrayList<ArrayList<String>> quizArray =new ArrayList<>();
    String quizData [][]={
            //{"Country","RightAnswer","Choice1","Choice2","Choice3};
            {"China","Beijing","Jakarta","Manila","Stockholm"},
            {"India","New Delhi","Beijing","Seoul","Bankok"},
            {"Indonesia","Jakarta","Manila","New Delhi","Quala Lampur"},
            {"Japan","Tokyo","Bankok","Jakarta","Taipei"},
            {"Thailand","Bankok","Havana","Kingston","Berlin"},
            {"Brazil","Brasilia","Bankok","Havana","Kingston"},
            {"Canada","Ottawa","Copenhagen","Jakarta","Bern"},
            {"Cuba","Havana","Bern","London","Mexico City"},
            {"Mexico","Mexico City","Santiago","Ottawa","Berlin"},
            {"United States","Wahington Dc","San Jose","Beunos Aires","Kuala Lampur"},
            {"France","Paris","Ottawa","Copenhagen","Tokyo"},
            {"Germany","Berlin","Santiago","Copenhagen","Bankok"},
            {"Italy","Rome","London","Paris","Athens"},
            {"Spain","Madrid","Mexico City","Jakarta","Havana"},
            {"United Kingdon","London","Singapore","Rome","Paris"}
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        ansButton1 = (Button)findViewById(R.id.ansButton1);
        ansButton2 = (Button)findViewById(R.id.ansButton2);
        ansButton3 = (Button)findViewById(R.id.ansButton3);
        ansButton4 = (Button)findViewById(R.id.ansButton4);

        //Create Quiz Array from Quiz Data
        for (int i=0;i<quizData.length;i++){
            ArrayList<String> tmpArray=new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            // add tmpArray to QuiArray
            quizArray.add(tmpArray);
        }
        showNextQuiz();
     }
     public  void showNextQuiz(){
         countLabel.setText("Q" + quizCount);
         //Generating a random number between 0 and quizArray's length -1
         Random random =new Random();
         int randomNum=random.nextInt(quizArray.size());
         ArrayList<String> Quiz = quizArray.get(randomNum);
         questionLabel.setText("Capital of" + Quiz.get(0) + "? ");
         rightAnswer=Quiz.get(1);
         Quiz.remove(0);
         Collections.shuffle(Quiz);
         ansButton1.setText(Quiz.get(0));
         ansButton2.setText(Quiz.get(1));
         ansButton3.setText(Quiz.get(2));
         ansButton4.setText(Quiz.get(3));

         quizArray.remove(randomNum);
     }

     public void checkAnswer(View view){
         Button ansButton=(Button) findViewById(view.getId());
         String btntxt= ansButton.getText().toString();
         String alertTitle;
         if(btntxt.equals(rightAnswer)){
             alertTitle="Correct!";
             rightAnswerCount++;
         }
         else{
             alertTitle="Incorrect";
         }
         AlertDialog.Builder builder=new AlertDialog.Builder(this);
         builder.setTitle(alertTitle);
         builder.setMessage("Answer: " + rightAnswer);
         builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                      if (quizCount == QUIZ_COUNT){
                          //show result
                          Intent intent=new Intent(getApplicationContext(),resultActivity.class);
                          intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                          startActivity(intent);
                      }
                      else {
                          quizCount++;
                          showNextQuiz();
                      }

             }
         });
         builder.setCancelable(false);
        builder.show();


     }

}
