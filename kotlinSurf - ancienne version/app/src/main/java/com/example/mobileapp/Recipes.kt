package com.example.mobileapp

data class Recipes (
    val recipes :List<Recipe>,
)

data class Recipe (
    val name : String,
    val ingredients: List<String>,
    val instructions : List<String>,
    val image: String,
)
