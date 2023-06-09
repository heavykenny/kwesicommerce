package com.example.kwesicommerce.data.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kwesicommerce.data.model.CartItemModel;
import com.example.kwesicommerce.data.model.CategoryModel;
import com.example.kwesicommerce.data.model.OrderModel;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.data.model.UserModel;
import com.example.kwesicommerce.utils.FunctionUtil;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kwesicommerce.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_ORDERS = "orders";
    private static final String TABLE_ORDER_ITEMS = "order_items";
    private static final String TABLE_WISHLIST = "wishlist";

    // UserModel table columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_FULL_NAME = "fullName";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_DATE_REGISTERED = "dateRegistered";
    private static final String COLUMN_DATE_UPDATED = "dateUpdated";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_HOBBIES = "hobbies";
    private static final String COLUMN_POSTCODE = "postcode";
    private static final String COLUMN_PROFILE_PICTURE = "profilePicture";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_IS_ADMIN = "isAdmin";

    // CategoryModel table columns
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE_URL = "imageUrl";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_LIST_PRICE = "listPrice";
    private static final String COLUMN_RETAIL_PRICE = "retailPrice";
    private static final String COLUMN_CATEGORY_ID = "categoryId";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_ORDER_ID = "orderId";
    private static final String COLUMN_ORDER_TRACKING_ID = "orderTrackingId";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_PRODUCT_ID = "productId";
    private static final String COLUMN_DATE_CREATED = "dateCreated";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_AMOUNT_PAID = "amountPaid";

    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user table
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FULL_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_HOBBIES + " TEXT," +
                COLUMN_POSTCODE + " TEXT," +
                COLUMN_PROFILE_PICTURE + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_IS_ADMIN + " INTEGER," +
                COLUMN_DATE_REGISTERED + " TEXT," +
                COLUMN_DATE_UPDATED + " TEXT" +
                ")";
        db.execSQL(createUserTableQuery);

        String plainPassword = "password123";
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

// Insert seed data
        String insertDataQuery = "INSERT INTO " + TABLE_USERS + "(" +
                COLUMN_FULL_NAME + "," +
                COLUMN_EMAIL + "," +
                COLUMN_DATE_REGISTERED + "," +
                COLUMN_DATE_UPDATED + "," +
                COLUMN_PASSWORD + "," +
                COLUMN_HOBBIES + "," +
                COLUMN_POSTCODE + "," +
                COLUMN_PROFILE_PICTURE + "," +
                COLUMN_ADDRESS + "," +
                COLUMN_IS_ADMIN +
                ") VALUES (" +
                "'John Doe'," +
                "'john@example.com'," +
                "'2023-01-01'," +
                "'2023-01-01'," +
                "'" + hashedPassword + "'," + // Use the hashed password
                "'Reading, Swimming'," +
                "'12345'," +
                "''," +
                "'123 Main St'," +
                "1" +
                ")";

        db.execSQL(insertDataQuery);

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
                COLUMN_CATEGORY_ID + " INTEGER," +
                COLUMN_DATE_CREATED + " TEXT," +
                COLUMN_DATE_UPDATED + " TEXT," +
                "FOREIGN KEY(" + COLUMN_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORIES + "(" + COLUMN_ID + ")" +
                ")";

        db.execSQL(createProductTableQuery);

        // Create the cart table
        String createCartTableQuery = "CREATE TABLE " + TABLE_CART + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_ID + " INTEGER," +
                COLUMN_PRODUCT_ID + " INTEGER," +
                COLUMN_QUANTITY + " INTEGER," +
                "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")," +
                "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + COLUMN_ID + ")" +
                ")";
        db.execSQL(createCartTableQuery);

        // Create the order table
        String createOrderTableQuery = "CREATE TABLE " + TABLE_ORDERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ORDER_TRACKING_ID + " TEXT," +
                COLUMN_USER_ID + " INTEGER," +
                COLUMN_STATUS + " TEXT," +
                COLUMN_AMOUNT_PAID + " REAL," +
                COLUMN_DATE_CREATED + " TEXT," +
                COLUMN_DATE_UPDATED + " TEXT," +
                "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")" +
                ")";

        db.execSQL(createOrderTableQuery);

        // Create the order items table
        String createOrderItemsQuery = "CREATE TABLE " + TABLE_ORDER_ITEMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ORDER_ID + " INTEGER," +
                COLUMN_PRODUCT_ID + " INTEGER," +
                COLUMN_QUANTITY + " INTEGER," +
                "FOREIGN KEY(" + COLUMN_ORDER_ID + ") REFERENCES " + TABLE_ORDERS + "(" + COLUMN_ID + ")," +
                "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + COLUMN_ID + ")" +
                ")";

        db.execSQL(createOrderItemsQuery);

        // create a wishlist table
        String createWishlistTableQuery = "CREATE TABLE " + TABLE_WISHLIST + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_ID + " INTEGER," +
                COLUMN_PRODUCT_ID + " INTEGER," +
                "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")," +
                "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCTS + "(" + COLUMN_ID + ")" +
                ")";

        db.execSQL(createWishlistTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the user table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);

        // recreate the tables
        onCreate(db);
    }

    /**
     * Getting a user from the database by id
     *
     * @param userId
     * @return UserModel
     */
    @SuppressLint("Range")
    public UserModel getUser(int userId) {
        // Get a readable database
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_ID + " = ?", new String[]{String.valueOf(userId)}, null, null, null);
        UserModel userModel = null;
        // Check if the cursor is not null and if it has a first item
        if (cursor != null && cursor.moveToFirst()) {
            // Create a new user model object and set the values to it
            userModel = new UserModel();
            userModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            userModel.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
            userModel.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            userModel.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
            userModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
            userModel.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            userModel.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
            userModel.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
            userModel.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            userModel.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);
            userModel.setProfileImage(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_PICTURE)));
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return userModel;
    }

    /**
     * Inserting a user into the database
     *
     * @param userModel
     * @return void
     */
    public int insertUser(UserModel userModel) {
        // Get a writable database
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, userModel.getFullName());
        values.put(COLUMN_EMAIL, userModel.getEmail());
        values.put(COLUMN_PASSWORD, userModel.getPassword());
        values.put(COLUMN_HOBBIES, userModel.getHobbies());
        values.put(COLUMN_POSTCODE, userModel.getPostcode());
        values.put(COLUMN_ADDRESS, userModel.getAddress());
        values.put(COLUMN_IS_ADMIN, userModel.isAdmin() ? 1 : 0);
        values.put(COLUMN_DATE_REGISTERED, FunctionUtil.getCurrentDateTime());
        values.put(COLUMN_DATE_UPDATED, FunctionUtil.getCurrentDateTime());
        values.put(COLUMN_PROFILE_PICTURE, userModel.getProfileImage());

        int id = (int) db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    /**
     * Updating a user in the database
     *
     * @param userModel
     * @return void
     */
    public void updateUser(UserModel userModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, userModel.getFullName());
        values.put(COLUMN_EMAIL, userModel.getEmail());
        values.put(COLUMN_DATE_UPDATED, String.format(String.valueOf(new Date())));
        values.put(COLUMN_PASSWORD, userModel.getPassword());
        values.put(COLUMN_HOBBIES, userModel.getHobbies());
        values.put(COLUMN_POSTCODE, userModel.getPostcode());
        values.put(COLUMN_ADDRESS, userModel.getAddress());
        values.put(COLUMN_IS_ADMIN, userModel.isAdmin() ? 1 : 0);
        values.put(COLUMN_PROFILE_PICTURE, userModel.getProfileImage());

        db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(userModel.getId())});
    }

    /**
     * Validating a user's credentials
     *
     * @param email, password
     * @return boolean
     */
    @SuppressLint("Range")
    public boolean isUserCredentialsValid(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USERS, null, selection,
                selectionArgs, null, null, null);
        boolean isValid = cursor != null && cursor.moveToFirst();

        if (isValid) {
            String userPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            isValid = BCrypt.checkpw(password, userPassword);
        }
        return isValid;
    }

    /**
     * Checking if a user's email is unique
     *
     * @param email
     * @return boolean
     */
    public boolean isEmailUnique(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USERS, null,
                selection, selectionArgs, null, null, null);
        boolean isUnique = cursor == null || cursor.getCount() == 0;
        if (cursor != null) {
            cursor.close();
        }
        return isUnique;
    }


    /**
     * Deleting a user from the database
     *
     * @param userModel
     * @return void
     */
    public void deleteUser(UserModel userModel) {
        // Handle deleting a userModel from the database
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[]{String.valueOf(userModel.getId())});
    }

    /**
     * Getting all the categories from the database
     *
     * @return List<CategoryModel>
     */
    @SuppressLint("Range")
    public List<CategoryModel> getCategories() {
        List<CategoryModel> categoryModelList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CATEGORIES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                CategoryModel categoryModel = new CategoryModel();

                categoryModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                categoryModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                categoryModelList.add(categoryModel);
            } while (cursor.moveToNext());
        }
        return categoryModelList;
    }

    /**
     * Getting a category from the database
     *
     * @param categoryId
     * @return CategoryModel
     */
    @SuppressLint("Range")
    public CategoryModel getCategory(int categoryId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CATEGORIES, null, COLUMN_ID + " = ?", new String[]{String.valueOf(categoryId)}, null, null, null);
        CategoryModel categoryModel = null;
        if (cursor != null && cursor.moveToFirst()) {
            categoryModel = new CategoryModel();
            categoryModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            categoryModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        }
        if (cursor != null) {
            cursor.close();
        }
        return categoryModel;
    }

    public void insertCategory(CategoryModel categoryModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, categoryModel.getName());

        db.insert(TABLE_CATEGORIES, null, values);
    }

    public void updateCategory(CategoryModel categoryModel) {
        // Handle updating an existing categoryModel in the database
    }

    public void deleteCategory(CategoryModel categoryModel) {
        // Handle deleting a categoryModel from the database
    }

    /**
     * Getting all the products from the database
     *
     * @return List<ProductModel>
     */
    @SuppressLint("Range")
    public List<ProductModel> getProducts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, null, null, null, null, null, null);
        List<ProductModel> productModelList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ProductModel productModel = new ProductModel();
                productModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                productModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                productModel.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                productModel.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
                productModel.setListPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_LIST_PRICE)));
                productModel.setRetailPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_RETAIL_PRICE)));
                productModel.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
                productModel.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                productModel.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));
                productModel.setDateCreated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CREATED)));
                productModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
                productModelList.add(productModel);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return productModelList;
    }

    /**
     * Getting all the products from the database
     *
     * @param productId
     * @return List<ProductModel>
     */
    @SuppressLint("Range")
    public ProductModel getProduct(int productId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, null, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)}, null, null, null);
        ProductModel productModel = null;
        if (cursor != null && cursor.moveToFirst()) {
            productModel = new ProductModel();
            productModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            productModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            productModel.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            productModel.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
            productModel.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
            productModel.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
            productModel.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));
        }
        if (cursor != null) {
            cursor.close();
        }
        //db.close();
        return productModel;
    }

    /**
     * Inserting a product into the database
     *
     * @param productModel
     * @return List<ProductModel>
     */
    public int insertProduct(ProductModel productModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, productModel.getName());
        values.put(COLUMN_DESCRIPTION, productModel.getDescription());
        values.put(COLUMN_PRICE, productModel.getPrice());
        values.put(COLUMN_CATEGORY_ID, productModel.getCategoryId());
        values.put(COLUMN_QUANTITY, productModel.getQuantity());
        values.put(COLUMN_IMAGE_URL, productModel.getImageUrl());
        values.put(COLUMN_LIST_PRICE, productModel.getListPrice());
        values.put(COLUMN_RETAIL_PRICE, productModel.getRetailPrice());
        values.put(COLUMN_DATE_CREATED, FunctionUtil.getCurrentDateTime());
        values.put(COLUMN_DATE_UPDATED, FunctionUtil.getCurrentDateTime());

        return (int) db.insert(TABLE_PRODUCTS, null, values);
    }

    /**
     * Updating a product in the database
     *
     * @param productModel
     * @return List<ProductModel>
     */
    public void updateProduct(ProductModel productModel) {
        // Handle updating an existing productModel in the database
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, productModel.getName());
        values.put(COLUMN_DESCRIPTION, productModel.getDescription());
        values.put(COLUMN_PRICE, productModel.getPrice());
        values.put(COLUMN_CATEGORY_ID, productModel.getCategoryId());
        values.put(COLUMN_QUANTITY, productModel.getQuantity());
        values.put(COLUMN_IMAGE_URL, productModel.getImageUrl());

        db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(productModel.getId())});
        //db.close();
    }

    public void deleteProduct(ProductModel productModel) {
        // Handle deleting a productModel from the database
    }

    /**
     * Getting all the orders from the database
     *
     * @return List<OrderModel>
     */
    @SuppressLint("Range")
    public List<OrderModel> getOrders() {
        // Handle getting all orders from the database
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_ORDERS, null, null, null, null, null, null);
        List<OrderModel> orderModelList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                OrderModel orderModel = new OrderModel();
                orderModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                orderModel.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                orderModel.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)));
                orderModel.setOrderTrackingNumber(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TRACKING_ID)));
                orderModel.setAmountPaid(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT_PAID)));
                orderModel.setDateCreated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CREATED)));
                orderModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
                orderModelList.add(orderModel);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        //db.close();
        return orderModelList;
    }

    public OrderModel getOrder(int orderId) {
        // Handle retrieving a specific order from the database by ID
        return null;
    }

    /**
     * Inserting an order into the database
     *
     * @param orderModel
     * @return long
     */
    public long insertOrder(OrderModel orderModel) {
        // Handle inserting a new orderModel into the database
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, orderModel.getUserId());
        values.put(COLUMN_STATUS, orderModel.getStatus());
        values.put(COLUMN_ORDER_TRACKING_ID, orderModel.getOrderTrackingNumber());
        values.put(COLUMN_AMOUNT_PAID, orderModel.getAmountPaid());
        values.put(COLUMN_DATE_CREATED, orderModel.getDateCreated());
        values.put(COLUMN_DATE_UPDATED, orderModel.getDateUpdated());
        //db.close();

        return db.insert(TABLE_ORDERS, null, values);
    }

    public void updateOrder(OrderModel orderModel) {
        // Handle updating an existing orderModel in the database
    }

    public void deleteOrder(OrderModel orderModel) {
        // Handle deleting an orderModel from the database
    }

    /**
     * Getting user by email from the database
     *
     * @return List<UserModel>
     */
    @SuppressLint("Range")
    public UserModel getByEmail(String email) {
        // Handle getting a user by email from the database
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + " = ?", new String[]{String.valueOf(email)}, null, null, null);
        UserModel userModel = null;

        // looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            userModel = new UserModel();
            userModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            userModel.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
            userModel.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            userModel.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
            userModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
            userModel.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            userModel.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
            userModel.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
            userModel.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            userModel.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);
        }

        if (cursor != null) {
            cursor.close();
        }
        //db.close();
        return userModel;
    }

    /**
     * Adding a product to the cart
     *
     * @param userId, productId, quantity
     */
    @SuppressLint({"Recycle", "Range"})
    public void addToCart(int userId, int productId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        // get the current userId and productId from the cart table
        // if the userId and productId already exist in the cart table, update the quantity
        // otherwise, insert a new row into the cart table
        String query = "SELECT * FROM " + TABLE_CART + " WHERE " + COLUMN_USER_ID + " = ? AND " + COLUMN_PRODUCT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(productId)});
        ContentValues values = new ContentValues();

        if (cursor != null && cursor.moveToFirst()) {
            // get productModel quantity from productModel table
            ProductModel productModel = getProduct(productId);
            int currentQuantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
            if (currentQuantity + quantity > productModel.getQuantity()) {
                return;
            }

            values.put(COLUMN_QUANTITY, currentQuantity + quantity);
            String[] args = {String.valueOf(userId), String.valueOf(productId)};
            db.update(TABLE_CART, values, COLUMN_USER_ID + " = ? AND " + COLUMN_PRODUCT_ID + " = ?", args);
        } else {
            values.put(COLUMN_USER_ID, userId);
            values.put(COLUMN_PRODUCT_ID, productId);
            values.put(COLUMN_QUANTITY, quantity);
            db.insert(TABLE_CART, null, values);
        }
    }

    /**
     * Removing a product from the cart
     *
     * @param cartId
     */
    public void removeFromCart(int cartId) {
        SQLiteDatabase db = getWritableDatabase();

        String[] args = {String.valueOf(cartId)};
        db.delete(TABLE_CART, COLUMN_ID + " = ?", args);
        //db.close();
    }

    /**
     * Getting all cart items for a user
     *
     * @param userId
     */
    @SuppressLint("Range")
    public List<CartItemModel> getCartItems(int userId) {
        List<CartItemModel> cartItemModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        // Handle getting all cart items for a user from the database
        String query = "SELECT products.id as productId, products.name, products.description, products.price, cart.quantity, cart.id " +
                "FROM cart " +
                "JOIN products ON cart.productId = products.id " +
                "WHERE cart.userId = ?";

        String[] args = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int productId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID));
                int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));

                // get productModel details from productModel table
                ProductModel productModel = this.getProduct(productId);
                CartItemModel cartItemModel = new CartItemModel(id, productModel, quantity);
                cartItemModels.add(cartItemModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return cartItemModels;
    }

    /**
     * Getting Products by Category Id
     *
     * @param categoryId
     */
    @SuppressLint("Range")
    public List<ProductModel> getProductsByCategoryId(int categoryId) {
        List<ProductModel> productModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // Handle getting all products by category id from the database
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_CATEGORY_ID + " = ?";
        String[] args = {String.valueOf(categoryId)};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                ProductModel productModel = new ProductModel();
                productModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                productModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                productModel.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                productModel.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
                productModel.setListPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_LIST_PRICE)));
                productModel.setRetailPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_RETAIL_PRICE)));
                productModel.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
                productModel.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                productModel.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));
                productModel.setDateCreated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CREATED)));
                productModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));

                productModels.add(productModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return productModels;
    }

    /**
     * Updating the quantity of a cart item
     *
     * @param cartId, quantity
     * @return void
     */
    public void updateCartItemQuantity(int cartId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();

        // Handle updating the quantity of a cart item
        String query = "SELECT * FROM " + TABLE_CART + " WHERE " + COLUMN_ID + " = ?";
        String[] args = {String.valueOf(cartId)};
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, args);

        if (cursor != null && cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_QUANTITY, quantity);
            db.update(TABLE_CART, values, COLUMN_ID + " = ?", args);
        }

        //db.close();
    }

    /**
     * Inserting an order item
     *
     * @param orderId, productId, quantity
     */
    public void insertOrderItem(int orderId, int productId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ID, orderId);
        values.put(COLUMN_PRODUCT_ID, productId);
        values.put(COLUMN_QUANTITY, quantity);

        db.insert(TABLE_ORDER_ITEMS, null, values);

        //db.close();
    }

    /**
     * Getting all order items for an order
     *
     * @param orderId
     * @return List<CartItemModel>
     */
    @SuppressLint("Range")
    public List<CartItemModel> getOrderItems(String orderId) {
        List<CartItemModel> cartItemModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // Handle getting all order items for an order from the database
        String query = "SELECT products.id as productId, products.name, products.description, products.price, order_items.quantity, order_items.id " +
                "FROM order_items " +
                "JOIN products ON order_items.productId = products.id " +
                "WHERE order_items.orderId = ?";
        String[] args = {orderId};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int productId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID));
                int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));

                ProductModel productModel = this.getProduct(productId);
                CartItemModel cartItemModel = new CartItemModel(id, productModel, quantity);
                cartItemModels.add(cartItemModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return cartItemModels;
    }

    /**
     * Getting the total price of an order
     *
     * @param orderId
     * @return Object
     */
    @SuppressLint("Range")
    public Object getOrderTotalPrice(String orderId) {
        SQLiteDatabase db = getReadableDatabase();

        // Handle getting the total price of an order from the database
        String query = "SELECT SUM(products.price * order_items.quantity) as total " +
                "FROM order_items " +
                "JOIN products ON order_items.productId = products.id " +
                "WHERE order_items.orderId = ?";
        String[] args = {orderId};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            return cursor.getDouble(cursor.getColumnIndex("total"));
        }

        cursor.close();
        //db.close();

        return 0;
    }

    /**
     * Getting all orders for a user
     *
     * @param userId
     * @return List<OrderModel>
     */
    @SuppressLint("Range")
    public List<CartItemModel> getUsersOrder(int userId) {
        List<CartItemModel> cartItemModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // order by order date created
        String query = "SELECT products.id as productId, " +
                "products.name, products.description, products.price, " +
                "order_items.quantity, order_items.id , orders.orderTrackingId " +
                "FROM order_items " +
                "JOIN products ON order_items.productId = products.id " +
                "JOIN orders ON order_items.orderId = orders.id " +
                "WHERE orders.userId = ? " +
                "ORDER BY orders.dateCreated DESC";

        String[] args = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int productId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID));
                int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));

                ProductModel productModel = this.getProduct(productId);
                CartItemModel cartItemModel = new CartItemModel(id, productModel, quantity);
                cartItemModels.add(cartItemModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return cartItemModels;
    }

    /**
     * Inserting a product into the wishlist
     *
     * @param productId, userId
     * @return int
     */
    @SuppressLint("Range")
    public int insertWishlist(int productId, int userId) {
        SQLiteDatabase db = getWritableDatabase();

        // Handle inserting a product into the wishlist
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, productId);
        values.put(COLUMN_USER_ID, userId);

        // check if product already exists in wishlist
        String query = "SELECT * FROM " + TABLE_WISHLIST + " WHERE "
                + COLUMN_PRODUCT_ID + " = ? AND " + COLUMN_USER_ID + " = ?";
        String[] args = {String.valueOf(productId), String.valueOf(userId)};
        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        } else {
            return (int) db.insert(TABLE_WISHLIST, null, values);
        }
    }

    /**
     * Getting all products in the wishlist
     *
     * @param userId
     * @return List<ProductModel>
     */
    @SuppressLint("Range")
    public List<ProductModel> getUserWishlist(int userId) {
        List<ProductModel> productModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // Handle getting all products in the wishlist from the database
        String query = "SELECT products.* " +
                "FROM wishlist " +
                "JOIN products ON wishlist.productId = products.id " +
                "WHERE wishlist.userId = ?";
        String[] args = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                ProductModel productModel = new ProductModel();
                productModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                productModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                productModel.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                productModel.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)));
                productModel.setListPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_LIST_PRICE)));
                productModel.setRetailPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_RETAIL_PRICE)));
                productModel.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID)));
                productModel.setQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                productModel.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));
                productModel.setDateCreated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CREATED)));
                productModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));

                productModels.add(productModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return productModels;
    }

    /**
     * Deleting a product from the wishlist
     *
     * @param productId, userId
     */
    public void deleteWishlist(int productId, int userId) {
        SQLiteDatabase db = getWritableDatabase();

        String[] args = {String.valueOf(productId), String.valueOf(userId)};
        db.delete(TABLE_WISHLIST, COLUMN_PRODUCT_ID + " = ? AND " + COLUMN_USER_ID + " = ?", args);

        //db.close();
    }

    /**
     * Getting the total number of products in the wishlist
     *
     * @param userId
     * @return int
     */
    @SuppressLint("Range")
    public int getUserWishlistCount(int userId) {
        SQLiteDatabase db = getReadableDatabase();

        // Handle getting the total number of products in the wishlist from the database
        String query = "SELECT COUNT(*) as count FROM wishlist WHERE userId = ?";
        String[] args = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("count"));
        }

        cursor.close();
        //db.close();

        return 0;
    }

    /**
     * Get all Admins
     *
     * @return List<UserModel>
     */
    @SuppressLint("Range")
    public List<UserModel> getAllAdmins() {
        List<UserModel> userModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // Handle getting all admins from the database
        String query = "SELECT * FROM users WHERE isAdmin = 1";

        Cursor cursor = db.rawQuery(query, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                userModel.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
                userModel.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                userModel.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
                userModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
                userModel.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                userModel.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
                userModel.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
                userModel.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                userModel.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);

                userModels.add(userModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return userModels;
    }

    /**
     * Get all Customers
     *
     * @return List<UserModel>
     */
    @SuppressLint("Range")
    public List<UserModel> getAllCustomers() {
        List<UserModel> userModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM users WHERE isAdmin = 0";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                userModel.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
                userModel.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                userModel.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
                userModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
                userModel.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                userModel.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
                userModel.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
                userModel.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                userModel.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);

                userModels.add(userModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return userModels;
    }

    /**
     * Get total number of sales
     *
     * @return double
     */
    @SuppressLint("Range")
    public double getTotalSales() {
        SQLiteDatabase db = getReadableDatabase();

        // Handle getting the total number of sales from the database
        String query = "SELECT SUM(amountPaid) as total FROM orders";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return cursor.getDouble(cursor.getColumnIndex("total"));
        }

        cursor.close();
        //db.close();

        return 0;
    }

    /**
     * Get all orders products
     *
     * @return List<CartItemModel>
     */
    @SuppressLint("Range")
    public List<CartItemModel> getAllOrdersProduct() {
        List<CartItemModel> cartItemModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // order by order date created
        String query = "SELECT products.id as productId, " +
                "products.name, products.description, products.price, " +
                "order_items.quantity, order_items.id , orders.orderTrackingId " +
                "FROM order_items " +
                "JOIN products ON order_items.productId = products.id " +
                "JOIN orders ON order_items.orderId = orders.id " +
                "ORDER BY orders.dateCreated DESC";

        String[] args = {};

        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int productId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID));
                int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));

                ProductModel productModel = this.getProduct(productId);
                CartItemModel cartItemModel = new CartItemModel(id, productModel, quantity);
                cartItemModels.add(cartItemModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return cartItemModels;
    }

    /**
     * Get all users from the database
     *
     * @return List of users
     */
    @SuppressLint("Range")
    public List<UserModel> getAllUsers() {
        List<UserModel> userModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM users";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                userModel.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
                userModel.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                userModel.setDateRegistered(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_REGISTERED)));
                userModel.setDateUpdated(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_UPDATED)));
                userModel.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                userModel.setHobbies(cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIES)));
                userModel.setPostcode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTCODE)));
                userModel.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                userModel.setAdmin(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADMIN)) == 1);

                userModels.add(userModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return userModels;
    }
}