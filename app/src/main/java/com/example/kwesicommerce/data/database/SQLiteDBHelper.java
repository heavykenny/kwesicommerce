package com.example.kwesicommerce.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.model.Order;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.data.model.User;

import java.util.List;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kwesicommerce.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user table
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, fullName TEXT, email TEXT, dateRegistered TEXT, dateUpdated TEXT, password TEXT, hobbies TEXT, postcode TEXT, address TEXT, isAdmin BOOLEAN)");

        // Create the category table
        db.execSQL("CREATE TABLE categories (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");

        // Create the product table
        db.execSQL("CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price REAL, listPrice REAL, retailPrice REAL, dateCreated TEXT, dateUpdated TEXT, categoryId INTEGER, FOREIGN KEY(categoryId) REFERENCES categories(id))");

        // Create the order table
        db.execSQL("CREATE TABLE orders (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, productId INTEGER, quantity INTEGER, status TEXT, dateCreated TEXT, dateUpdated TEXT, FOREIGN KEY(userId) REFERENCES users(id), FOREIGN KEY(productId) REFERENCES products(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database schema
        // Implement migration logic here
    }

    public List<User> getUsers() {
        // Handle retrieving all users from the database
        return null;
    }

    public User getUser(int userId) {
        // Handle retrieving a specific user from the database by ID
        return null;
    }

    public void insertUser(User user) {
        // Handle inserting a new user into the database
    }

    public void updateUser(User user) {
        // Handle updating an existing user in the database
    }

    public void deleteUser(User user) {
        // Handle deleting a user from the database
    }

    public List<Category> getCategories() {
        // Handle retrieving all categories from the database
        return null;
    }

    public Category getCategory(int categoryId) {
        // Handle retrieving a specific category from the database by ID
        return null;
    }

    public void insertCategory(Category category) {
        // Handle inserting a new category into the database
    }

    public void updateCategory(Category category) {
        // Handle updating an existing category in the database
    }

    public void deleteCategory(Category category) {
        // Handle deleting a category from the database
    }

    public List<Product> getProducts() {
        // Handle retrieving all products from the database
        return null;
    }

    public Product getProduct(int productId) {
        // Handle retrieving a specific product from the database by ID
        return null;
    }

    public void insertProduct(Product product) {
        // Handle inserting a new product into the database
    }

    public void updateProduct(Product product) {
        // Handle updating an existing product in the database
    }

    public void deleteProduct(Product product) {
        // Handle deleting a product from the database
    }

    public List<Order> getOrders() {
        // Handle retrieving all orders from the database
        return null;
    }

    public Order getOrder(int orderId) {
        // Handle retrieving a specific order from the database by ID
        return null;
    }

    public void insertOrder(Order order) {
        // Handle inserting a new order into the database
    }

    public void updateOrder(Order order) {
        // Handle updating an existing order in the database
    }

    public void deleteOrder(Order order) {
        // Handle deleting an order from the database
    }
}