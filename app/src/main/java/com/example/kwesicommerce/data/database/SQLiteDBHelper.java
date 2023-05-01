package com.example.kwesicommerce.data.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.model.Order;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.data.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kwesicommerce.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_ORDERS = "orders";


    // User table columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_FULL_NAME = "fullName";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_DATE_REGISTERED = "dateRegistered";
    private static final String COLUMN_DATE_UPDATED = "dateUpdated";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_HOBBIES = "hobbies";
    private static final String COLUMN_POSTCODE = "postcode";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_IS_ADMIN = "isAdmin";

    // Category table columns
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE_URL = "imageUrl";

    private static final String COLUMN_PRICE = "price";

    private static final String COLUMN_LIST_PRICE = "listPrice";

    private static final String COLUMN_RETAIL_PRICE = "retailPrice";

    private static final String COLUMN_CATEGORY_ID = "categoryId";

    private static final String COLUMN_QUANTITY = "quantity";

    private static final String COLUMN_ORDER_ID = "orderId";

    private static final String COLUMN_USER_ID = "userId";

    private static final String COLUMN_PRODUCT_ID = "productId";

    private static final String COLUMN_DATE_CREATED = "dateCreated";


    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user table
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FULL_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_DATE_REGISTERED + " TEXT," +
                COLUMN_DATE_UPDATED + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_HOBBIES + " TEXT," +
                COLUMN_POSTCODE + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_IS_ADMIN + " INTEGER" +
                ")";
        db.execSQL(createUserTableQuery);

        // Create the category table
        String createCategoryTableQuery = "CREATE TABLE " + TABLE_CATEGORIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT" +
                ")";
        db.execSQL(createCategoryTableQuery);

        // Create the product table
        String createProductTableQuery = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_QUANTITY + " REAL," +
                COLUMN_PRICE + " REAL," +
                COLUMN_LIST_PRICE + " REAL," +
                COLUMN_IMAGE_URL + " TEXT," +
                COLUMN_RETAIL_PRICE + " REAL," +
                COLUMN_DATE_CREATED + " TEXT," +
                COLUMN_DATE_UPDATED + " TEXT," +
                COLUMN_CATEGORY_ID + " INTEGER," +
                "FOREIGN KEY(" + COLUMN_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORIES + "(" + COLUMN_ID + ")" +
                ")";

        db.execSQL(createProductTableQuery);


        // Create the order table
        db.execSQL("CREATE TABLE orders (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, productId INTEGER, quantity INTEGER, status TEXT, dateCreated TEXT, dateUpdated TEXT, FOREIGN KEY(userId) REFERENCES users(id), FOREIGN KEY(productId) REFERENCES products(id))");

        db.execSQL("CREATE TABLE cart (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, productId INTEGER, quantity INTEGER, FOREIGN KEY(userId) REFERENCES users(id), FOREIGN KEY(productId) REFERENCES products(id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database schema
        // Implement migration logic here
    }

    @SuppressLint("Range")
    public List<User> getUsers() {
        SQLiteDatabase db = getReadableDatabase();
        List<User> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                user.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
                user.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
                user.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                user.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);

                userList.add(user);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return userList;
    }


    @SuppressLint("Range")
    public User getUser(int userId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_ID + " = ?", new String[]{String.valueOf(userId)}, null, null, null);
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            user.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            user.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
            user.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            user.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
            user.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            user.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return user;
    }

    public void insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, user.getFullName());
        values.put(COLUMN_EMAIL, user.getEmail());
        String currentDateTime = String.format(String.valueOf(new Date()));
        values.put(COLUMN_DATE_REGISTERED, currentDateTime);
        values.put(COLUMN_DATE_UPDATED, currentDateTime);
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_HOBBIES, user.getHobbies());
        values.put(COLUMN_POSTCODE, user.getPostcode());
        values.put(COLUMN_ADDRESS, user.getAddress());
        values.put(COLUMN_IS_ADMIN, user.isAdmin() ? 1 : 1);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, user.getFullName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_DATE_UPDATED, String.format(String.valueOf(new Date())));
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_HOBBIES, user.getHobbies());
        values.put(COLUMN_POSTCODE, user.getPostcode());
        values.put(COLUMN_ADDRESS, user.getAddress());
        values.put(COLUMN_IS_ADMIN, user.isAdmin() ? 1 : 1);

        db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean isUserCredentialsValid(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
        boolean isValid = cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return isValid;
    }

    public boolean isEmailUnique(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
        boolean isUnique = cursor == null || cursor.getCount() == 0;
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return isUnique;
    }


    public void deleteUser(User user) {
        // Handle deleting a user from the database

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<Category> getCategories() {
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CATEGORIES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Category category = new Category();

                category.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                categoryList.add(category);
            } while (cursor.moveToNext());
        }

        db.close();


        return categoryList;
    }

    @SuppressLint("Range")
    public Category getCategory(int categoryId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CATEGORIES, null, COLUMN_ID + " = ?", new String[]{String.valueOf(categoryId)}, null, null, null);
        Category category = null;
        if (cursor != null && cursor.moveToFirst()) {
            category = new Category();
            category.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            category.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return category;
    }

    public void insertCategory(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, category.getName());

        db.insert(TABLE_CATEGORIES, null, values);
        db.close();
    }

    public void updateCategory(Category category) {
        // Handle updating an existing category in the database
    }

    public void deleteCategory(Category category) {
        // Handle deleting a category from the database
    }

    @SuppressLint("Range")
    public List<Product> getProducts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, null, null, null, null, null, null);
        List<Product> productList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
                product.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
                product.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                product.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return productList;
    }

    public Product getProduct(int productId) {
        // Handle retrieving a specific product from the database by ID
        return null;
    }

    public void insertProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_CATEGORY_ID, product.getCategoryId());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        values.put(COLUMN_IMAGE_URL, product.getImageUrl());

        long result =  db.insert(TABLE_PRODUCTS, null, values);


        if(result == -1) {
            Log.e("MESSAGE",""+ product.getName() +"  Not Inserted");
        }else{
            Log.e("MESSAGE",""+ product.getName() +"  Inserted");
        }

        db.close();
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

    @SuppressLint("Range")
    public User getByEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + " = ?", new String[]{String.valueOf(email)}, null, null, null);
        User user = null;

        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            user.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            user.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
            user.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            user.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
            user.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            user.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return user;
    }

    public void addToCart(int userId, int productId) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("productId", productId);
        values.put("quantity", 1);

        db.insert("cart", null, values);
        db.close();
    }

    public void removeFromCart(int userId, int productId) {
        SQLiteDatabase db = getWritableDatabase();

        String[] args = {String.valueOf(userId), String.valueOf(productId)};
        db.delete("cart", "userId = ? AND productId = ?", args);
        db.close();
    }

    @SuppressLint("Range")
    public List<CartItem> getCartItems(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT products.id, products.name, products.description, products.price, cart.quantity " +
                "FROM cart " +
                "JOIN products ON cart.productId = products.id " +
                "WHERE cart.userId = ?";
        String[] args = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
//                String productName = cursor.getString(cursor.getColumnIndex("name"));
//                String productDescription = cursor.getString(cursor.getColumnIndex("description"));
//                String productPrice = cursor.getString(cursor.getColumnIndex("price"));


                int productId = cursor.getInt(cursor.getColumnIndex("id"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));

                Product product = this.getProduct(productId);
                CartItem cartItem = new CartItem(product, quantity);
                cartItems.add(cartItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cartItems;
    }

    public void clearCart(int userId) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {String.valueOf(userId)};
        db.delete("cart", "userId = ?", args);
        db.close();
    }

    @SuppressLint("Range")
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM products WHERE categoryId = ?";
        String[] args = {String.valueOf(categoryId)};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex("id")));
                product.setName(cursor.getString(cursor.getColumnIndex("name")));
                product.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
                product.setCategoryId(cursor.getInt(cursor.getColumnIndex("categoryId")));
                product.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));

                products.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return products;
    }
}