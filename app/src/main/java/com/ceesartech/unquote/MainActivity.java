package com.ceesartech.unquote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;

    ArrayList<Question> questions;
    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTexView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        questionImageView = findViewById(R.id.quoteImage);
        questionTextView = findViewById(R.id.questionDisplay);
        questionsRemainingTexView = findViewById(R.id.questionCount);
        answer0Button = findViewById(R.id.answer1);
        answer1Button = findViewById(R.id.answer2);
        answer2Button = findViewById(R.id.answer3);
        answer3Button = findViewById(R.id.answer4);
        submitButton = findViewById(R.id.submitButton);

        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(0);
            }
        });
        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(1);
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(2);
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(3);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSubmission();
            }
        });

        startNewGame();
    }

    void displayQuestion(Question question) {
        questionImageView.setImageResource(question.imageId);
        questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
        answer1Button.setText(question.answer1);
        answer2Button.setText(question.answer2);
        answer3Button.setText(question.answer3);
    }

    void displayQuestionsRemaining(int questionsRemaining) {
        questionsRemainingTexView.setText(String.valueOf(questionsRemaining));
    }

    void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;

        answer0Button.setText(currentQuestion.answer0);
        answer1Button.setText(currentQuestion.answer1);
        answer2Button.setText(currentQuestion.answer2);
        answer3Button.setText(currentQuestion.answer3);

//        switch (answerSelected) {
//            case 0:
//                answer0Button.setText("✔ " + currentQuestion.answer0);
//                break;
//            case 1:
//                answer1Button.setText("✔ " + currentQuestion.answer1);
//                break;
//            case 2:
//                answer2Button.setText("✔ " + currentQuestion.answer2);
//                break;
//            case 3:
//                answer3Button.setText("✔ " + currentQuestion.answer3);
//                break;
//            default:
//                return;
//        }
        if (answerSelected == 0) {
            answer0Button.setText("✔ " + currentQuestion.answer0);
        }
        else if (answerSelected == 1) {
            answer1Button.setText("✔ " + currentQuestion.answer1);
        }
        else if (answerSelected == 2) {
            answer2Button.setText("✔ " + currentQuestion.answer2);
        }
        else if (answerSelected == 3) {
            answer3Button.setText("✔ " + currentQuestion.answer3);
        }
        else {
            return;
        }
    }


    // TODO #1: add integer member variables here


    // TODO #2: add ArrayList member variable here


    // TODO #3 add startNewGame() here

    void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
            totalCorrect += 1;
        }
        questions.remove(currentQuestion);
        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMessage = "Game Over " + getGameOverMessage(totalCorrect, totalQuestions);
//            System.out.println("Game Over " + getGameOverMessage(totalCorrect, totalQuestions));
            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setMessage(gameOverMessage);
            gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startNewGame();
                }
            });
            gameOverDialogBuilder.create().show();
//            startNewGame();
        }
        else {
            chooseNewQuestion();
//            chooseNewQuestion();
            // TODO: uncomment after implementing displayQuestion()displayQuestion(getCurrentQuestion());
        }
    }

    void startNewGame() {
        questions = new ArrayList<>();

        Question question0 = new Question(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it... Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
        Question question1 = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
        Question question2 = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);
        Question question3 = new Question(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);
        Question question4 = new Question(R.drawable.img_quote_4, "A peace worth striving for — who actually reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 1);
        Question question5 = new Question(R.drawable.img_quote_5, "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);

        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);

        totalCorrect = 0;
        totalQuestions = 0;

        Question firstQuestion = chooseNewQuestion();

        displayQuestionsRemaining(questions.size());
//        Log.d("String Value is: ", String.valueOf(questions.size()));

        displayQuestion(firstQuestion);
    }

//  TODO #4 add chooseNewQuestion() here

//    Question chooseNewQuestion(int maxNumber) {
//        maxNumber = questions.size();
//        currentQuestionIndex = generateRandomNumber(maxNumber);
//        return questions.get(currentQuestionIndex);
//    }

    Question chooseNewQuestion() {
        currentQuestionIndex = generateRandomNumber(questions.size());
        return questions.get(currentQuestionIndex);
    }

    // TODO #5 add getCurrentQuestion() here

    // TODO #6 add onAnswerSubmission() here

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}