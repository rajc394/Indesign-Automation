import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView displayTextView;
    private StringBuilder input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTextView = findViewById(R.id.displayTextView);
        input = new StringBuilder();
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        String number = button.getText().toString();
        input.append(number);
        displayTextView.setText(input.toString());
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String operator = button.getText().toString();
        input.append(operator);
        displayTextView.setText(input.toString());
    }

    public void onEqualsClick(View view) {
        try {
            double result = evaluateExpression(input.toString());
            displayTextView.setText(String.valueOf(result));
        } catch (Exception e) {
            displayTextView.setText("Error");
        }
    }

    public void onClearClick(View view) {
        input.setLength(0);
        displayTextView.setText("");
    }

    private double evaluateExpression(String expression) {
        // Implement your evaluation logic here
        // You can use libraries like ExprEval, Rhino, etc. for expression evaluation
        // In this example, we'll use a simple evaluation of addition/subtraction operations

        String[] terms = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");

        double result = Double.parseDouble(terms[0]);
        for (int i = 1; i < terms.length; i += 2) {
            String operator = terms[i];
            double number = Double.parseDouble(terms[i + 1]);

            switch (operator) {
                case "+":
                    result += number;
                    break;
                case "-":
                    result -= number;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        }

        return result;
    }
}
