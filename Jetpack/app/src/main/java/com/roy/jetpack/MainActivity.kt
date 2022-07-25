package com.roy.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roy.jetpack.ui.theme.JetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val items by rememberSaveable { mutableStateOf(mutableListOf<Person>())}
                    NavHost(navController = navController, startDestination = "LISTA" ){
                        composable(route = "LISTA"){
                            MyList(navController, items)
                        }
                        composable(route = "FORM"){
                            Form(navController, items)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackTheme {
        Greeting("Android")
    }
}

@Composable
fun Form(navController: NavController, items: MutableList<Person>){

    var name by rememberSaveable {mutableStateOf("")}
    var lastName by rememberSaveable {mutableStateOf("")}
    var city by rememberSaveable {mutableStateOf("")}

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MyField(
            value = name,
            onvaluechange = {name = it},
            placeholder = "Name",
            imageVector = Icons.Filled.Person,
            contentDescription = "Person"
        )
        Spacer(modifier = Modifier.height(4.dp))
        MyField(
            value = lastName,
            onvaluechange = {lastName = it},
            placeholder = "LastName",
            imageVector = Icons.Filled.Person,
            contentDescription = "Person"
        )
        Spacer(modifier = Modifier.height(4.dp))
        MyField(
            value = city,
            onvaluechange = {city = it},
            placeholder = "City",
            imageVector = Icons.Filled.Person,
            contentDescription = "Location"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            items.add(Person(name, lastName, city))
            navController.navigate("LISTA")
        }) {
            Text(text = "Añadir")
        }
    }
}

@Composable
fun MyField(value: String, onvaluechange: (String) -> Unit, placeholder: String, imageVector: ImageVector, contentDescription: String){
    OutlinedTextField(value = value, onValueChange = onvaluechange, placeholder = {
        Text(text = placeholder)
    }, leadingIcon = {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    })
}


@Composable
fun MyItem(name:String, lastName: String, city: String){
    var count by rememberSaveable{mutableStateOf(0)}
    Row(verticalAlignment = Alignment.CenterVertically){
        Column() {
            Text(text = "$name $lastName")
            Text(text = city)
        }

       Column(horizontalAlignment = Alignment.CenterHorizontally) {
           IconButton(onClick = { /*TODO*/ }) {
               Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
           }
           Text(text = count.toString())
       }
    }
}

data class Person(val name:String, val lastName: String, val city: String)

@Composable
fun MyList(navController: NavController, items: MutableList<Person>){
    LazyColumn(){
        items(items) { item ->
            MyItem(name = item.name, lastName = item.lastName, city = item.city)
        }
        
        item {
            Button(onClick = { navController.navigate("FORM") }) {
                Text(text = "Nuevo")
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun ver(){
    //MyItem(name = "Roy", lastName = "Maestre", city = "Medeshin")
    MyList()
}*/
