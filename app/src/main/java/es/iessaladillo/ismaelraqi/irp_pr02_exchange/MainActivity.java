package es.iessaladillo.ismaelraqi.irp_pr02_exchange;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText txtAmount;
    private RadioButton rbFromEuro;
    private RadioButton rbFromDollar;
    private RadioButton rbFromPound;
    private RadioButton rbToEuro;
    private RadioButton rbToDollar;
    private RadioButton rbToPound;
    private ImageView imgFrom;
    private ImageView imgTo;
    private Button btnExchange;
    private RadioGroup rdGroupFrom;
    private RadioGroup rdGroupTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    protected void initViews(){     // Para poner "RequiresViewById" tiene que ejecutarse en una versión 28 (actual 21)
        txtAmount = findViewById(R.id.txtAmount);

        rdGroupFrom = findViewById(R.id.rdGroupFrom);
        rbFromEuro = findViewById(R.id.rbFromEuro);
        rbFromDollar = findViewById(R.id.rbFromDollar);
        rbFromPound = findViewById(R.id.rbFromPound);

        rdGroupTo = findViewById(R.id.rdGroupTo);
        rbToEuro = findViewById(R.id.rbToEuro);
        rbToDollar = findViewById(R.id.rbToDollar);
        rbToPound = findViewById(R.id.rbToPound);

        imgFrom = findViewById(R.id.imgFrom);
        imgTo = findViewById(R.id.imgTo);

        btnExchange = findViewById(R.id.btn_exchange);

        btnExchange.setOnClickListener(this);
        txtAmount.setOnClickListener(this);

        rbFromEuro.setOnClickListener(this);
        rbFromDollar.setOnClickListener(this);
        rbFromPound.setOnClickListener(this);

        rbToEuro.setOnClickListener(this);
        rbToDollar.setOnClickListener(this);
        rbToPound.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnExchange.getId()){
            if (txtAmount.getText().toString().isEmpty() || !txtAmount.getText().toString().matches(getString(R.string.amount_matches))){
                txtAmount.setText("0.00");

            }else{
                exchange();
            }
        }else if(v.getId() == txtAmount.getId()){
            txtAmount.selectAll();

        }else{

            if (v.getId() == rbFromEuro.getId()){
                rbToEuro.setEnabled(false);
                rbToPound.setEnabled(true);
                rbToDollar.setEnabled(true);

                imgFrom.setImageResource(R.drawable.ic_euro);

            }else if (v.getId() == rbFromDollar.getId()){
                rbToEuro.setEnabled(true);
                rbToPound.setEnabled(true);
                rbToDollar.setEnabled(false);

                imgFrom.setImageResource(R.drawable.ic_dollar);

            }else if (v.getId() == rbFromPound.getId()){
                rbToEuro.setEnabled(true);
                rbToPound.setEnabled(false);
                rbToDollar.setEnabled(true);

                imgFrom.setImageResource(R.drawable.ic_pound);

            }else if (v.getId() == rbToEuro.getId()){
                rbFromEuro.setEnabled(false);
                rbFromPound.setEnabled(true);
                rbFromDollar.setEnabled(true);

                imgTo.setImageResource(R.drawable.ic_euro);

            }else if (v.getId() == rbToDollar.getId()){
                rbFromEuro.setEnabled(true);
                rbFromPound.setEnabled(true);
                rbFromDollar.setEnabled(false);

                imgTo.setImageResource(R.drawable.ic_dollar);

            }else if (v.getId() == rbToPound.getId()){
                rbFromEuro.setEnabled(true);
                rbFromPound.setEnabled(false);
                rbFromDollar.setEnabled(true);

                imgTo.setImageResource(R.drawable.ic_pound);
            }
        }
    }

    public void exchange(){
        String message;
        if (rdGroupFrom.getCheckedRadioButtonId() == rbFromEuro.getId()){
            message = getFromEuro();

        }else if(rdGroupFrom.getCheckedRadioButtonId() == rbFromDollar.getId()){
            message = getFromDollar();

        }else{
            message = getFromPound();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("DefaultLocale")
    public String getFromEuro(){
        double amount =  Double.parseDouble(txtAmount.getText().toString());
        double result;
        String message;
        if (rdGroupTo.getCheckedRadioButtonId() == rbToDollar.getId()){
            result = amount * 1.17;
            message = String.format("%.2f€ = %.2f$", amount, result);
        }else{
            result = amount * 0.88;
            message = String.format("%.2f€ = %.2f£", amount, result);
        }
        return message;
    }

    @SuppressLint("DefaultLocale")
    public String getFromDollar(){
        double amount =  Double.parseDouble(txtAmount.getText().toString());
        double result;
        String message;
        if (rdGroupTo.getCheckedRadioButtonId() == rbToEuro.getId()){
            result = amount * 0.86;
            message = String.format("%.2f$ = %.2f€", amount, result);
        }else{
            result = amount * 0.77;
            message = String.format("%.2f$ = %.2f£", amount, result);
        }
        return message;
    }

    @SuppressLint("DefaultLocale")
    public String getFromPound(){
        double amount =  Double.parseDouble(txtAmount.getText().toString());
        double result;
        String message;

        if (rdGroupTo.getCheckedRadioButtonId() == rbToEuro.getId()){
            result = amount * 1.13;
            message = String.format("%.2f£ = %.2f€", amount, result);
        }else{
            result = amount * 1.32;
            message = String.format("%.2f£ = %.2f$", amount, result);
        }
        return message;
    }

}
