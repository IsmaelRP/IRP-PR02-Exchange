package es.iessaladillo.ismaelraqi.irp_pr02_exchange;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    protected void initViews(){
        // Para poner "RequiresViewById" tiene que ejecutarse en una versión 28 (actual 21)
        // POR ESO DEBES USAR ActivityCompat.requireViewById QUE ES QUIEN TE DA LA COMPATIBILDIAD
        // CON VERSIONES ANTERIORES.
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

        btnExchange = findViewById(R.id.btnExchange);

        // ¿POR QUÉ NO USAS LAMBDAS?
        btnExchange.setOnClickListener(this);
        txtAmount.setOnClickListener(this);

        rbFromEuro.setOnClickListener(this);
        rbFromDollar.setOnClickListener(this);
        rbFromPound.setOnClickListener(this);

        rbToEuro.setOnClickListener(this);
        rbToDollar.setOnClickListener(this);
        rbToPound.setOnClickListener(this);

        // AÑADO ESTAS LÍNEAS PARA QUE PASE ALGUNOS TESTS. FÍJATE BIEN.
        imgFrom.setTag(R.drawable.ic_euro);
        imgTo.setTag(R.drawable.ic_dollar);
    }

    // ES MUCHO MÁS CÓMODO USAR LAMBDAS. EN TU CASO TIENES QUE ANDAR CREANDO IF ELSE IF.
    @Override
    public void onClick(View v) {
        if (v.getId() == btnExchange.getId()){
            if (txtAmount.getText().toString().isEmpty() || !txtAmount.getText().toString().matches(getString(R.string.amount_matches))){
                txtAmount.setText("0.00");

            }else{
                exchange();
            }
        }else if(v.getId() == txtAmount.getId()){
            // ¡¡¡MUY BUENA IDEA!!!.
            txtAmount.selectAll();
        // TE RECOMIENDO QUE HAGAS Code -> Reformat Code PARA REFORMATEAR EL CÓDGIO Y QUEDE BONITO.
        }else{
            // EXTRAE CÓDIGO A OTROS MÉTODOS. TANTO CÓDIGO ANIDADO ES DIFÍCIL DE LEER.
            if (v.getId() == rbFromEuro.getId()){
                rbToEuro.setEnabled(false);
                rbToPound.setEnabled(true);
                rbToDollar.setEnabled(true);

                imgFrom.setImageResource(R.drawable.ic_euro);
                // AÑADO ESTA LÍNEA PARA QUE PASE ALGUNOS TEST.
                imgFrom.setTag(R.drawable.ic_euro);

            }else if (v.getId() == rbFromDollar.getId()){
                rbToEuro.setEnabled(true);
                rbToPound.setEnabled(true);
                rbToDollar.setEnabled(false);

                imgFrom.setImageResource(R.drawable.ic_dollar);
                // AÑADO ESTA LÍNEA PARA QUE PASE ALGUNOS TEST.
                imgFrom.setTag(R.drawable.ic_dollar);

            }else if (v.getId() == rbFromPound.getId()){
                rbToEuro.setEnabled(true);
                rbToPound.setEnabled(false);
                rbToDollar.setEnabled(true);

                imgFrom.setImageResource(R.drawable.ic_pound);
                // AÑADO ESTA LÍNEA PARA QUE PASE ALGUNOS TEST.
                imgFrom.setTag(R.drawable.ic_pound);

            }else if (v.getId() == rbToEuro.getId()){
                rbFromEuro.setEnabled(false);
                rbFromPound.setEnabled(true);
                rbFromDollar.setEnabled(true);

                imgTo.setImageResource(R.drawable.ic_euro);
                // AÑADO ESTA LÍNEA PARA QUE PASE ALGUNOS TEST.
                imgTo.setTag(R.drawable.ic_euro);

            }else if (v.getId() == rbToDollar.getId()){
                rbFromEuro.setEnabled(true);
                rbFromPound.setEnabled(true);
                rbFromDollar.setEnabled(false);

                imgTo.setImageResource(R.drawable.ic_dollar);
                // AÑADO ESTA LÍNEA PARA QUE PASE ALGUNOS TEST.
                imgTo.setTag(R.drawable.ic_dollar);

            }else if (v.getId() == rbToPound.getId()){
                rbFromEuro.setEnabled(true);
                rbFromPound.setEnabled(false);
                rbFromDollar.setEnabled(true);

                imgTo.setImageResource(R.drawable.ic_pound);
                // AÑADO ESTA LÍNEA PARA QUE PASE ALGUNOS TEST.
                imgTo.setTag(R.drawable.ic_pound);
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
        // NO USES NÚMERO MÁGICOS EN EL CÓDIGO. DEFINE CONSTANTES.
        double amount =  Double.parseDouble(txtAmount.getText().toString());
        double result;
        String message;
        // EN VEZ DE USAR String.format USA RECURSOS DE CADENA CON PARÁMETROS.
        if (rdGroupTo.getCheckedRadioButtonId() == rbToDollar.getId()){
            result = amount * 1.17;
            // CAMBIO ESTA LÍNE PARA QUE PASE EL TEST.
            message = String.format("%.2f € = %.2f $", amount, result);
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
