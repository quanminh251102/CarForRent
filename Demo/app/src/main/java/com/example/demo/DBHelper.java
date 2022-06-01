package com.example.demo;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "ManageGarageDB.db";
    public  static final int DATABASE_VERSION = 1;

    // region table account


    private static final String CREATE_TABLE_ACCOUNT = "create Table Account(Username TEXT primary key, Password TEXT, Phonenumber TEXT, Fullname TEXT, Email TEXT, RegisterID TEXT)";


    Cursor Read_Account(){
        String query = "Select * from Account";
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = null;
        if(MyDB != null)
        {
            cursor = MyDB.rawQuery(query,null);
        }
        return cursor;
    }

    public Boolean insertData(String username, String password, String email, String phonenumber, String fullname, String id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username", username);
        contentValues.put("Password", password);
        contentValues.put("Phonenumber",phonenumber);
        contentValues.put("Fullname",fullname);
        contentValues.put("Email",email);
        contentValues.put("RegisterID", id);

        long result = MyDB.insert("Account",null,contentValues);
        if(result == -1) return false;
        else{
            return true;
        }
    }
    public Boolean updateData_user(String username,String name, String phone, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Fullname", name);
        cv.put("Phonenumber", phone);
        cv.put("Password", pass);

        long result = db.update("Account", cv, "Username = ?", new String[]{username});
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Boolean checkusername(String username)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where Username = ?",new String[] {username});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean check_email(String email)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where Email = ?",new String[]{email});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public String get_Name(String username)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where Username = ?",new String[]{username});
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            return cursor.getString(3);
        }
        else
        {
            return null;
        }
    }
    public String get_Phone(String username)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where Username = ?",new String[]{username});
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            return cursor.getString(2);
        }
        else
        {
            return null;
        }
    }
    public String get_Pass(String username)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where Username = ?",new String[]{username});
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            return cursor.getString(1);
        }
        else
        {
            return null;
        }
    }
    public String get_ID(String email)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where Email = ?",new String[]{email});
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            return cursor.getString(5);
        }
        else
        {
            return null;
        }
    }
    public String get_Email(String ID)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where RegisterID = ?",new String[]{ID});
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            return cursor.getString(4);
        }
        else
        {
            return "";
        }
    }
    public Boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where Username = ? and Password = ?",new String[]{username,password});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkID(String id, String email)
    {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Account where RegisterID = ? and Email = ?",new String[]{id,email});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean updateData(String id, String newpass)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Password",newpass);

        long result = MyDB.update("Account",contentValues,"RegisterID = ?",new String[]{id});
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // endregion

    // region table customer
    private static final String TABLE_CUSTOMER = "Customer";
    private static final String KEY_CUSID = "CusId";
    private static final String KEY_CUSNAME = "CusName";
    private static final String KEY_CUSADD = "CusAdd";
    private static final String KEY_PHONE = "Phone";

    private static final String CREATE_TABLE_CUSTOMER =
            "CREATE TABLE " + TABLE_CUSTOMER +
                    " (" +
                    KEY_CUSID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_CUSNAME + " TEXT," +
                    KEY_CUSADD + " TEXT," +
                    KEY_PHONE + " TEXT" +
                    ")";

    void add_customer(String cusname, String cusadd, String phone){

        if (cusname.length() == 0 || cusadd.length() == 0 || phone.length() == 0){
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CUSNAME, cusname);
        cv.put(KEY_CUSADD, cusadd);
        cv.put(KEY_PHONE, phone);

        long result = db.insert(TABLE_CUSTOMER,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor read_all_customer(){
        String query = "SELECT * FROM " + TABLE_CUSTOMER;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    String getData_customer_name_with_id(String _rowid){
        String res = "No name";

        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER + " where CusId=" + _rowid ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                res = String.valueOf(cursor.getString(1));
                ;//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return res;
    }

    void updateData_customer(String row_id,String cusname, String cusadd, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CUSNAME, cusname);
        cv.put(KEY_CUSADD, cusadd);
        cv.put(KEY_PHONE, phone);

        long result = db.update(TABLE_CUSTOMER, cv, KEY_CUSID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_one_customer(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_CUSTOMER, KEY_CUSID+"=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_all_customer(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CUSTOMER);
    }

    public List<String> get_all_customer_spinner(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1) + " (" +
                        cursor.getString(3)+ ")_"
                        + cursor.getString(0)
                );//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> get_all_customer_id_spinner(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0)
                );//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
    // endregion

    // region table rent
    private static final String TABLE_RENT = "Rent";
    private static final String KEY_RENTID = "RentId";
    private static final String KEY_CARREG = "CarReg";
    private static final String KEY_RENT_CUSID = "Rent_CusId";
    private static final String KEY_RENTALDATE = "RentalDate";
    private static final String KEY_RETURNDATE = "ReturnDate";
    private static final String KEY_FEES = "Fees";
    private static final String KEY_PAID = "Paid";

    private static final String CREATE_TABLE_RENT =
            "CREATE TABLE " + TABLE_RENT +
                    " (" +
                    KEY_RENTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_CARREG+ " TEXT," +
                    KEY_RENT_CUSID+ " INTEGER," +
                    KEY_RENTALDATE+ " DATE," +
                    KEY_RETURNDATE+ " DATE," +
                    KEY_FEES+ " INTEGER," +
                    KEY_PAID+ "  BOOLEAN" +
                    ")";

    private String getDateTime(String inp) {
        String parts[] = inp.split("/");
        String day =  parts[0];
        String month =  parts[1];
        String year=  parts[2];
        if ( day.length() == 1) day = "0" + day;
        if ( month.length() == 1) month = "0" + month;
        return year + "-" + month + "-" + day;
    }

    void add_rent(String carreg, Integer rent_cusid,String rentaldate,String renturndate, Integer fees){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CARREG,carreg);
        cv.put(KEY_RENT_CUSID,rent_cusid);
        cv.put(KEY_RENTALDATE,getDateTime(rentaldate));
        cv.put(KEY_RETURNDATE,getDateTime(renturndate));
        cv.put(KEY_FEES,fees);
        cv.put(KEY_PAID,Boolean.FALSE);

        updateData_car_available_with_regno(carreg,"0");

        long result = db.insert(TABLE_RENT,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor read_all_rent_with_paid(String _paid){
        String query = "SELECT * FROM " + TABLE_RENT + " where Paid=" + _paid ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    int get_rent_money_all(){
        int res = 0;

        String selectQuery = "SELECT  * FROM " + TABLE_RENT + " where Paid=1" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                res = res + Integer.valueOf(cursor.getString(5));
                ;//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        return res;
    }

    int get_rent_money_month(String _month, String year){
        // _month = 01 02 ... 10 12
        if (_month.length() == 1){
            _month = "0" + _month;
        }

        int res = 0;

        String selectQuery = "SELECT  * FROM " + TABLE_RENT + " where Paid=1" +
                " and strftime('%Y',RentalDate)='" + year +
                "' and strftime('%m',RentalDate)='"+ _month + "'"
                ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                res = res + Integer.valueOf(cursor.getString(5));
                ;//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        return res;
    }

    int get_rent_money_today(String day, String _month, String year){
        // _month = 01 02 ... 10 12
        // _day = 00
        if (_month.length() == 1){
            _month = "0" + _month;
        }

        int res = 0;

        String selectQuery = "SELECT  * FROM " + TABLE_RENT + " where Paid=1" +
                " and strftime('%Y',RentalDate)='" + year +
                "' and strftime('%m',RentalDate)='"+ _month + "'" +
                " and strftime('%d',RentalDate)='"+ day + "'"
                ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                res = res + Integer.valueOf(cursor.getString(5));
                ;//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        return res;
    }

    Cursor read_all_rent(){
        String query = "SELECT * FROM " + TABLE_RENT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    void updateData_rent(String row_id,String carreg, Integer rent_cusid,String rentaldate,String renturndate, Integer fees){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_CARREG,carreg);
        cv.put(KEY_RENT_CUSID,rent_cusid);
        cv.put(KEY_RENTALDATE,getDateTime(rentaldate));
        cv.put(KEY_RETURNDATE,getDateTime(renturndate));
        cv.put(KEY_FEES,fees);

        long result = db.update(TABLE_RENT, cv, KEY_RENTID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }
    }
    void updateData_rent_when_car_update(String oldRegno,String newRegno){
        String query = "UPDATE " + TABLE_RENT +
                " SET " + KEY_CARREG + "=" + "'" + newRegno + "'" +
                " WHERE " + KEY_CARREG + "=" + "'" + oldRegno + "' ";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    void updateData_rent_paid(String row_id, String _regno, String _paid){
        String query = "UPDATE " + TABLE_RENT +
                " SET " + KEY_PAID + "=" + _paid +
                " WHERE " + KEY_RENTID + "="  + row_id ;

        updateData_car_available_with_regno(_regno,"1");

        Toast.makeText(context, "Xác nhận thanh toán thành công", Toast.LENGTH_SHORT).show();

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
    }

    void delete_one_rent(String row_id, String _regno){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_RENT, KEY_RENTID+"=?", new String[]{row_id});

//        updateData_car_available_with_regno(_regno,"1");

        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_all_rent(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RENT);
    }
    // endregion

    // region table car
    private static final String TABLE_CAR = "Car";
    private static final String KEY_CAR_ID = "Car_Id";
    private static final String KEY_REGNO = "Regno";
    private static final String KEY_BRAND = "Brand";
    private static final String KEY_MODEL = "Model";
    private static final String KEY_PRICE = "Price";
    private static final String KEY_AVAILABLE = "Available";

    private static final String CREATE_TABLE_CAR =
            "CREATE TABLE " + TABLE_CAR +
                    " (" +
                    KEY_CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_REGNO + " TEXT," +
                    KEY_BRAND + " TEXT," +
                    KEY_MODEL + " TEXT," +
                    KEY_PRICE + " INTEGER," +
                    KEY_AVAILABLE + " BOOLEAN" +
                    ")";

    void add_car(String regno, String brand, String model, Integer price, Boolean available){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (regno.length() == 0 || brand.length() == 0 || model.length() == 0 ){
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        cv.put(KEY_REGNO, regno);
        cv.put(KEY_BRAND, brand);
        cv.put(KEY_MODEL, model);
        cv.put(KEY_PRICE, price);
        cv.put(KEY_AVAILABLE, available);

        Toast.makeText(context, "xe" + available, Toast.LENGTH_SHORT).show();

        long result = db.insert(TABLE_CAR,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor  read_car_fees_with_regno(String regno){

        String query = "SELECT * FROM " + TABLE_CAR + " WHERE " + KEY_REGNO + "='" + regno.trim() + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        int result = 1;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor read_all_car(){
        String query = "SELECT * FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData_car(String row_id, String regno, String brand, String model, Integer price, Boolean available){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_REGNO, regno);
        cv.put(KEY_BRAND, brand);
        cv.put(KEY_MODEL, model);
        cv.put(KEY_PRICE, price);
        cv.put(KEY_AVAILABLE, available);

        long result = db.update(TABLE_CAR, cv, KEY_CAR_ID+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }
    }


    void updateData_car_available_with_regno(String _regno, String _available){
        String query = "UPDATE " + TABLE_CAR +
                " SET " + KEY_AVAILABLE + "=" + _available +
                " WHERE " + KEY_REGNO + "=" + "'" + _regno + "' ";
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL(query);

    }

    void delete_one_car(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_CAR, KEY_CAR_ID+"=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    void delete_all_car(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CAR);
    }

    public List<String> get_all_car_spinner(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAR + " where Available=1" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1) + "_" +
                        cursor.getString(2)+ "_"+
                        cursor.getString(3));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> get_all_car_regno_spinner(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAR + " where Available=1" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1)
                );//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
    // endregion

    private static final String CREATE_TABLE_CURRENT_ACCOUNT = "create Table Current_Account(Current_User TEXT primary key)";

    public Boolean add_current_account(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Current_User",user);
        long result = db.insert("Current_Account",null,cv);
        if(result == -1) return false;
        else{
            return true;
        }

    }
    public String get_current_account()
    {
        String query = "SELECT * FROM " + "Current_Account";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
        {
            return "";
        }
    }
    void delete_current_account(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "Current_Account");
    }

    DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_RENT);
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_CAR);
        db.execSQL(CREATE_TABLE_CURRENT_ACCOUNT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Account");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE IF EXISTS Current_Account");
        onCreate(db);
    }
}
