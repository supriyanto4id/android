package my.id.supri.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView
    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button=findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnWithData : Button= findViewById(R.id.btn_move_with_data)
        btnWithData.setOnClickListener(this)

        val btnWithDataObject:Button= findViewById(R.id.btn_move_activity_object)
        btnWithDataObject.setOnClickListener(this)

        val btnDialNumber:Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)

        val btnMoveForResult : Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)
        tvResult=findViewById(R.id.tv_result)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_move_activity-> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_with_data->{
                val moveWithdata = Intent(this@MainActivity,MoveWithDataActivity::class.java)
                moveWithdata.putExtra(MoveWithDataActivity.EXTRA_NAME,"Supriyanto")
                moveWithdata.putExtra(MoveWithDataActivity.EXTRA_AGE,34)
                startActivity(moveWithdata)
            }
            R.id.btn_move_activity_object->{
                val person = Person("Supriyanto", 12, "Supriprosesor@gmail.com", "Malang")
                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_dial_number->{
                val phoneNumber= "081232884901"
                val dialNumber = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialNumber)
            }
            R.id.btn_move_for_result->{
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tvResult.text = "Hasil : $selectedValue"
            }
        }
    }
    }

