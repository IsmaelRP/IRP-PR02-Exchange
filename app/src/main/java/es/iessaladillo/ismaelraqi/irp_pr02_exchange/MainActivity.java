package es.iessaladillo.ismaelraqi.irp_pr02_exchange;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.ismaelraqi.irp_pr02_exchange.Utils.KeyboardUtils;
import es.iessaladillo.ismaelraqi.irp_pr02_exchange.Utils.SnackbarUtils;

public class MainActivity extends AppCompatActivity {

    private EditText txtAmount;
    private RadioButton rbFromEuro;
    private RadioButton rbFromDollar;
    private RadioButton rbFromPound;
    private RadioButton rbToEuro;
    private RadioButton rbToDollar;
    private RadioButton rbToPound;
    private ImageView imgFrom;
    private ImageView imgTo;
    private RadioGroup rdGroupFrom;
    private RadioGroup rdGroupTo;

    private static final double EURO_DOLLAR = 1.77;
    private static final double EURO_POUND = 0.88;

    private static final double DOLLAR_EURO = 0.86;
    private static final double DOLLAR_POUND = 0.77;

    private static final double POUND_EURO = 1.13;
    private static final double POUND_DOLLAR = 1.32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        Button btnExchange;

        txtAmount = ActivityCompat.requireViewById(this, R.id.txtAmount);

        txtAmount.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s)  {
                if (txtAmount.getText().toString().length() <= 0) {
                    txtAmount.setError("You have to specify a number");
                } else {
                    txtAmount.setError(null);
                }
            }
        });

        rdGroupFrom = ActivityCompat.requireViewById(this, R.id.rdGroupFrom);
        rbFromEuro = ActivityCompat.requireViewById(this, R.id.rbFromEuro);
        rbFromDollar = ActivityCompat.requireViewById(this, R.id.rbFromDollar);
        rbFromPound = ActivityCompat.requireViewById(this, R.id.rbFromPound);

        rdGroupTo = ActivityCompat.requireViewById(this, R.id.rdGroupTo);
        rbToEuro = ActivityCompat.requireViewById(this, R.id.rbToEuro);
        rbToDollar = ActivityCompat.requireViewById(this, R.id.rbToDollar);
        rbToPound = ActivityCompat.requireViewById(this, R.id.rbToPound);

        imgFrom = ActivityCompat.requireViewById(this, R.id.imgFrom);
        imgTo = ActivityCompat.requireViewById(this, R.id.imgTo);

        btnExchange = ActivityCompat.requireViewById(this, R.id.btnExchange);

        btnExchange.setOnClickListener(v -> exchange());
        txtAmount.setOnClickListener(v -> txtAmount.selectAll());

        rbFromEuro.setOnClickListener(v -> {
            rbToEuro.setEnabled(false);
            rbToPound.setEnabled(true);
            rbToDollar.setEnabled(true);
            imgFrom.setImageResource(R.drawable.ic_euro);
        });

        rbFromDollar.setOnClickListener(v -> {
            rbToEuro.setEnabled(true);
            rbToPound.setEnabled(true);
            rbToDollar.setEnabled(false);
            imgFrom.setImageResource(R.drawable.ic_dollar);
        });

        rbFromPound.setOnClickListener(v -> {
            rbToEuro.setEnabled(true);
            rbToPound.setEnabled(false);
            rbToDollar.setEnabled(true);
            imgFrom.setImageResource(R.drawable.ic_pound);
        });

        rbToEuro.setOnClickListener(v -> {
            rbFromEuro.setEnabled(false);
            rbFromPound.setEnabled(true);
            rbFromDollar.setEnabled(true);
            imgTo.setImageResource(R.drawable.ic_euro);
        });

        rbToDollar.setOnClickListener(v -> {
            rbFromEuro.setEnabled(true);
            rbFromPound.setEnabled(true);
            rbFromDollar.setEnabled(false);
            imgTo.setImageResource(R.drawable.ic_dollar);
        });

        rbToPound.setOnClickListener(v -> {
            rbFromEuro.setEnabled(true);
            rbFromPound.setEnabled(false);
            rbFromDollar.setEnabled(true);
            imgTo.setImageResource(R.drawable.ic_pound);
        });
    }

    private void exchange() {
        String message;
        KeyboardUtils.HideKeyboard(this);

        if (txtAmount.getText().toString().isEmpty() || !txtAmount.getText().toString().matches(getString(R.string.amount_matches))) {
            txtAmount.setText(getString(R.string.txt_defaultValue));

        } else {
            if (rdGroupFrom.getCheckedRadioButtonId() == rbFromEuro.getId()) {
                message = getFromEuro();

            } else if (rdGroupFrom.getCheckedRadioButtonId() == rbFromDollar.getId()) {
                message = getFromDollar();

            } else {
                message = getFromPound();
            }
            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            SnackbarUtils.snackbar(this.getCurrentFocus(), message, Snackbar.LENGTH_LONG);
        }
    }

    @SuppressLint("DefaultLocale")
    private String getFromEuro() {

        double amount = Double.parseDouble(txtAmount.getText().toString());
        double result;
        String message;

        if (rdGroupTo.getCheckedRadioButtonId() == rbToDollar.getId()) {
            result = amount * EURO_DOLLAR;
            message = getString(R.string.euro_dollar_string, amount, result);
        } else {
            result = amount * EURO_POUND;
            message = getString(R.string.euro_pound_string, amount, result);
        }
        return message;
    }

    @SuppressLint("DefaultLocale")
    private String getFromDollar() {
        double amount = Double.parseDouble(txtAmount.getText().toString());
        double result;
        String message;
        if (rdGroupTo.getCheckedRadioButtonId() == rbToEuro.getId()) {
            result = amount * DOLLAR_EURO;
            message = getString(R.string.dollar_euro_string, amount, result);
        } else {
            result = amount * DOLLAR_POUND;
            message = getString(R.string.dollar_pound_string, amount, result);
        }
        return message;
    }

    @SuppressLint("DefaultLocale")
    private String getFromPound() {
        double amount = Double.parseDouble(txtAmount.getText().toString());
        double result;
        String message;

        if (rdGroupTo.getCheckedRadioButtonId() == rbToEuro.getId()) {
            result = amount * POUND_EURO;
            message = getString(R.string.pound_euro_string, amount, result);
        } else {
            result = amount * POUND_DOLLAR;
            message = getString(R.string.pound_dollar_string, amount, result);
        }
        return message;
    }
}
