package com.eliezer.afinal

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.properties.Delegates

class RegistrarUsuario : AppCompatActivity() {


    private lateinit var editnombre: EditText
    private lateinit var editprimerapellido: EditText
    private lateinit var editcorreo: EditText
    private lateinit var editcontras: EditText
    private lateinit var  progressBar: ProgressDialog
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    //global variables
    private var primernombre by Delegates.notNull<String>()
    private var primerapellido by Delegates.notNull<String>()
    private var correo by Delegates.notNull<String>()
    private var contras by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)
        initialice()
    }

    private fun initialice() {
        //llamamos nuestras vista
        editnombre = findViewById(R.id.editnombre)
        editprimerapellido = findViewById(R.id.editprimerapellido)
        editcorreo = findViewById(R.id.editcorreo)
        editcontras = findViewById(R.id.editcontras)

        //Creamos nuestro progressDialog
        progressBar = ProgressDialog(this)

        /*Creamos una instancia para guardar los datos del usuario en nuestra base  de datos*/
        database = FirebaseDatabase.getInstance()
        /*Creamos una instancia para crear nuestra autenticación y guardar el usuario*/
        auth = FirebaseAuth.getInstance()

        /*reference nosotros leemos o escribimos en una ubicación específica la base de datos*/
        databaseReference = database.reference.child("Usuarios")
    }

    private fun createNewAccount() {

        //Obtenemos los datos de nuestras cajas de texto
        primernombre = editnombre.text.toString()
        primerapellido = editprimerapellido.text.toString()
        correo = editcorreo.text.toString()
        contras = editcontras.text.toString()

        //Verificamos que los campos estén llenos
        if (!TextUtils.isEmpty(primernombre) && !TextUtils.isEmpty(editprimerapellido.toString())
            && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contras)) {

            /*Antes de iniciar nuestro registro bloqueamos la pantalla o también podemos usar una barra de proceso por lo que progressbar está obsoleto*/

            progressBar.setMessage("Usuario registrado correctamente")
            progressBar.show()

            //vamos a dar de alta el usuario con el correo y la contraseña
            auth.createUserWithEmailAndPassword(correo, contras)
                .addOnCompleteListener(this) {

                    //Si está en este método quiere decir quetodo salio bien en la autenticación

                    /*Una vez que se dio de alta la cuenta vamos a dar de alta la información en la base de datos*/

                    /*Vamos a obtener el id del usuario con que accedio con currentUser*/
                    val user: FirebaseUser = auth.currentUser!!

                    //enviamos email de verificación a la cuenta del usuario
                    verifyEmail(user);

                    /*Damos de alta la información del usuario enviamos la referencia para guardarlo en la base de datos  de preferencia enviamos el id para que no se repita*/
                    val currentUserDb = databaseReference.child(user.uid)

                    //Agregamos el nombre y el apellido dentro de user/id/
                    currentUserDb.child("Primer Nombre").setValue(primernombre)
                    currentUserDb.child("Primer Apellido").setValue(primerapellido)

                    //Por último nos vamos a la vista home
                    updateUserInfoAndGoHome()

                }.addOnFailureListener{
                    // si el registro falla se mostrara este mensaje
                    Toast.makeText(this, "Error en la autenticación.",
                        Toast.LENGTH_SHORT).show()
                }

        } else {
            Toast.makeText(this, "Porfavor llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyEmail(user: FirebaseUser) {
        user.sendEmailVerification()
            .addOnCompleteListener(this) {

                //Verificamos que la tarea se realizó correctamente
                    task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Email " + user.email,
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,
                        "Error al verificar el correo ",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUserInfoAndGoHome() {
        //Nos vamos a la actividad home
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        //ocultamos el progress
        progressBar.hide()
    }

    fun registrar(view: View) {
        createNewAccount()
    }

}